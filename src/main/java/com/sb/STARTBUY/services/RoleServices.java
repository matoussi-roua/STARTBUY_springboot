package com.sb.STARTBUY.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.STARTBUY.entites.Role;
import com.sb.STARTBUY.repository.RoleRepository;

@Service
public class RoleServices implements InterfaceRole {
	
	@Autowired
	RoleRepository roleRep;
	@Override
	public Role addRole(Role role) {
		return roleRep.save(role);
	}
	;
}
