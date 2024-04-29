package com.dollop.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.app.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
