package com.jsp.HomeServeO.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.HomeServeO.config.MailSenderForLiveProject;
import com.jsp.HomeServeO.dao.VendorDao;
import com.jsp.HomeServeO.entity.Vendor;
import com.jsp.HomeServeO.exception.CustomerEmailNotMatchException;
import com.jsp.HomeServeO.exception.NoSuchElementFoundByVendorException;
import com.jsp.HomeServeO.exception.NoSuchListOfElementFoundByVendorException;
import com.jsp.HomeServeO.exception.UpdationFailureException;
import com.jsp.HomeServeO.exception.VendorEmailNotMatchException;
import com.jsp.HomeServeO.exception.VendorPasswordNotMatchException;
import com.jsp.HomeServeO.util.ResponseStructure;

import jakarta.mail.MessagingException;

@Service
public class VendorService {
	@Autowired
	private VendorDao vendDao;

	@Autowired
	private MailSenderForLiveProject sendMail;

	/**
	 * Saves a new Vendor record.
	 *
	 * @param vendor The vendor object to be saved. This object should contain all
	 *               the necessary fields for creating a new vendor record. Refer to
	 *               the {@link Vendor} class documentation for details on the
	 *               expected data format.
	 * @return ResponseEntity containing a {@link ResponseStructure} with
	 *         information about the saved vendor. - If successful, returns HTTP
	 *         status 201 (CREATED) with a ResponseStructure. If unsuccessful,
	 *         usually returns other HTTP status codes indicating the error. -
	 *         Common errors might include: - 400 (BAD_REQUEST): Provided vendor
	 *         object fails validation. - 500 (INTERNAL_SERVER_ERROR): Unexpected
	 *         error during data persistence.
	 * @throws RuntimeException if an unexpected error occurs during the save
	 *                          operation.
	 */
	public ResponseEntity<ResponseStructure<Vendor>> saveVendor(Vendor vendor) {
		ResponseStructure<Vendor> structure = new ResponseStructure<Vendor>();
		Vendor v = vendDao.saveVendor(vendor);
		try {
			sendMail.sendMail(v.getEmail(), v);
		} catch (MessagingException e) {
			System.out.println("OTP for Vendor cannot be sent, Try Again...!");
			e.printStackTrace();
		}
		structure.setTime(LocalDateTime.now());
		structure.setData(v);
		structure.setMessage("Vendor record has been saved successfully...!");
		structure.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Vendor>>(structure, HttpStatus.CREATED);
	}

	/**
	 * This method is used to generate a random 6-digits of Integer code which is
	 * sent to the registered user email id.
	 * 
	 * @param email The email associated with the Vendor's account where OTP is to
	 *              sent.
	 * @return response containing a ResponseStructure with details of the vendor
	 *         upon successful OTP generation.
	 */
	public ResponseEntity<ResponseStructure<Integer>> generateOPT(String email) {
		ResponseStructure<Integer> structure = new ResponseStructure<Integer>();
		Vendor vendDb = vendDao.getVendorByEmail(email);
		if (vendDb != null) {
			int randomOTP = new Random().nextInt(999999);
			structure.setTime(LocalDateTime.now());
			structure.setData(randomOTP);
			try {
				sendMail.generateOTPCode(email, vendDb, randomOTP);
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
	 * Retrieves a vendor record from the database based on the given ID.
	 *
	 * @param id The unique identifier of the vendor to retrieve.
	 * @return ResponseEntity containing a ResponseStructure with details of the
	 *         vendor if found. If no vendor is found with the given ID, a
	 *         NoSuchElementFoundByVendorException is thrown.
	 * @throws NoSuchElementFoundByVendorException If no Vendor record is found for
	 *                                             the specified ID.
	 */
	public ResponseEntity<ResponseStructure<Vendor>> getVendorById(int id) {
		Vendor db = vendDao.getVendorById(id);
		ResponseStructure<Vendor> structure = new ResponseStructure<Vendor>();
		if (db != null) {
			structure.setTime(LocalDateTime.now());
			structure.setData(db);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Vendor record is found by id...!");
			return new ResponseEntity<ResponseStructure<Vendor>>(structure, HttpStatus.FOUND);
		} else {
			throw new NoSuchElementFoundByVendorException("There is no any recoed with Given Vendor id: " + id);
		}
	}

	/**
	 * Retrieves all vendor records from the database.
	 *
	 * @return ResponseEntity containing ResponseStructure with list of Vendor
	 *         objects.
	 * @throws NoSuchListOfElementFoundByVendorException if no vendor records are
	 *                                                   found
	 */
	public ResponseEntity<ResponseStructure<List<Vendor>>> getAllVendor() {
		ResponseStructure<List<Vendor>> structure = new ResponseStructure<List<Vendor>>();
		List<Vendor> custDb = vendDao.getAllVendor();
		if (custDb != null) {
			structure.setTime(LocalDateTime.now());
			structure.setData(custDb);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("All Vendor record is found...!");
			return new ResponseEntity<ResponseStructure<List<Vendor>>>(structure, HttpStatus.FOUND);
		} else {
			throw new NoSuchListOfElementFoundByVendorException("No any list of record found...!");
		}
	}

	/**
	 * Logs in a vendor with the provided email and password. Retrieves a vendor
	 * record from the database based on the given email. And validate the
	 * credentials for log-in.
	 *
	 * @param email    The unique email address of the vendor attempting to log in.
	 * @param password The password associated with the vendor's account.
	 * @return A ResponseEntity containing a ResponseStructure with details of the
	 *         vendor upon successful login.
	 * @throws VendorPasswordNotMatchException If the provided password does not
	 *                                         match the password stored for the
	 *                                         vendor.
	 * @throws VendorEmailNotMatchException    If no vendor exists with the provided
	 *                                         email.
	 */
	public ResponseEntity<ResponseStructure<Vendor>> vendorLogin(String email, String password) {
		ResponseStructure<Vendor> structure = new ResponseStructure<Vendor>();
		Vendor vendor = vendDao.getVendorByEmail(email);
		if (vendor != null) {
			if (vendor.getPassword().equals(password)) {
				structure.setTime(LocalDateTime.now());
				structure.setData(vendor);
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setMessage("Vendor login is successfull...!");
				return new ResponseEntity<ResponseStructure<Vendor>>(structure, HttpStatus.FOUND);
			} else {
				throw new VendorPasswordNotMatchException("Vendor given Password is incorrect: " + password);
			}
		} else {
			throw new VendorEmailNotMatchException("Vendor given email is wrong...!");
		}
	}

	/**
	 * Deletes a vendor record from the database by the specified ID.
	 *
	 * @param id The unique ID of the vendor record to delete.
	 * @return ResponseEntity containing ResponseStructure<Vendor> with details of
	 *         the deleted vendor if found. HttpStatus.FOUND (201) is returned if
	 *         the vendor record is successfully deleted.
	 * 
	 * @throws NoSuchElementFoundByVendorrException If no vendor record is found for
	 *                                              the given ID.
	 */
	public ResponseEntity<ResponseStructure<Vendor>> removeVendorById(int id) {
		ResponseStructure<Vendor> structure = new ResponseStructure<Vendor>();
		Vendor vendor = vendDao.deleteVendorById(id);
		if (vendor != null) {
			structure.setTime(LocalDateTime.now());
			structure.setData(vendor);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Vendor record is deleted by Id...!");
			return new ResponseEntity<ResponseStructure<Vendor>>(structure, HttpStatus.FOUND);
		} else {
			throw new NoSuchElementFoundByVendorException("No any record found for the particular Id: " + id);
		}
	}

	/**
	 * Deletes a vendor record identified by the given email address from the
	 * database.
	 *
	 * @param email The unique email address of the vendor whose record is to be
	 *              deleted.
	 * @return ResponseEntity containing ResponseStructure<Vendor> with details of
	 *         the deleted vendor if found. HttpStatus.FOUND (201) is returned if
	 *         the vendor record is successfully deleted.
	 * @throws VendorEmailNotMatchException if no vendor record is found with the
	 *                                      provided email address.
	 */
	public ResponseEntity<ResponseStructure<Vendor>> removeVendorByEmail(String email) {
		ResponseStructure<Vendor> structure = new ResponseStructure<Vendor>();
		Vendor vendor = vendDao.deleteVendorByEmail(email);
		if (vendor != null) {
			structure.setTime(LocalDateTime.now());
			structure.setData(vendor);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Vendor record is deleted by email...!");
			return new ResponseEntity<ResponseStructure<Vendor>>(structure, HttpStatus.FOUND);
		} else {
			throw new VendorEmailNotMatchException("Vendor given email is wrong...!");
		}
	}

	/**
	 * Updates an existing vendor record in the database with new record.
	 *
	 * @param vendor The updated vendor object containing new data.
	 * @return ResponseEntity containing a ResponseStructure with updated vendor
	 *         data, along with status information.
	 * @throws UpdationFailureException if the vendor record to be updated does not
	 *                                  exist in the database.
	 */
	public ResponseEntity<ResponseStructure<Vendor>> modifyVendor(Vendor vendor) {
		ResponseStructure<Vendor> structure = new ResponseStructure<Vendor>();
		Vendor vendDb = vendDao.getVendorById(vendor.getId());
		if (vendDb != null) {
			Vendor updateData = vendDao.updateVendor(vendor);
			structure.setTime(LocalDateTime.now());
			structure.setData(updateData);
			structure.setMessage("Vendor record has been updated successfully...!");
			structure.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Vendor>>(structure, HttpStatus.CREATED);
		} else {
			throw new UpdationFailureException("Vendor Updation cannot be performed with given id: " + vendor.getId());
		}
	}

}
