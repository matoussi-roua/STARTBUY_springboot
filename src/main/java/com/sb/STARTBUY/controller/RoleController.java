package com.sb.STARTBUY.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sb.STARTBUY.entites.Role;
import com.sb.STARTBUY.services.RoleServices;
@RestController
public class RoleController {
@Autowired
RoleServices rolesrv;
@PostMapping(value = "/addrole")
public Role addRole(@RequestBody Role role) {
    return rolesrv.addRole(role);
}

}