package com.dollop.app.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dollop.app.entity.Address;
import com.dollop.app.service.IAddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private IAddressService service;
	
	@PostMapping("/create")
	public ResponseEntity<Integer> saveAddress(@Valid @RequestBody Address address){
		Integer add = service.saveAddress(address);
		ResponseEntity<Integer> response = new ResponseEntity<>(add,HttpStatus.CREATED);
		return response;
	}
	
	@PutMapping("/{addressId}")
	public ResponseEntity<Address> updateAddress(@Valid @PathVariable("addressId") Integer addressId,
			@RequestBody Address address){
		Address add = service.updateAddress(address, addressId);
		return ResponseEntity.ok(add);
	}
	
	@DeleteMapping("/{addressId}")
	public ResponseEntity<String> deleteAddress(@PathVariable Integer addressId){
		service.deleteAddress(addressId);
		return new ResponseEntity<String>("address deleted",HttpStatus.OK);
	}
}
