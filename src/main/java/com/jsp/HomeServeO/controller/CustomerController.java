package com.jsp.HomeServeO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.HomeServeO.entity.Customer;
import com.jsp.HomeServeO.service.CustomerService;
import com.jsp.HomeServeO.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE,
		RequestMethod.PUT })
public class CustomerController {
	@Autowired
	private CustomerService service;

	/**
	 * Handles a POST request or URL to save customer data into the Customer table.
	 *
	 * This method maps HTTP POST requests sent to "/saveCustomerData" and processes
	 * the insertion of customer information into the database.
	 *
	 * @param customer The Customer object containing data to be saved.
	 * @return response containing the response structure with the saved Customer
	 *         object and HTTP status code.
	 */
	@PostMapping("/saveCustomerData")
	public ResponseEntity<ResponseStructure<Customer>> insertCustomer(@RequestBody Customer customer) {
		ResponseEntity<ResponseStructure<Customer>> response = service.saveCustomer(customer);
		return response;
	}

	/**
	 * Fetches customer data from the Customer table based on the provided customer
	 * ID.
	 * 
	 * This method retrieves customer information identified by the specified ID
	 * from the data source and returns it encapsulated within a structured
	 * response.
	 * 
	 * @param id The unique identifier of the customer whose data is to be fetched.
	 * @return response containing a ResponseStructure wrapping the Customer data
	 *         fetched from the database.
	 */
	@GetMapping("/fetchCustomerDataById")
	public ResponseEntity<ResponseStructure<Customer>> fetchCustomerById(@RequestParam int id) {
		ResponseEntity<ResponseStructure<Customer>> response = service.getCustomerById(id);
		return response;
	}

	/**
	 * Retrieves all customer data from the Customer table. This method maps to the
	 * "/fetchAllCustomerData" end point to fetch all customer records.
	 * 
	 * @return response containing ResponseStructure with a list of Customer
	 *         objects. Returns HTTP status codes as per the operation status.
	 */
	@GetMapping("/fetchAllCustomerData")
	public ResponseEntity<ResponseStructure<List<Customer>>> fetchAllCustomer() {
		ResponseEntity<ResponseStructure<List<Customer>>> response = service.getAllCustomer();
		return response;
	}

	/**
	 * End-point to perform customer login using email and password. This method
	 * maps the GET request to '/loginEmailPassword' and attempts to authenticate
	 * the customer based on provided credentials.
	 * 
	 * @param email    The unique email address of the customer.
	 * @param password The password associated with the customer's account.
	 * @return response containing a ResponseStructure with customer details upon
	 *         successful login. Returns appropriate HTTP status codes for success
	 *         or failure scenarios.
	 */
	@GetMapping("/customerLoginEmailPassword")
	public ResponseEntity<ResponseStructure<Customer>> customerLogin(@RequestParam String email,
			@RequestParam String password) {
		ResponseEntity<ResponseStructure<Customer>> response = service.customerLogin(email, password);
		return response;
	}

	/**
	 * Initiates the generation of a one-time password (OTP) for a customer. This
	 * method maps a GET request to '/customerOTPGeneration' endpoint.
	 *
	 * @param email The email address of the customer for OTP generation.
	 * @return response containing a ResponseStructure with an Integer data field
	 *         representing the generated OTP.
	 */
	@GetMapping("/customerOTPGeneration")
	public ResponseEntity<ResponseStructure<Integer>> generateOTP(@RequestParam String email) {
		ResponseEntity<ResponseStructure<Integer>> response = service.generateOTP(email);
		return response;
	}

	/**
	 * Deletes a customer from the Customer table based on the provided ID.
	 * 
	 * This is mapped to handle DELETE requests to '/deleteCustomerDataById'. It
	 * deletes the customer data identified by the given ID.
	 * 
	 * @param id The unique identifier of the customer to be deleted.
	 * @return response containing a ResponseStructure object encapsulating the
	 *         result of the deletion operation. The ResponseStructure holds a
	 *         Customer object and includes metadata about the operation status.
	 */
	@DeleteMapping("/deleteCustomerDataById")
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomerById(@RequestParam int id) {
		ResponseEntity<ResponseStructure<Customer>> response = service.removeCustomerById(id);
		return response;
	}

	/**
	 * Deletes customer data from the Customer table based on the provided email
	 * address.
	 * 
	 * This method handles HTTP DELETE requests at "/deleteCustomerDataByEmail"
	 * request, where it deletes the customer record associated with the given email
	 * address.
	 * 
	 * @param email The unique email address of the customer whose data is to be
	 *              deleted.
	 * @return response containing a ResponseStructure object with details about the
	 *         operation's success or failure, along with any associated Customer
	 *         object.
	 */
	@DeleteMapping("/deleteCustomerDataByEmail")
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomerByEmail(@RequestParam String email) {
		ResponseEntity<ResponseStructure<Customer>> response = service.removeCustomerByEmail(email);
		return response;
	}

	/**
	 * Used to update customer data by mapping the request to save or insert data
	 * into the Customer table.
	 * 
	 * @param customer The Customer object containing updated data.
	 * @return response containing a ResponseStructure with the updated Customer
	 *         object.
	 */
	@PutMapping("/updateCustomerData")
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestBody Customer customer) {
		ResponseEntity<ResponseStructure<Customer>> response = service.modifyCustomer(customer);
		return response;
	}

}
