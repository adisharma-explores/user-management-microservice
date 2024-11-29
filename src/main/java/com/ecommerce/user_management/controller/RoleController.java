package com.ecommerce.user_management.controller;

import com.ecommerce.user_management.Exceptions.InvalidUserNameException;
import com.ecommerce.user_management.model.Customer;
import com.ecommerce.user_management.model.Role;
import com.ecommerce.user_management.pojo.CustomerRequest;
import com.ecommerce.user_management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    @Autowired
    CustomerService Cs;

    @PostMapping("/addRole")
    public ResponseEntity<Customer> assignRole(@RequestBody CustomerRequest request) throws InvalidUserNameException {
        return ResponseEntity.ok().body(Cs.addRoleToUser(request.getUserName(), request.getRole()));
    }

    @PostMapping("/insertRole")
    public ResponseEntity<Role> insertRole(@RequestBody Role request) throws InvalidUserNameException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/insertRole").toUriString());
        return ResponseEntity.created(uri).body(Cs.saveRole(request));
    }
}
