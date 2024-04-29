package com.dollop.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.app.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

}
