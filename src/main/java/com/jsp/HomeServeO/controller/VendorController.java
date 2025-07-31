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

import com.jsp.HomeServeO.entity.Vendor;
import com.jsp.HomeServeO.service.VendorService;
import com.jsp.HomeServeO.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*",methods =  {RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})
public class VendorController {
	@Autowired
	private VendorService service;

	/**
	 * Handles a POST request or URL to save Vendor data into the Vendor table. This
	 * method maps HTTP POST requests sent to "/saveVendorData" and process the
	 * insertion of Vendor information into the database.
	 *
	 * @param vendor The vendor object containing data to be saved.
	 * @return response A ResponseEntity containing the response structure with the
	 *         saved Vendor object and HTTP status code.
	 */
	@PostMapping("/saveVendorData")
	public ResponseEntity<ResponseStructure<Vendor>> saveVendor(@RequestBody Vendor vendor) {
		ResponseEntity<ResponseStructure<Vendor>> response = service.saveVendor(vendor);
		return response;
	}

	/**
	 * Initiates the generation of a one-time password (OTP) for a vendor. This
	 * method maps a GET request to '/vendorOTPGeneration' endpoint.
	 *
	 * @param email The email address of the vendor for OTP generation.
	 * @return response containing a ResponseStructure with an Integer data field
	 *         representing the generated OTP.
	 */
	@GetMapping("/vendorOTPGeneration")
	public ResponseEntity<ResponseStructure<Integer>> generateOTP(@RequestBody Vendor vendor) {
		ResponseEntity<ResponseStructure<Integer>> response = service.generateOPT(vendor.getEmail());
		return response;
	}

	/**
	 * Fetches Vendor data from the Vendor table based on the provided Vendor ID.
	 * This method retrieves Vendor information identified by the specified ID from
	 * the data source and returns it encapsulated within a structured response.
	 * 
	 * @param id The unique identifier of the Vendor whose data is to be fetched.
	 * @return response containing a ResponseStructure wrapping the Vendor data
	 *         fetched from the database.
	 */
	@GetMapping("/fetchVendorDataById")
	public ResponseEntity<ResponseStructure<Vendor>> fetchVendorById(@RequestParam int id) {
		ResponseEntity<ResponseStructure<Vendor>> response = service.getVendorById(id);
		return response;
	}

	/**
	 * Retrieves all Vendor data from the Vendor table. This method maps to the
	 * "/fetchAllVendorData" to fetch all Vendor records.
	 * 
	 * @return response containing ResponseStructure with a list of Vendor objects.
	 *         Returns HTTP status codes as per the operation status.
	 */
	@GetMapping("/fetchAllVendorData")
	public ResponseEntity<ResponseStructure<List<Vendor>>> fetchAllVendor() {
		ResponseEntity<ResponseStructure<List<Vendor>>> response = service.getAllVendor();
		return response;
	}

	/**
	 * To perform Vendor login using email and password. This method maps the GET
	 * request to '/loginEmailPassword' and attempts to authenticate the Vendor
	 * based on provided credentials.
	 * 
	 * @param email    The unique email address of the Vendor.
	 * @param password The password associated with the Vendor's account.
	 * @return response containing a ResponseStructure with Vendor details upon
	 *         successful login. Returns appropriate HTTP status codes for success
	 *         or failure scenarios.
	 */
	@GetMapping("/vendorLoginEmailPassword")
	public ResponseEntity<ResponseStructure<Vendor>> vendorLogin(@RequestParam String email,
			@RequestParam String password) {
		ResponseEntity<ResponseStructure<Vendor>> response = service.vendorLogin(email, password);
		return response;
	}

	/**
	 * Deletes a Vendor from the Vendor table based on the provided ID.
	 * 
	 * This is mapped to handle DELETE requests to '/deleteVendorDataById'. It
	 * deletes the Vendor data identified by the given ID.
	 * 
	 * @param id The unique identifier of the Vendor to be deleted.
	 * @return response containing a ResponseStructure object encapsulating the
	 *         result of the deletion operation. The ResponseStructure holds a
	 *         Vendor object and includes metadata about the operation status.
	 */
	@DeleteMapping("/deleteVendorDataById")
	public ResponseEntity<ResponseStructure<Vendor>> deleteVendorById(@RequestParam int id) {
		ResponseEntity<ResponseStructure<Vendor>> response = service.removeVendorById(id);
		return response;
	}

	/**
	 * Deletes Vendor data from the Vendor table based on the provided email
	 * address.
	 * 
	 * This method handles HTTP DELETE requests at "/deleteVendorDataByEmail", where
	 * it deletes the Vendor record associated with the given email address.
	 * 
	 * @param email The unique email address of the Vendor whose data is to be
	 *              deleted.
	 * @return response containing a ResponseStructure object with details about the
	 *         operation's success or failure, along with any associated Vendor
	 *         object.
	 */
	@DeleteMapping("/deleteVendorDataByEmail")
	public ResponseEntity<ResponseStructure<Vendor>> deleteVendorByEmail(@RequestParam String email) {
		ResponseEntity<ResponseStructure<Vendor>> response = service.removeVendorByEmail(email);
		return response;
	}

	/**
	 * Used to update Vendor data by mapping the request to save or insert data into
	 * the Vendor table.
	 * 
	 * @param Vendor The Vendor object containing updated data.
	 * @return response containing a ResponseStructure with the updated Vendor
	 *         object.
	 */
	@PutMapping("/updateVendorData")
	public ResponseEntity<ResponseStructure<Vendor>> updateVendor(@RequestBody Vendor Vendor) {
		ResponseEntity<ResponseStructure<Vendor>> response = service.modifyVendor(Vendor);
		return response;
	}

}
