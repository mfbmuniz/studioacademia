package com.example.empresasjava;

import com.example.empresasjava.enums.RolesEnum;
import com.example.empresasjava.enums.SexEnum;
import com.example.empresasjava.models.*;
import com.example.empresasjava.repository.AddressRepository;
import com.example.empresasjava.repository.RoleRepository;
import com.example.empresasjava.repository.UserRepository;
import com.example.empresasjava.service.CityService;
import com.example.empresasjava.service.StateService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class EmpresasJavaApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private CityService cityService;

	@Autowired
	private StateService stateService;

	@Autowired
	private AddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(EmpresasJavaApplication.class, args);
	}

	@PostConstruct
	@Transactional
	public void createFirstAdminUser() throws NotFoundException {

		User admin = this.userRepository.findOneByEmail("admin@admin.com");

		Role admRole = this.roleRepository.findByName(RolesEnum.ADMIN.getCode());

		if(admin == null){
			System.out.println("Creating first admin default account...");

			Cities city = this.cityService.findByCity("BH");
			States states = this.stateService.findByUf("MG");
			Address address = this.addressRepository.save(new Address(
					"Rua",
					123,
					"bairro",
					city,
					states
			));

			admin = new User("Admin",
					"admin@admin.com",
					this.bcryptEncoder.encode("12345678"),
					new Date(),
					"33853056",
					"33853056",
					"123456789",
					address,
					SexEnum.MALE,
					Collections.singletonList(admRole),
					new Date()
				);
		}else if(admin.getRoles().stream().noneMatch(role -> role.equals(admRole))){
			System.out.println("Adding role to existing Admin account...");
			admin.setRoles(Collections.singletonList(admRole));
		}else{
			return;
		}

		this.userRepository.save(admin);
	}
}
