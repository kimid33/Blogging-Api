package com.dollop.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollop.app.entity.Address;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.repo.AddressRepository;
import com.dollop.app.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {
	
	@Autowired
	private AddressRepository arepo;
	@Override
	public Integer saveAddress(Address address) {
		Address add = arepo.save(address);
		return add.getAddressId();
	}

	@Override
	public Address updateAddress(Address address, Integer addressId) {
		Address add = arepo.findById(addressId).orElseThrow(()->
		new ResourceNotFoundException("address not found for given id"+addressId));
		if(add.isActive())
		{
		add.setCity(address.getCity());
		add.setStreet(address.getStreet());
		add.setSuite(address.getSuite());
		add.setZipCode(address.getZipCode());
		arepo.save(add);
		}
		else
		{
			throw new ResourceNotFoundException("address is not activated for given ids");
		}
		return add;
	}

	@Override
	public void deleteAddress(Integer addressId) {
		Address add = arepo.findById(addressId).orElseThrow(()->
		new ResourceNotFoundException("address not found for given id"+addressId));
		if(add.isActive())
		{
			add.setActive(false);
			arepo.save(add);
		}
		else
		{
			throw new RuntimeException("address already deleted for given id "+addressId);
		}
	}

}
