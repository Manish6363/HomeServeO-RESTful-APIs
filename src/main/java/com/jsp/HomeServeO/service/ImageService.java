package com.jsp.HomeServeO.service;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.HomeServeO.dao.CustomerDao;
import com.jsp.HomeServeO.dao.ImageDao;
import com.jsp.HomeServeO.dao.VendorDao;
import com.jsp.HomeServeO.dao.WorkDao;
import com.jsp.HomeServeO.entity.Customer;
import com.jsp.HomeServeO.entity.Image;
import com.jsp.HomeServeO.entity.Vendor;
import com.jsp.HomeServeO.entity.Work;
import com.jsp.HomeServeO.exception.NoSuchElementFoundByImageException;
import com.jsp.HomeServeO.exception.UpdationFailureException;
import com.jsp.HomeServeO.util.ResponseStructure;

@Service
public class ImageService {
	@Autowired
	private ImageDao imgDao;

	@Autowired
	private CustomerDao custDao;

	@Autowired
	private VendorDao vendDao;

	@Autowired
	private WorkDao workDao;

	@Autowired
	private Image image;

	/*
	 * =====================================================================
	 * ========IMAGE UPDATION IN SERVICE FOR CUSTOMER, VENDOR, WORK=========
	 * =====================================================================
	 */
	/**
	 * Saves an image record into the database and associates it with a customer.
	 *
	 * @param custId The unique ID of the customer to whom the image will be
	 *               associated.
	 * @param file   The MultipartFile containing the image data to be saved. This
	 *               file should contain the image data and the original file name.
	 * @return response containing a ResponseStructure with the saved customer data
	 *         including the image, along with status information.
	 * @throws IOException If an error occurs while reading the image data from the
	 *                     MultipartFile.
	 * @throws Exception   If an error occurs while saving the image or updating the
	 *                     customer.
	 */
	public ResponseEntity<ResponseStructure<Customer>> insertCustomerImage(int custId, MultipartFile file)
			throws IOException {
		Customer customer = custDao.getCustomerById(custId);
		if (customer != null) {
			image.setName(file.getOriginalFilename());
			image.setData(file.getBytes());
			customer.setImage(image);
			Customer cust = custDao.updateCustomer(customer);
			ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
			structure.setData(cust);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Image record is Added to the Customer: Mr." + cust.getName());
			return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.CREATED);
		}
		return null;
	}

	/**
	 * Saves an image record into the database and associates it with a Vendor.
	 *
	 * @param vendId The unique ID of the Vendor to whom the image will be
	 *               associated.
	 * @param file   The MultipartFile containing the image data to be saved. This
	 *               file should contain the image data and the original file name.
	 * @return response containing a ResponseStructure with the saved Vendor data
	 *         including the image, along with status information.
	 * @throws IOException If an error occurs while reading the image data from the
	 *                     MultipartFile.
	 * @throws Exception   If an error occurs while saving the image or updating the
	 *                     Vendor.
	 */
	public ResponseEntity<ResponseStructure<Vendor>> insertVendorImage(int vendId, MultipartFile file)
			throws IOException {
		Vendor vendor = vendDao.getVendorById(vendId);
		if (vendor != null) {
			image.setName(file.getOriginalFilename());
			image.setData(file.getBytes());
			vendor.setImage(image);
			Vendor vend = vendDao.updateVendor(vendor);
			ResponseStructure<Vendor> structure = new ResponseStructure<Vendor>();
			structure.setData(vend);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Image record is Added to the Vendor: Mr." + vend.getName());
			return new ResponseEntity<ResponseStructure<Vendor>>(structure, HttpStatus.CREATED);
		}
		return null;
	}

	/**
	 * Saves an image record into the database and associates it with a Work.
	 *
	 * @param workId The unique ID of the Work to whom the image will be associated.
	 * @param file   The MultipartFile containing the image data to be saved. This
	 *               file should contain the image data and the original file name.
	 * @return response containing a ResponseStructure with the saved Work data
	 *         including the image, along with status information.
	 * @throws IOException If an error occurs while reading the image data from the
	 *                     MultipartFile.
	 * @throws Exception   If an error occurs while saving the image or updating the
	 *                     Work.
	 */
	public ResponseEntity<ResponseStructure<Work>> insertWorkImage(int workId, MultipartFile file) throws IOException {
		Work work = workDao.getWorkById(workId);
		if (work != null) {
			image.setName(file.getOriginalFilename());
			image.setData(file.getBytes());
			work.setImage(image);
			Work w = workDao.updateWork(work);
			ResponseStructure<Work> structure = new ResponseStructure<Work>();
			structure.setData(w);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Image record is Added to the Work");
			return new ResponseEntity<ResponseStructure<Work>>(structure, HttpStatus.CREATED);
		}
		return null;
	}

	/*
	 * ==========================================================================
	 * =========IMAGE DELETION IN SERVICE FOR ALL CUSTOMER, VENDOR, WORK=========
	 * ==========================================================================
	 */
	/**
	 * Deletes an existing image record in the database by its ID.
	 *
	 * @param id The unique ID of the image to be deleted.
	 * @return response containing a ResponseStructure with the deleted Image data,
	 *         along with status if the deletion is successful.
	 * @throws NoSuchElementFoundByImageException If the image with the provided ID
	 *                                            does not exist in the database.
	 */
	public ResponseEntity<ResponseStructure<Image>> removeImageById(int id) {
		Image img = imgDao.getImageById(id);
		ResponseStructure<Image> structure = new ResponseStructure<Image>();
		if (img != null) {
			imgDao.removeImage(id);
			structure.setTime(LocalDateTime.now());
			structure.setData(img);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Image record is Deleted by id...!");
			return new ResponseEntity<ResponseStructure<Image>>(structure, HttpStatus.FOUND);
		} else {
			throw new NoSuchElementFoundByImageException("There is no any Image record is found by Id: " + id);
		}
	}

	/*
	 * ==========================================================================
	 * ========IMAGE UPDATION IN SERVICE FOR ALL CUSTOMER, VENDOR, WORK==========
	 * ==========================================================================
	 */
	/**
	 * Updates an existing image record in the database with new data.
	 * 
	 * This method updates an image record identified by the provided ID with new
	 * data from the MultipartFile file. If successful, it returns a ResponseEntity
	 * with HttpStatus.CREATED and a ResponseStructure containing the updated Image
	 * data, status, message, and current time. If no image record exists for the
	 * provided ID, it throws UpdationFailureException.
	 * 
	 * @param id   The ID of the image record to be updated.
	 * @param file containing the new image data and properties.
	 * @return response containing a ResponseStructure with updated Image data.
	 * @throws IOException              if there's an issue with file handling.
	 * @throws UpdationFailureException if no image record is found for the provided
	 *                                  ID.
	 */
	public ResponseEntity<ResponseStructure<Image>> modifyImage(int id, MultipartFile file) throws IOException {
		ResponseStructure<Image> structure = new ResponseStructure<Image>();
		Image imageDb = imgDao.getImageById(id);
		if (imageDb != null) {
			imageDb.setName(file.getOriginalFilename());
			imageDb.setData(file.getBytes());
			Image updateData = imgDao.updateImage(imageDb);
			structure.setTime(LocalDateTime.now());
			structure.setData(updateData);
			structure.setMessage("Image has been updated successfully...!");
			structure.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Image>>(structure, HttpStatus.CREATED);
		} else {
			throw new UpdationFailureException("Cannot be updated by given data...!");
		}
	}

}
