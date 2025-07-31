package com.jsp.HomeServeO.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.HomeServeO.entity.Address;
import com.jsp.HomeServeO.repo.AddressRepo;

@Repository
public class AddressDao {
	@Autowired
	AddressRepo addRepo;

	/**
	 * This method is used to find/fetch the details of an Address by using address
	 * id with the help of an abstract non-static findById() method which returns
	 * Optional tpye of {@opt} Generic class. Now with the help of {@opt} reference
	 * we will call the non-static get() method of Optional class to change/convert
	 * into generic type and return the generic class reference.
	 * 
	 * @param id
	 * @return {@address} If the record is present into the database.
	 * @return null, If {@address} is null that means the record is not present into
	 *         the database.
	 */
	public Address getAddressById(int id) {
		Optional<Address> opt = addRepo.findById(id);
		Address address = opt.get();
		if (address != null) {
			return address;
		}
		return null;
	}

	/**
	 * This method is used to modify/update the record based on given Address object
	 * reference. Before updating we have to check the data from database with the
	 * help of address id for validation purpose. If we will get the object from
	 * database then every time we have to check for null value of each data member,
	 * if it is not null then have to update {@addressDb} object. And finally we
	 * need to update the database record with the {@addressDb} object.
	 * 
	 * @param vendor
	 * @return the Vendor type of {@add} reference to the method.
	 */
	public Address updateAddress(Address address) {
		Address addressDb = addRepo.findById(address.getId()).get();
		if (addressDb != null) {
			if (address.getDoorNo() != null) {
				addressDb.setDoorNo(address.getDoorNo());
			}
			if (address.getDistrict() == null) {
				addressDb.setDistrict(address.getDistrict());
			}
			if (address.getLandmark() == null) {
				addressDb.setLandmark(address.getLandmark());
			}
			if (address.getPincode() == 0) {
				addressDb.setPincode(address.getPincode());
			}
			if (address.getState() == null) {
				addressDb.setState(address.getState());
			}
			if (address.getStreet() == null) {
				addressDb.setStreet(address.getStreet());
			}
			Address add = addRepo.save(addressDb);
			return add;
		}
		return null;
	}

}
