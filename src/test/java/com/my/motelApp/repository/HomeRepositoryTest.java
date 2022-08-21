package com.my.motelApp.repository;

import java.util.Arrays;

import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.my.motelApp.entity.Role;
import com.my.motelApp.entity.RoleName;
import com.my.motelApp.entity.User;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestClassOrder(OrderAnnotation.class)

class HomeRepositoryTest {

	Logger log = LoggerFactory.getLogger(HomeRepositoryTest.class);
	
	@Autowired
	private HomeRepository homeRepository;
	
	@Autowired
	private WardRepository wardRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private InfoRepository infoRepository;
	
	@Autowired
	private DescriptionRepository descriptionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Test
	@Order(1)
	void testCreateRoles() {
		Role roleAdmin = new Role();
		roleAdmin.setName(RoleName.ADMIN);
		
		roleRepository.save(roleAdmin);
		
		Role roleUser = new Role();
		roleUser.setName(RoleName.USER);
		
		roleRepository.save(roleUser);
		
	}
	
	@Test
	@Order(2)
	void testCreateUser() {
		User userK = new User(); 
		userK.setUsername("khai");
		userK.setPassword(passwordEncoder.encode("123"));
		userK.setEmail("k@gmail.com");
		userK.setPhoneNumber("Phone number");
		userK.setEnabled(Boolean.TRUE);
		
		Role role = roleRepository.findByName(RoleName.ADMIN).get();

		
		userK.setRoles(Arrays.asList(role));
		
		log.info("Before create user");
		userRepository.save(userK);
		log.info("Create user");
		
		// another user
		User userKiet = new User(); 
		userKiet.setUsername("kiet");
		userKiet.setPassword(passwordEncoder.encode("123"));
		userKiet.setEmail("kiet@gmail.com");
		userKiet.setPhoneNumber("Phone number");
		userKiet.setEnabled(Boolean.TRUE);
		
		Role role2 = roleRepository.findByName(RoleName.USER).get();

		
		userKiet.setRoles(Arrays.asList(role2));
		
		userRepository.save(userKiet);
		
	}
	
	
}
