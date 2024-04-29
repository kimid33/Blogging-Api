package com.dollop.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollop.app.entity.Role;
import com.dollop.app.entity.RoleName;
import com.dollop.app.repo.RoleRepository;
import com.dollop.app.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public Role createRole() {
		Role rol = new Role();
		rol.setId(1L);
		rol.setName(RoleName.ROLE_USER);
		
		Role rol1 = new Role();
		rol1.setId(2L);
		rol1.setName(RoleName.ROLE_ADMIN);
		
		return roleRepository.save(rol1);		
		
		
		
	}

}
