package com.jsp.HomeServeO.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.HomeServeO.dao.AddressDao;
import com.jsp.HomeServeO.entity.Address;
import com.jsp.HomeServeO.exception.NoSuchElementFoundForAddressException;
import com.jsp.HomeServeO.exception.UpdationFailureException;
import com.jsp.HomeServeO.util.ResponseStructure;

@Service
public class AddressService {
	@Autowired
	AddressDao addresDao;

	/**
	 * Retrieves an Address entity by its unique identifier.
	 *
	 * @param id The unique identifier of the Address to retrieve.
	 * @return ResponseEntity containing a ResponseStructure with details of the
	 *         retrieved Address. If the Address is found, HttpStatus.FOUND (201) is
	 *         returned with the Address details. If no Address is found for the
	 *         given id, a NoSuchElementFoundForAddressException is thrown.
	 * @throws NoSuchElementFoundForAddressException If no Address is found for the
	 *                                               given id.
	 */
	public ResponseEntity<ResponseStructure<Address>> getAddressById(int id)
			throws NoSuchElementFoundForAddressException {
		Address address = addresDao.getAddressById(id);
		ResponseStructure<Address> structure = new ResponseStructure<>();

		if (address != null) {
			structure.setTime(LocalDateTime.now());
			structure.setData(address);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Address Id is found...!");
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.FOUND);
		} else {
			throw new NoSuchElementFoundForAddressException("Address cannot be found by the given id " + id);
		}
	}

	/**
	 * Updates an existing address record in the database with given new data.
	 *
	 * @param address The updated Address object containing new data.
	 * @return ResponseEntity containing a ResponseStructure with updated Address
	 *         details upon successful update.
	 * @throws UpdationFailureException if the address with the provided ID does not
	 *                                  exist in the database.
	 */
	public ResponseEntity<ResponseStructure<Address>> modifyAddress(Address address) throws UpdationFailureException {
		// Initialize the response structure
		ResponseStructure<Address> structure = new ResponseStructure<>();
		// Retrieve the existing address from the database based on its ID
		Address addressDb = addresDao.getAddressById(address.getId());
		if (addressDb != null) {
			Address updatedAddress = addresDao.updateAddress(address);
			structure.setTime(LocalDateTime.now());
			structure.setData(updatedAddress);
			structure.setMessage("Address has been updated successfully...!");
			structure.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.CREATED);
		} else {
			throw new UpdationFailureException("Cannot update address: Address not found with the given ID.");
		}
	}

}
