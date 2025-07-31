package com.jsp.HomeServeO.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.HomeServeO.entity.Vendor;
import com.jsp.HomeServeO.entity.Work;
import com.jsp.HomeServeO.repo.VendorRepo;

@Repository
public class VendorDao {

	@Autowired
	VendorRepo vendRepo;

	@Autowired
	WorkDao workDao;

	/**
	 * This method takes input as Vendor type object reference to store/save the
	 * data into the Vendor Entity Table with the help of an abstract non-static
	 * save() method of JpaRepository interface.
	 * 
	 * @param Vendor
	 * @return the Vendor type of {@vend} reference to the method.
	 */
	public Vendor saveVendor(Vendor Vendor) {
		Vendor vend = vendRepo.save(Vendor);
		if (vend != null)
			return vend;
		return null;
	}

	/**
	 * This method is used to find/get all the Vendors who are available in the
	 * Vendor Entity table with the help of an abstract non-static findAll() method
	 * of JpaRepository interface.
	 * 
	 * @return the List<Vendor> type of {@vendList} reference to the method.
	 */
	public List<Vendor> getAllVendor() {
		List<Vendor> vendList = vendRepo.findAll();
		if (vendList != null)
			return vendList;
		return null;
	}

	/**
	 * This method is used to find/fetch the details of a single Vendor by using
	 * vendor id with the help of abstract non-static findById() method which
	 * returns Optional tpye of {@opt} Generic class. Now with the help of
	 * {@opt} reference we will call the non-static get() method of Optional class
	 * to change/convert into generic type and return the generic class reference.
	 * 
	 * @param id
	 * @return {@vendor} If the record is present into the database.
	 * @return null, If {@Vendor} is null that means the record is not present into
	 *         the database.
	 */
	public Vendor getVendorById(int id) {
		Optional<Vendor> opt = vendRepo.findById(id);
		Vendor vendor = opt.get();
		if (vendor != null)
			return vendor;
		return null;
	}

	/**
	 * This method is used to find/fetch the details of a single Vendor by using
	 * vendor email with the help of an abstract non-static findByEmail() method
	 * from JpaRepositry interface which returns the Vendor class type of reference
	 * is {@vendor}.
	 * 
	 * @param id
	 * @return {@vendor} If the record is present into the database.
	 * @return null, If {@vendor} is null that means the record is not present into
	 *         the database.
	 */
	public Vendor getVendorByEmail(String email) {
		Vendor vendor = vendRepo.findByEmail(email);
		if (vendor != null)
			return vendor;
		return null;
	}

	/**
	 * This method is used to remove/delete the record based on given id. So before
	 * deleting the record we should to check weather the record is available into
	 * the database or not. If the record is available then we will go for deleting
	 * operation with the help of an abstract non-static deleteById() method of
	 * JpaRepositry interface which returns void but we have to return the Vendor
	 * class type by explicitly which reference is {@vendor}.
	 * 
	 * @param id
	 * @return the Vendor type of {@vendor} reference to the method.
	 */
	public Vendor deleteVendorById(int id) {
		Vendor vendor = vendRepo.findById(id).get();
		if (vendor != null) {
			List<Work> list = workDao.listOfUnMappedWorks();
			if (list != null) {
				List<Vendor> updatedVendor = new ArrayList<>();
				for (Work work : list) {
					List<Vendor> vend = work.getVendors();
					if (vend != null) {
						for (Vendor v : vend) {
							if (v.getId() != id) {
								updatedVendor.add(v);
							}
						}
					}
					work.setVendors(updatedVendor);
					workDao.updateWork(work);
				}
			}
			vendor.setServiceCosts(null);
			vendRepo.deleteById(id);
			return vendor;
		}
		return null;
	}

	/**
	 * This method is used to remove/delete the details of a single Vendor by using
	 * Vendor email with the help of an abstract non-static deleteById() method from
	 * JpaRepositry interface which returns void but we have to return the Vendor
	 * class type by explicitly of reference is {@vendor}.
	 * 
	 * @param id
	 * @return {@vendor} If the record is present into the database.
	 * @return null, If {@vendor} is null that means the record is not present into
	 *         the database.
	 */
	public Vendor deleteVendorByEmail(String email) {
		Vendor vendor = vendRepo.findByEmail(email);
		if (vendor != null)
			vendRepo.deleteById(vendor.getId());
		return null;
	}

	/**
	 * This method is used to modify/update the record based on given Vendor record
	 * object reference. Before updating we have to check the data from database
	 * with the help of vendor id for validation purpose. If we will get the object
	 * from database then every time we have to check for null value of each data
	 * member, if it is not null then have to update {@vendDb} object. And finally
	 * we need to update the database record with the {@vendDb} object.
	 * 
	 * @param vendor
	 * @return the Vendor type of {@vend} reference to the method.
	 */
	public Vendor updateVendor(Vendor vendor) {
		Vendor vendDb = vendRepo.findById(vendor.getId()).get();
		if (vendDb != null) {
			if (vendor.getName() != null) {
				vendDb.setName(vendor.getName());
			}
			if (vendor.getEmail() != null) {
				vendDb.setEmail(vendor.getEmail());
			}
			if (vendor.getPhone() != 0) {
				vendDb.setPhone(vendor.getPhone());
			}
			if (vendor.getPassword() != null) {
				vendDb.setPassword(vendor.getPassword());
			}
			if (vendor.getRole() != null) {
				vendDb.setRole(vendor.getRole());
			}
			if (vendor.getCostPerDay() != 0.0) {
				vendDb.setCostPerDay(vendor.getCostPerDay());
			}
			if (vendor.getAddress() != null) {
				vendDb.setAddress(vendor.getAddress());
			}
			if (vendor.getImage() != null) {
				vendDb.setImage(vendor.getImage());
			}
			if (vendor.getServiceCosts() != null) {
				vendDb.setServiceCosts(vendor.getServiceCosts());
			}

			Vendor vend = vendRepo.save(vendDb);
			return vend;
		}
		return null;
	}

}
