package com.jsp.HomeServeO.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.HomeServeO.config.MailSenderForLiveProject;
import com.jsp.HomeServeO.dao.CustomerDao;
import com.jsp.HomeServeO.entity.Customer;
import com.jsp.HomeServeO.exception.CustomerEmailNotMatchException;
import com.jsp.HomeServeO.exception.CustomerPasswordNotMatchException;
import com.jsp.HomeServeO.exception.NoSuchElementFoundByCustomerException;
import com.jsp.HomeServeO.exception.NoSuchListOfElementFoundByCustomerException;
import com.jsp.HomeServeO.exception.UpdationFailureException;
import com.jsp.HomeServeO.util.ResponseStructure;

import jakarta.mail.MessagingException;

@Service
public class CustomerService {
	@Autowired
	private CustomerDao custDao;

	@Autowired
	private MailSenderForLiveProject sendMail;

	/**
	 * Saves a new customer record.
	 *
	 * @param customer The customer object to be saved. This object should contain
	 *                 all the necessary fields for creating a new customer record.
	 *                 Refer to the {@link Customer} class documentation for details
	 *                 on the expected data format.
	 * @return ResponseEntity containing a {@link ResponseStructure} with
	 *         information about the saved customer. - If successful, returns HTTP
	 *         status 201 (CREATED) with a ResponseStructure. If unsuccessful,
	 *         usually returns other HTTP status codes indicating the error. -
	 *         Common errors might include: - 400 (BAD_REQUEST): Provided customer
	 *         object fails validation. - 500 (INTERNAL_SERVER_ERROR): Unexpected
	 *         error during data persistence.
	 * @throws RuntimeException if an unexpected error occurs during the save
	 *                          operation.
	 */
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer customer) {
		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
		structure.setTime(LocalDateTime.now());
		structure.setData(custDao.saveCustomer(customer));
		try {
			sendMail.sendMail(customer.getEmail(), customer);
		} catch (MessagingException e) {
			System.out.println("Mail cannot be sent to the given mail...!");
//			e.printStackTrace();
		}
		structure.setMessage("Customer record has been saved successfully...!");
		structure.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.CREATED);
	}

	/**
	 * Retrieves a customer record from the database based on the given ID.
	 *
	 * @param id The unique identifier of the customer to retrieve.
	 * @return ResponseEntity containing a ResponseStructure with details of the
	 *         customer if found. If no customer is found with the given ID, a
	 *         NoSuchElementFoundByCustomerException is thrown.
	 * @throws NoSuchElementFoundByCustomerException If no customer record is found
	 *                                               for the specified ID.
	 */
	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(int id) {
		Customer db = custDao.getCustomerById(id);
		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
		if (db != null) {
			structure.setTime(LocalDateTime.now());
			structure.setData(db);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Customer record is found by id...!");
			return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.FOUND);
		} else {
			throw new NoSuchElementFoundByCustomerException("No any record found for the particular Id: " + id);
		}
	}

	/**
	 * Retrieves all customer records from the database.
	 *
	 * @return ResponseEntity containing ResponseStructure with list of Customer
	 *         objects
	 * @throws NoSuchListOfElementFoundByCustomerException if no customer records
	 *                                                     are found
	 */
	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer() {
		ResponseStructure<List<Customer>> structure = new ResponseStructure<List<Customer>>();
		List<Customer> custDb = custDao.getAllCustomer();
		if (custDb != null) {
			structure.setTime(LocalDateTime.now());
			structure.setData(custDb);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("All customer record is found...!");
			return new ResponseEntity<ResponseStructure<List<Customer>>>(structure, HttpStatus.FOUND);
		} else {
			throw new NoSuchListOfElementFoundByCustomerException("No any list of record found...!");
		}
	}

	/**
	 * Logs in a customer with the provided email and password. Retrieves a customer
	 * record from the database based on the given email. And validate the
	 * credentials for log-in.
	 *
	 * @param email    The unique email address of the customer attempting to log
	 *                 in.
	 * @param password The password associated with the customer's account.
	 * @return A ResponseEntity containing a ResponseStructure with details of the
	 *         customer upon successful login.
	 * @throws CustomerPasswordNotMatchException If the provided password does not
	 *                                           match the password stored for the
	 *                                           customer.
	 * @throws CustomerEmailNotMatchException    If no customer exists with the
	 *                                           provided email.
	 */
	public ResponseEntity<ResponseStructure<Customer>> customerLogin(String email, String password) {
		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
		Customer custDb = custDao.getCustomerByEmail(email);
		if (custDb != null) {
			if (custDb.getPassword().equals(password)) {
				structure.setTime(LocalDateTime.now());
				structure.setData(custDb);
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setMessage("Customer login is successfull...!");
				return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.FOUND);
			} else {
				throw new CustomerPasswordNotMatchException("Customer given Password is incorrect: " + password);
			}
		} else {
			throw new CustomerEmailNotMatchException("Customer given email is wrong...!");
		}
	}

	/**
	 * This method is used to generate a random 6-digits of Integer code which is
	 * sent to the registered user email id.
	 * 
	 * @param email The email associated with the customer's account where OTP is to sent.
	 * @return response containing a ResponseStructure with details of the customer
	 *         upon successful OTP generation.
	 */
	public ResponseEntity<ResponseStructure<Integer>> generateOTP(String email) {
		ResponseStructure<Integer> structure = new ResponseStructure<Integer>();
		Customer custDb = custDao.getCustomerByEmail(email);
		if (custDb != null) {
			int randomOTP = new Random().nextInt(999999);
			structure.setTime(LocalDateTime.now());
			structure.setData(randomOTP);
			try {
				sendMail.generateOTPCode(email, custDb, randomOTP);
			} catch (MessagingException e) {
				System.out.println("OTP generation is failed...!");
				e.printStackTrace();
			}
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("OK! OTP Generated...!");
			return new ResponseEntity<ResponseStructure<Integer>>(structure, HttpStatus.FOUND);
		} else {
			throw new CustomerEmailNotMatchException("Customer given email is wrong...!");
		}
	}

	/**
	 * Deletes a customer record from the database by the specified ID.
	 *
	 * @param id The unique ID of the customer record to delete.
	 * @return ResponseEntity containing ResponseStructure<Customer> with details of
	 *         the deleted customer if found. HttpStatus.FOUND (201) is returned if
	 *         the customer record is successfully deleted.
	 * 
	 * @throws NoSuchElementFoundByCustomerException If no customer record is found
	 *                                               for the given ID.
	 */
	public ResponseEntity<ResponseStructure<Customer>> removeCustomerById(int id) {
		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
		Customer customer = custDao.deleteCustomerById(id);
		if (customer != null) {
			structure.setTime(LocalDateTime.now());
			structure.setData(customer);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Customer record is deleted by Id...!");
			return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.FOUND);
		} else {
			throw new NoSuchElementFoundByCustomerException("No any record found for the particular Id: " + id);
		}
	}

	/**
	 * Deletes a customer record identified by the given email address from the
	 * database.
	 *
	 * @param email The unique email address of the customer whose record is to be
	 *              deleted.
	 * @return ResponseEntity containing ResponseStructure<Customer> with details of
	 *         the deleted customer if found. HttpStatus.FOUND (201) is returned if
	 *         the customer record is successfully deleted.
	 * @throws CustomerEmailNotMatchException if no customer record is found with
	 *                                        the provided email address.
	 */
	public ResponseEntity<ResponseStructure<Customer>> removeCustomerByEmail(String email) {
		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
		Customer customer = custDao.deleteCustomerByEmail(email);
		if (customer != null) {
			structure.setTime(LocalDateTime.now());
			structure.setData(customer);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Customer record is deleted by email...!");
			return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.FOUND);
		} else {
			throw new CustomerEmailNotMatchException("Customer given email is wrong...!");
		}
	}

	/**
	 * Updates an existing customer record in the database with new record.
	 *
	 * @param customer The updated customer object containing new data.
	 * @return ResponseEntity containing a ResponseStructure with updated customer
	 *         data, along with status information.
	 * @throws UpdationFailureException if the customer record to be updated does
	 *                                  not exist in the database.
	 */
	public ResponseEntity<ResponseStructure<Customer>> modifyCustomer(Customer customer) {
		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
		Customer custDb = custDao.getCustomerById(customer.getId());
		if (custDb != null) {
			Customer updateData = custDao.updateCustomer(customer);
			structure.setTime(LocalDateTime.now());
			structure.setData(updateData);
			structure.setMessage("Customer record has been updated successfully...!");
			structure.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.CREATED);
		} else {
			throw new UpdationFailureException("Customer Updation cannot be performed with given id: " + customer.getId());
		}
	}

}
