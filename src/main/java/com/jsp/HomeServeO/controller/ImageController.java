package com.jsp.HomeServeO.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.HomeServeO.dao.ImageDao;
import com.jsp.HomeServeO.entity.Customer;
import com.jsp.HomeServeO.entity.Image;
import com.jsp.HomeServeO.entity.Vendor;
import com.jsp.HomeServeO.entity.Work;
import com.jsp.HomeServeO.service.ImageService;
import com.jsp.HomeServeO.util.ResponseStructure;

@RestController
public class ImageController {
	@Autowired
	private ImageService service;

	@Autowired
	private ImageDao imgDao;

	/*
	 * ======================================================================
	 * =======IMAGE UPDATION IN CONTROLLER FOR CUSTOMER, VENDOR, WORK========
	 * ======================================================================
	 */
	/**
	 * Saves an image for a customer using the provided image data.
	 * 
	 * This method handles HTTP POST requests on the "/saveCustomerImageData"
	 * endpoint. It saves the image data associated with a customer identified by
	 * vendId.
	 * 
	 * @param id   The unique ID of the customer for whom the image data is to be
	 *             saved.
	 * @param file containing the image data to be saved.
	 * @return response containing a ResponseStructure wrapping the saved Customer
	 *         object.
	 * @throws IOException if there's an issue with file handling.
	 */
	@PostMapping("/saveCustomerImageData")
	public ResponseEntity<ResponseStructure<Customer>> saveImageForCustomer(@RequestParam int id,
			@RequestParam MultipartFile file) throws IOException {
		ResponseEntity<ResponseStructure<Customer>> response = service.insertCustomerImage(id, file);
		return response;
	}

	/**
	 * Saves an image for a vendor using the provided image data.
	 * 
	 * This method handles HTTP POST requests on the "/saveVendorImageData"
	 * endpoint. It saves the image data associated with a vendor identified by
	 * vendId.
	 * 
	 * @param id   The unique ID of the vendor for whom the image data is to be
	 *             saved.
	 * @param file containing the image data to be saved.
	 * @return response containing a ResponseStructure wrapping the saved Vendor
	 *         object.
	 * @throws IOException if there's an issue with file handling.
	 */
	@PostMapping("/saveVendorImageData")
	public ResponseEntity<ResponseStructure<Vendor>> saveImageForVendor(@RequestParam int id,
			@RequestParam MultipartFile file) throws IOException {
		ResponseEntity<ResponseStructure<Vendor>> response = service.insertVendorImage(id, file);
		return response;
	}

	/**
	 * Saves an image for a work using the provided image data.
	 * 
	 * This method handles HTTP POST requests on the "/saveWorkImageData" endpoint.
	 * It saves the image data associated with a work identified by workId.
	 * 
	 * @param id   The unique ID of the Work for whom the image data is to be saved.
	 * @param file containing the image data to be saved.
	 * @return response containing a ResponseStructure wrapping the saved Work
	 *         object.
	 * @throws IOException if there's an issue with file handling.
	 */
	@PostMapping("/saveWorkImageData")
	public ResponseEntity<ResponseStructure<Work>> saveImageForWork(@RequestParam int id,
			@RequestParam MultipartFile file) throws IOException {
		ResponseEntity<ResponseStructure<Work>> response = service.insertWorkImage(id, file);
		return response;
	}

	/*
	 * ======================================================================
	 * ================IMAGE DELETING IN CONTROLLER FOR ALL==================
	 * ======================================================================
	 */
	/**
	 * Deletes image data associated with a customer or vendor or work entity based
	 * on the provided image ID.
	 *
	 * This method handles HTTP DELETE requests on the "/deleteImageData" endpoint.
	 * It removes the image data associated with the customer or vendor or work
	 * entity identified by the specified image ID from the data source.
	 *
	 * @param id The unique ID of the image data is to be deleted.
	 * @return ResponseEntity containing a ResponseStructure indicating the outcome
	 *         of the deletion operation. The ResponseStructure encapsulates details
	 *         about the success or failure of the operation, including appropriate
	 *         status messages and any relevant data.
	 *
	 *         Possible responses: - 200 OK: The image data was successfully
	 *         deleted. - 404 Not Found: No image data was found with the provided
	 *         ID. - 500 Internal Server Error: An error occurred during the
	 *         deletion process.
	 */
	@DeleteMapping("/deleteImageData")
	public ResponseEntity<ResponseStructure<Image>> deleteImageForCustomer(@RequestParam int id) {
		ResponseEntity<ResponseStructure<Image>> response = service.removeImageById(id);
		return response;
	}

	/*
	 * ======================================================================
	 * ================IMAGE FETCHING IN CONTROLLER FOR ALL==================
	 * ======================================================================
	 */
	/**
	 * Fetches image data from the Image table based on the provided Image ID.
	 * 
	 * This method handles HTTP GET requests on the "/fetchImageDataById" endpoint.
	 * It retrieves the binary data (byte array) of the image identified by the
	 * specified ID from the data source and returns it as a ResponseEntity
	 * containing the image data and appropriate HTTP headers.
	 * 
	 * @param id The unique identifier of the Image whose data is to be fetched.
	 * @return rseponse containing a byte array representing the fetched Image data
	 *         and appropriate HTTP headers.
	 */
	@GetMapping("/fetchImageDataById")
	public ResponseEntity<byte[]> fetchImageById(@RequestParam int id) {
		byte[] imageByte = imgDao.getImageById(id).getData();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(imageByte, headers, HttpStatus.FOUND);
	}

	/*
	 * ======================================================================
	 * =================IMAGE UPDATION IN CONTROLLER FOR ALL=================
	 * ======================================================================
	 */
	/**
	 * Updates image data by modifying the Image table with new data/content from a
	 * MultipartFile.
	 * 
	 * This method handles HTTP PUT requests on the "/updateImageData" endpoint. It
	 * updates the image identified by the provided ID with new content from the
	 * MultipartFile file.
	 * 
	 * @param id   The unique ID of the image to be updated.
	 * @param file containing the updated image data.
	 * @return response containing a ResponseStructure with the updated Image
	 *         object.
	 * @throws IOException if there's an issue with file handling.
	 */
	@PutMapping("/updateImageData")
	public ResponseEntity<ResponseStructure<Image>> updateImage(@RequestParam int id, @RequestParam MultipartFile file)
			throws IOException {
		ResponseEntity<ResponseStructure<Image>> response = service.modifyImage(id, file);
		return response;
	}

}
