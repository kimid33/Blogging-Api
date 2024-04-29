package com.dollop.app.service;

import com.dollop.app.entity.Address;

public interface IAddressService {
	//saveAddress 
		/***
		 * 
		 * @param keyword
		 * @return
		 */
	Integer saveAddress(Address address);
	//updateAddress
		/***
		 * 
		 * @param keyword
		 * @return
		 */
	Address updateAddress(Address address,Integer addressId);
	//deleteAddress
		/***
		 * 
		 * @param keyword
		 * @return
		 */
	void deleteAddress(Integer addressId);
}
