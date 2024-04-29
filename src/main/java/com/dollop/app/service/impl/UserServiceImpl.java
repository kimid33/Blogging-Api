package com.dollop.app.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dollop.app.dtos.PageableResponse;
import com.dollop.app.entity.Role;
import com.dollop.app.entity.RoleName;
import com.dollop.app.entity.User;
import com.dollop.app.entity.UserRole;
import com.dollop.app.exception.DuplicateEntryException;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.repo.UserRepository;
import com.dollop.app.service.IUserService;
import com.dollop.app.service.helper.Helper;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private ModelMapper mapper;
	@Override
	public Integer createUser(User user) {
		if(urepo.existsByuserName(user.getUserName())) {
			throw new DuplicateEntryException("duplicate username entry not allowed");
		}
		if(urepo.existsByuserEmail(user.getUserEmail())) {
			throw new DuplicateEntryException("duplicate email entry not allowed");
		}
		
		if(urepo.existsByuserPassword(user.getUserPassword())) {
			throw new DuplicateEntryException("duplicate password entry not allowed");
		}
		
		if(urepo.existsByuserPhone(user.getUserPhone())) {
			throw new DuplicateEntryException("duplicate phone no entry not allowed");
		}
		
		if(urepo.existsByuserWebsite(user.getUserWebsite())) {
			throw new DuplicateEntryException("duplicate website entry not allowed");
		}
		
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		
		
		Role role = new Role();
		role.setId(2L);
		role.setName(RoleName.ROLE_USER);
		userRole.setRoles(role);
		
		Set<UserRole> userRoleList = new HashSet<>();
		userRoleList.add(userRole);
		user.setUserRoles(userRoleList);
		
		user= urepo.save(user);
		return user.getUserId();
	}

	@Override
	public User updateUser(User user,Integer userId) {
		User u=urepo.findById(userId).orElseThrow(
				()->new ResourceNotFoundException("user not found for given user id"));
		if(u.isActive())
		{
		u.setUserEmail(user.getUserEmail());
		u.setUserFirstName(user.getUserFirstName());
		u.setUserLastName(user.getUserLastName());
		u.setUserPassword(user.getUserPassword());
		u.setUserPhone(user.getUserPhone());
		u.setUserName(user.getUserName());
		u.setUserWebsite(user.getUserWebsite());
		urepo.save(u);
		}
		else 
		{
			throw new ResourceNotFoundException("user is not active for validation");
		}
		return u;
		
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User u=urepo.findById(userId).orElseThrow(()->
		new ResourceNotFoundException("user not found for given id "+ userId));
		if(u.isActive())
		{
		  u.setActive(false);
		  urepo.save(u);
		}
		else
		{
			throw new ResourceNotFoundException("user is already deactivated for given id "+userId);		}
	}

	@Override
	public User getOneUser(Integer userId) {
		User user= urepo.findById(userId).orElseThrow(()->
		new ResourceNotFoundException("user not found by given id"));
		if(user.isActive())
		{
			return user;
		}
		else
		{
			throw new ResourceNotFoundException("User not found for id "+ userId);
		}
	}

	@Override
	public List<User> getAllUser() {
		List<User> list=urepo.findByIsActiveTrue();
		return list;
	}

	@Override
	public List<User> SearchUser(String keyword) {
		List<User> u = urepo.findByuserNameContaining(keyword);
		List<User> users=u.stream()
				.map((user)->mapper
						.map(user, User.class))
				.collect(Collectors.toList());
		return users;
		}

	@Override
	public PageableResponse<User> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir) {
		Sort sort = (sortDir.equalsIgnoreCase("desc"))?
				(Sort.by(sortBy).descending()):
					(Sort.by(sortBy).ascending());
		Pageable pageable=PageRequest.of(pageNumber, pageSize, sort);
		Page<User> page=urepo.findAll(pageable);
		PageableResponse<User> response=Helper.getpageableResponse(page,User.class);
		return response;
	}

	@Override
	public User loginUser(String userName, String userPassword) {
		User user = urepo.findByuserNameAndPassword(userName, userPassword);
		//System.out.println(user.getUserEmail());
		return user;
	}

	@Override
	public void userRegistration(User user) {
		if(urepo.findByuserName(user.getUserName()).isPresent())
		{
			throw new ResourceNotFoundException("user already exists");
		}
		if(urepo.findByuserEmail(user.getUserEmail()).isPresent())
		{
			throw new ResourceNotFoundException("user already exists");
		}
		
		User newUserRegister = new User();
		newUserRegister.setUserFirstName(user.getUserFirstName());
		newUserRegister.setUserLastName(user.getUserLastName());
		newUserRegister.setUserName(user.getUserName());
		newUserRegister.setUserPassword(user.getUserPassword());
		newUserRegister.setUserEmail(user.getUserEmail());
		newUserRegister.setUserPhone(user.getUserPhone());
		urepo.save(newUserRegister);
	}
	}


