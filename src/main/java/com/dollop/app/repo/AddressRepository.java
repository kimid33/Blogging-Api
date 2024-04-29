package com.dollop.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.app.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
}
