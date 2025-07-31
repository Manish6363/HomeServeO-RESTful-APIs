package com.jsp.HomeServeO.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.HomeServeO.util.ResponseStructure;

//@ControllerAdvice
@RestControllerAdvice // We are dealing with API so better to use it
public class ExceptionHandlerForPackage1 {
	/*
	 * ==================================================================
	 * ==============UPDATION EXCEPTION HANDLING for ALL=================
	 * ==================================================================
	 */
	@ExceptionHandler(UpdationFailureException.class)
	public ResponseEntity<ResponseStructure<String>> updationFail(UpdationFailureException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Updation cannot be performed, Try Again...!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}

	/*
	 * ==================================================================
	 * ===============ADDRESS RELATED EXCEPTION HANDLING=================
	 * ==================================================================
	 */
	@ExceptionHandler(NoSuchElementFoundForAddressException.class)
	public ResponseEntity<ResponseStructure<String>> addressMismatched(NoSuchElementFoundForAddressException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("There might be address Id is incorrect, Try Again...!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}

	/*
	 * ==================================================================
	 * ==============CUSTOMER RELATED EXCEPTION HANDLING=================
	 * ==================================================================
	 */
	@ExceptionHandler(CustomerPasswordNotMatchException.class)
	public ResponseEntity<ResponseStructure<String>> custPasswordIncorrect(CustomerPasswordNotMatchException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Customer given password is mismatched or incorrect, Try Again...!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerEmailNotMatchException.class)
	public ResponseEntity<ResponseStructure<String>> custEmailIncorrect(CustomerEmailNotMatchException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Customer given email id is mismatched or incorrect, Try Again...!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchElementFoundByCustomerException.class)
	public ResponseEntity<ResponseStructure<String>> custIdIncorrect(NoSuchElementFoundByCustomerException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Customer given Id is mismatched or incorrect, Try Again...!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}

	/*
	 * ================================================================
	 * ==============VENDOR RELATED EXCEPTION HANDLING=================
	 * ================================================================
	 */
	@ExceptionHandler(VendorPasswordNotMatchException.class)
	public ResponseEntity<ResponseStructure<String>> vendPasswordIncorrect(VendorPasswordNotMatchException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Vendor given password is mismatched or incorrect, Try Again...!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(VendorEmailNotMatchException.class)
	public ResponseEntity<ResponseStructure<String>> vendEmailIncorrect(VendorEmailNotMatchException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Vendor given email id is mismatched or incorrect, Try Again...!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchElementFoundByVendorException.class)
	public ResponseEntity<ResponseStructure<String>> vendIdIncorrect(NoSuchElementFoundByVendorException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Vendor given Id is mismatched or incorrect, Try Again...!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}

	/*
	 * ================================================================
	 * ===============WORK RELATED EXCEPTION HANDLING==================
	 * ================================================================
	 */
	@ExceptionHandler(NoSuchListOfElementFoundByWorkException.class)
	public ResponseEntity<ResponseStructure<String>> workUnmapped(NoSuchListOfElementFoundByWorkException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("There might be no any Work record, Try Again...!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchElementFoundForWorkException.class)
	public ResponseEntity<ResponseStructure<String>> workIdIncorrect(NoSuchElementFoundForWorkException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Work given Id is mismatched or incorrect, Try Again...!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}

	/*
	 * ======================================================================
	 * ==============SERVICE COST RELATED EXCEPTION HANDLING=================
	 * ======================================================================
	 */
	@ExceptionHandler(NoSuchElementFoundByServiceCostException.class)
	public ResponseEntity<ResponseStructure<String>> serviceCostFailed(NoSuchElementFoundByServiceCostException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Service Cost cannot be found, Try Again...!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}

	/*
	 * ===============================================================
	 * ==============IMAGE RELATED EXCEPTION HANDLING=================
	 * ===============================================================
	 */
	@ExceptionHandler(NoSuchElementFoundByImageException.class)
	public ResponseEntity<ResponseStructure<String>> imgNotFound(NoSuchElementFoundByImageException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Image data cannot be found, Try Again...!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}

}
