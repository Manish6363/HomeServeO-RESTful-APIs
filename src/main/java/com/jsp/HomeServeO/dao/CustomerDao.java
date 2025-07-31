package com.jsp.HomeServeO.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.HomeServeO.entity.Customer;
import com.jsp.HomeServeO.entity.Work;
import com.jsp.HomeServeO.repo.CustomerRepo;

@Repository
public class CustomerDao {

	@Autowired
	private CustomerRepo custRepo;

	@Autowired
	private WorkDao workDao;

	/**
	 * This method takes input as Customer type object reference to store/save the
	 * data into the Customer Entity Table with the help of an abstract non-static
	 * save() method of JpaRepository interface.
	 * 
	 * @param customer
	 * @return the Customer type of {@cust} reference to the method.
	 */
	public Customer saveCustomer(Customer customer) {
		System.out.println("Customer DAO");
		Customer cust = custRepo.save(customer);
		if (cust != null)
			return cust;
		return null;
	}

	/**
	 * This method is used to find/get all the customer who are available in the
	 * Customer Entity table with the help of an abstract non-static findAll()
	 * method of JpaRepository interface.
	 * 
	 * @return the List<Customer> type of {@custList} reference to the method.
	 */
	public List<Customer> getAllCustomer() {
		List<Customer> custList = custRepo.findAll();
		if (custList != null)
			return custList;
		return null;
	}

	/**
	 * This method is used to find/fetch the details of a single customer by using
	 * customer id with the help of abstract non-static findById() method which
	 * returns Optional tpye of {@opt} Generic class. Now with the help of
	 * {@opt} reference we will call the non-static get() method of Optional class
	 * to change/convert into generic type and return the generic class reference.
	 * 
	 * @param id
	 * @return {@customer} If the record is present into the database.
	 * @return null, If {@customer} is null that means the record is not present
	 *         into the database.
	 */
	public Customer getCustomerById(int id) {
		Optional<Customer> opt = custRepo.findById(id);
		Customer customer = opt.get();
		if (customer != null)
			return customer;
		return null;
	}

	/**
	 * This method is used to find/fetch the details of a single customer by using
	 * customer email with the help of an abstract non-static findByEmail() method
	 * from JpaRepositry interface which returns the Customer class type of
	 * reference is {@customer}.
	 * 
	 * @param id
	 * @return {@customer} If the record is present into the database.
	 * @return null, If {@customer} is null that means the record is not present
	 *         into the database.
	 */
	public Customer getCustomerByEmail(String email) {
		Customer customer = custRepo.findByEmail(email);
		if (customer != null)
			return customer;
		return null;
	}

	/**
	 * This method is used to remove/delete the record based on given id. So before
	 * deleting the record we should to check weather the record is available into
	 * the database or not. If the record is available then we will go for deleting
	 * operation with the help of an abstract non-static deleteById() method of
	 * JpaRepositry interface which returns void but we have to return the Customer
	 * class type by explicitly which reference is {@customer}.
	 * 
	 * @param id
	 * @return the Customer type of {@customer} reference to the method.
	 */
	public Customer deleteCustomerById(int id) {
		Customer customer = custRepo.findById(id).get();
		if (customer != null) {
			List<Work> work = workDao.getAllWork();
			if (work != null) {
				for (Work w : work) {
					Customer cust = w.getCustomer();
					if (cust != null && cust.getId() == id) {
						w.setCustomer(null);
						workDao.updateWork(w);
					}
				}
			}
			custRepo.deleteById(id);
			return customer;
		}
		return null;
	}

	/**
	 * This method is used to remove/delete the details of a single customer by
	 * using customer email with the help of an abstract non-static deleteById()
	 * method from JpaRepositry interface which returns void but we have to return
	 * the Customer class type by explicitly of reference is {@customer}.
	 * 
	 * @param id
	 * @return {@customer} If the record is present into the database.
	 * @return null, If {@customer} is null that means the record is not present
	 *         into the database.
	 */
	public Customer deleteCustomerByEmail(String email) {
		Customer customer = custRepo.findByEmail(email);
		if (customer != null) {
			List<Work> work = workDao.getAllWork();
			if (work != null) {
				for (Work w : work) {
					Customer cust = w.getCustomer();
					if (cust != null && cust.getEmail().equalsIgnoreCase(email)) {
						w.setCustomer(null);
						workDao.updateWork(w);
					}
				}
			}
			custRepo.deleteById(customer.getId());
			return customer;
		}
		return null;
	}

	/**
	 * This method is used to modify/update the record based on given customer
	 * record object reference. Before updating we have to check the data from
	 * database with the help of customer id for validation purpose. If we will get
	 * the object from database then every time we have to check for null value of
	 * each data member, if it is not null then have to update {@custDb} object. And
	 * finally we need to update the database record with the {@custDb} object.
	 * 
	 * @param customer
	 * @return the Customer type of {@cust} reference to the method.
	 */
	public Customer updateCustomer(Customer customer) {
		Customer custDb = custRepo.findById(customer.getId()).get();
		if (custDb != null) {
			if (customer.getName() != null) {
				custDb.setName(customer.getName());
			}
			if (customer.getEmail() != null) {
				custDb.setEmail(customer.getEmail());
			}
			if (customer.getPhone() != 0) {
				custDb.setPhone(customer.getPhone());
			}
			if (customer.getPassword() != null) {
				custDb.setPassword(customer.getPassword());
			}
			if (customer.getFamilyCount() != 0) {
				custDb.setFamilyCount(customer.getFamilyCount());
			}
			if (customer.getAddress() != null) {
				custDb.setAddress(customer.getAddress());
			}
			if (customer.getImage() != null) {
				custDb.setImage(customer.getImage());
			}
			if (customer.getWork() != null) {
				custDb.setWork(customer.getWork());
			}

			Customer cust = custRepo.save(custDb);
			return cust;
		}
		return null;
	}

}
