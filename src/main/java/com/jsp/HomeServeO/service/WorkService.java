package com.jsp.HomeServeO.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.HomeServeO.dao.CustomerDao;
import com.jsp.HomeServeO.dao.VendorDao;
import com.jsp.HomeServeO.dao.WorkDao;
import com.jsp.HomeServeO.entity.Customer;
import com.jsp.HomeServeO.entity.Vendor;
import com.jsp.HomeServeO.entity.Work;
import com.jsp.HomeServeO.exception.NoSuchElementFoundByCustomerException;
import com.jsp.HomeServeO.exception.NoSuchElementFoundByVendorException;
import com.jsp.HomeServeO.exception.NoSuchElementFoundForWorkException;
import com.jsp.HomeServeO.exception.NoSuchListOfElementFoundByVendorException;
import com.jsp.HomeServeO.exception.NoSuchListOfElementFoundByWorkException;
import com.jsp.HomeServeO.exception.UpdationFailureException;
import com.jsp.HomeServeO.util.ResponseStructure;

@Service
public class WorkService {
	@Autowired
	private WorkDao workDao;

	@Autowired
	private CustomerDao custDao;

	@Autowired
	private VendorDao vendDao;

	/**
	 * Saves a new work record into the database.
	 *
	 * This method handles the creation of a new work record in the database. It
	 * takes a Work object containing all necessary fields for the new record and
	 * associates it with the specified customer identified by custId.
	 *
	 * @param work   The Work object to be saved. It should contain all required
	 *               fields for creating a new work record.
	 * @param custId The ID of the customer to associate with the new work record.
	 * @return response containing ResponseStructure with details of the saved Work.
	 *         If successful, returns HTTP status code 201 (Created) with the saved
	 *         Work data and a success message. If there are errors during saving,
	 *         appropriate error details will be included in the response.
	 * @throws NoSuchElementFoundByCustomerException If the customer with the given
	 *                                               custId does not exist.
	 */
	public ResponseEntity<ResponseStructure<Work>> saveWork(Work work, int custId) {
		ResponseStructure<Work> structure = new ResponseStructure<Work>();
		Customer customer = custDao.getCustomerById(custId);
		if (customer != null) {
			work.setCustomer(customer);
			structure.setTime(LocalDateTime.now());
			structure.setData(workDao.saveWork(work));
			structure.setMessage("Work record has been saved successfully...!");
			structure.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Work>>(structure, HttpStatus.CREATED);
		} else
			throw new NoSuchElementFoundByCustomerException("customer id not found");
	}

	/**
	 * Retrieves the work information identified by the given ID from the database.
	 * Returns a ResponseEntity containing detailed response structure.
	 *
	 * @param id The unique ID of the work to retrieve.
	 * @return ResponseEntity<ResponseStructure<Work>> containing information about
	 *         the retrieved work.
	 * @throws NoSuchElementFoundForWorkException if no work is found with the
	 *                                            specified ID.
	 */
	public ResponseEntity<ResponseStructure<Work>> getWorkById(int id) {
		Work db = workDao.getWorkById(id);
		ResponseStructure<Work> structure = new ResponseStructure<Work>();
		if (db != null) {
			structure.setTime(LocalDateTime.now());
			structure.setData(db);// where db=dao.getWorkById(id)
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Work Id is found...!");
			return new ResponseEntity<ResponseStructure<Work>>(structure, HttpStatus.FOUND);

		} else {
			throw new NoSuchElementFoundForWorkException("There is no any Work found by the given id: " + id);
		}
	}

	/**
	 * Updates the start date of work started for a specific vendor on a given work
	 * item.
	 *
	 * This method updates the start date of the work identified by `workId` and
	 * assigns the vendor identified by `vendId` to the work item.
	 *
	 * @param workId The ID of the work item to begin work on.
	 * @param vendId The ID of the vendor assigned to start the work.
	 * @return response containing ResponseStructure with details of the updated
	 *         Work. If successful, returns HTTP status code 302 (Found) with the
	 *         updated Work data and a success message. If there are errors during
	 *         processing, appropriate error details will be included in the
	 *         response.
	 * @throws NoSuchElementFoundForWorkException  If no work item is found with the
	 *                                             given workId.
	 * @throws NoSuchElementFoundByVendorException If no vendor is found with the
	 *                                             given vendId.
	 */
	public ResponseEntity<ResponseStructure<Work>> beginWorkDate(int workId, int vendId) {
		Vendor vendor = vendDao.getVendorById(vendId);
		if (vendor != null) {
			Work work = workDao.getWorkById(workId);
			if (work != null) {
				List<Vendor> list = new ArrayList<>();
				list.add(vendor);
				Date date = new Date(new java.util.Date().getTime());
				work.setStartDate(date);
				work.setVendors(list);
				ResponseStructure<Work> structure = new ResponseStructure<>();
				structure.setMessage("Work start date updated successfully.");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(workDao.updateWork(work));
				return new ResponseEntity<ResponseStructure<Work>>(structure, HttpStatus.FOUND);
			} else
				throw new NoSuchElementFoundForWorkException("There is no any Work found by the given id: " + workId);
		}
		throw new NoSuchElementFoundByVendorException("There is no any Vendor found by the given id: " + vendId);
	}

	/**
	 * Updates the end date of work completed by a specific vendor on a given work
	 * item.
	 *
	 * This method sets the end date of the work identified by `workId` to the
	 * current date and time, indicating completion by the vendor identified by
	 * `vendId`.
	 *
	 * @param workId The ID of the work item to update the end date for.
	 * @param vendId The ID of the vendor who completed the work.
	 * @return response containing ResponseStructure with details of the updated
	 *         Work. If successful, returns HTTP status code 302 (Found) with the
	 *         updated Work data and a success message. If there are errors during
	 *         processing, appropriate error details will be included in the
	 *         response.
	 * @throws NoSuchElementFoundForWorkException  If no work item is found with the
	 *                                             given workId.
	 * @throws NoSuchElementFoundByVendorException If no vendor is found with the
	 *                                             given vendId.
	 */
	public ResponseEntity<ResponseStructure<Work>> finishedWorkDate(int workId, int vendId) {
		Vendor vendor = vendDao.getVendorById(vendId);
		if (vendor != null) {
			Work work = workDao.getWorkById(workId);
			if (work != null) {
				Date date = new Date(new java.util.Date().getTime());
				work.setEndDate(date);
				ResponseStructure<Work> structure = new ResponseStructure<>();
				structure.setMessage("Work end date updated successfully.");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(workDao.updateWork(work));
				return new ResponseEntity<ResponseStructure<Work>>(structure, HttpStatus.FOUND);
			} else
				throw new NoSuchElementFoundForWorkException("There is no any Work found by the given id: " + workId);
		}
		throw new NoSuchElementFoundByVendorException("There is no any Vendor found by the given id: " + vendId);
	}

	/**
	 * Retrieves all work items that either have not been mapped by a vendor or have
	 * not been started.
	 *
	 * This method fetches a list of work items that are available for mapping by
	 * the vendor identified by `vendId`.
	 *
	 * @param vendId The ID of the vendor for whom to retrieve unmapped work items.
	 * @return response containing ResponseStructure<List<Work>> with details of
	 *         unmapped works if found, HttpStatus.FOUND if successful.
	 * @throws NoSuchListOfElementFoundByVendorException If no unmapped work items
	 *                                                   are found for the given
	 *                                                   vendor.
	 */
	public ResponseEntity<ResponseStructure<List<Work>>> getListOfUnMappedWorks(int vendId) {
		Vendor vendor = vendDao.getVendorById(vendId);
		if (vendor != null) {
			ResponseStructure<List<Work>> structure = new ResponseStructure<List<Work>>();
			List<Work> works = workDao.listOfUnMappedWorks();
			structure.setTime(LocalDateTime.now());
			structure.setMessage("The list of work availble for vendor is :");
			structure.setData(works);
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Work>>>(structure, HttpStatus.FOUND);
		} else {
			throw new NoSuchListOfElementFoundByVendorException(
					"There are no any list of Unmapped Work record is found by given Vendor...!");
		}
	}

	/**
	 * Retrieves all ongoing work records associated with a specific vendor.
	 *
	 * This method queries the database to fetch ongoing work records that are
	 * associated with the vendor identified by `vendId`.
	 *
	 * @param vendId The ID of the vendor for whom to retrieve ongoing work records.
	 * @return response containing a ResponseStructure with the list of ongoing work
	 *         records if found, HttpStatus.FOUND if successful.
	 * @throws NoSuchListOfElementFoundByWorkException If no ongoing work records
	 *                                                 are found in the database.
	 */
	public ResponseEntity<ResponseStructure<List<Work>>> getListOfOnGoingWork(int vendId) {
		Vendor vendor = vendDao.getVendorById(vendId);
		if (vendor != null) {
			ResponseStructure<List<Work>> structure = new ResponseStructure<List<Work>>();
			List<Work> works = workDao.listOfOnGoingWork();
			structure.setTime(LocalDateTime.now());
			structure.setMessage("The list of ongoing work are :");
			structure.setData(works);
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Work>>>(structure, HttpStatus.FOUND);
		} else {
			throw new NoSuchListOfElementFoundByWorkException("There are no any list of ongoing work record found...!");
		}
	}

	/**
	 * Retrieves a list of completed work records associated with a specific vendor.
	 *
	 * This method queries the database to fetch completed work records that are
	 * associated with the vendor identified by `vendId`.
	 *
	 * @param vendId The ID of the vendor for whom to retrieve completed work
	 *               records.
	 * @return response containing a ResponseStructure with the list of completed
	 *         work records if found, HttpStatus.FOUND if successful.
	 * @throws NoSuchListOfElementFoundByWorkException If no completed work records
	 *                                                 are found in the database.
	 */
	public ResponseEntity<ResponseStructure<List<Work>>> getListOfCompletedWorks(int vendId) {
		Vendor vendor = vendDao.getVendorById(vendId);
		if (vendor != null) {
			ResponseStructure<List<Work>> structure = new ResponseStructure<List<Work>>();
			List<Work> works = workDao.listOfCompletedWorks();
			structure.setTime(LocalDateTime.now());
			structure.setMessage("The list of Completed work are :");
			structure.setData(works);
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Work>>>(structure, HttpStatus.FOUND);
		} else {
			throw new NoSuchListOfElementFoundByWorkException(
					"There are no any list of completed work record found...!");
		}
	}

	/**
	 * Updates an existing Work entity in the database.
	 *
	 * This method retrieves the Work entity identified by the provided ID from the
	 * database, attempts to update it with the data provided in the `work`
	 * parameter, and returns a ResponseEntity containing a ResponseStructure object
	 * encapsulating the updated Work entity along with metadata such as status,
	 * message, and TimeStamp.
	 *
	 * @param work The updated Work entity containing new data.
	 * @return response containing a ResponseStructure object with the updated Work
	 *         entity. If successful, returns HTTP status code 302 (Found) with the
	 *         updated Work data and a success message. If there are errors during
	 *         processing, appropriate error details will be included in the
	 *         response.
	 * @throws UpdationFailureException If no Work entity exists with the given ID
	 *                                  in the database, this exception is thrown
	 *                                  indicating the update operation failed.
	 */
	public ResponseEntity<ResponseStructure<Work>> modifyWork(Work work) {
		ResponseStructure<Work> structure = new ResponseStructure<Work>();
		Work workDb = workDao.getWorkById(work.getId());
		if (workDb != null) {
			Work updateData = workDao.updateWork(work);
			structure.setTime(LocalDateTime.now());
			structure.setData(updateData);
			structure.setMessage("Work has been updated successfully...!");
			structure.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Work>>(structure, HttpStatus.CREATED);
		} else {
			throw new UpdationFailureException("Work Updation cannot be performed with given id: " + work.getId());
		}
	}

}
