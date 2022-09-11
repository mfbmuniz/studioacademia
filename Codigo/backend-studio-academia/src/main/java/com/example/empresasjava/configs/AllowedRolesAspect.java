package com.example.empresasjava.configs;

import com.example.empresasjava.enums.RolesEnum;
import com.example.empresasjava.models.Role;
import com.example.empresasjava.models.User;
import com.example.empresasjava.service.AuthService;
import com.example.empresasjava.service.UserService;
import com.example.empresasjava.util.Secure;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Component
public class AllowedRolesAspect {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @Around("@annotation(com.example.empresasjava.util.Secure)")
    public Object doSomething(ProceedingJoinPoint jp) throws Throwable {

        List<String> allowedRoles = Arrays.stream(((MethodSignature) jp.getSignature()).getMethod()
                        .getAnnotation(Secure.class).value())
                .collect(Collectors.toSet())
                .stream().map(RolesEnum::getCode)
                .collect(Collectors.toList());

        Authentication authentication = this.authService.getAuthenticatedUser();

        User user = this.userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new BadCredentialsException("Erro ao identificar permissão! Usuario não encontrado"));


        List<String> userRoles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        Object[] args = jp.getArgs();
        args[0] = !Collections.disjoint(allowedRoles, userRoles);
        return jp.proceed(args);
    }

}
