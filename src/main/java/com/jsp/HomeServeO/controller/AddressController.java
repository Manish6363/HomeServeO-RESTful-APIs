package com.jsp.HomeServeO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.HomeServeO.entity.Address;
import com.jsp.HomeServeO.service.AddressService;
import com.jsp.HomeServeO.util.ResponseStructure;

@RestController
//@CrossOrigin(origins = "*",methods =  {RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})
public class AddressController {

	@Autowired
	private AddressService service;

	/**
	 * Fetches Address data from the Address table based on the provided Address ID.
	 * 
	 * This method retrieves Address information identified by the specified ID from
	 * the data source and returns it encapsulated within a structured response.
	 * 
	 * @param id The unique identifier of the Address whose data is to be fetched.
	 * @return response containing a ResponseStructure wrapping the Address data
	 *         fetched from the database.
	 */
	@GetMapping("/fetchAddressDataById")
	public ResponseEntity<ResponseStructure<Address>> fetchAddressById(@RequestParam int id) {
		ResponseEntity<ResponseStructure<Address>> response = service.getAddressById(id);
		return response;
	}

	/**
	 * Used to update Address data by mapping the request to save or insert data
	 * into the Address table.
	 * 
	 * @param address The address object containing updated data.
	 * @return response containing a ResponseStructure with the updated Address
	 *         object.
	 */
	@PutMapping("/updateAddressData")
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@RequestBody Address address) {
		ResponseEntity<ResponseStructure<Address>> response = service.modifyAddress(address);
		return response;
	}
}
