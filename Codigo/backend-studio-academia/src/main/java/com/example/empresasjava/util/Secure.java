package com.example.empresasjava.util;

import com.example.empresasjava.enums.RolesEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Secure {
    RolesEnum[] value();
}
