package com.jsp.HomeServeO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.HomeServeO.entity.Work;
import com.jsp.HomeServeO.service.WorkService;
import com.jsp.HomeServeO.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE,
		RequestMethod.PUT })
public class WorkController {
	@Autowired
	private WorkService service;

	/**
	 * Handles a POST request to save Work data into the database.
	 *
	 * This method maps HTTP POST requests sent to "/saveWorkData" and processes the
	 * insertion of Work information into the Work table associated with a specific
	 * customer.
	 *
	 * @param work   The Work object containing data to be saved.
	 * @param custId The ID of the customer associated with the Work data.
	 * @return response containing the response structure with the saved Work object
	 *         and HTTP status code 201 (Created).
	 */
	@PostMapping("/saveWorkData")
	public ResponseEntity<ResponseStructure<Work>> saveWork(@RequestBody Work work, @RequestParam int custId) {
		ResponseEntity<ResponseStructure<Work>> response = service.saveWork(work, custId);
		return response;
	}

	/**
	 * Fetches Work data from the Work table based on the provided Work ID.
	 * 
	 * This method retrieves Work information identified by the specified ID from
	 * the data source and returns it encapsulated within a structured response.
	 * 
	 * @param id The unique identifier of the Work whose data is to be fetched.
	 * @return response containing a ResponseStructure wrapping the Work data
	 *         fetched from the database.
	 */
	@GetMapping("/fetchWorkDataById")
	public ResponseEntity<ResponseStructure<Work>> fetchWorkById(@RequestParam int id) {
		ResponseEntity<ResponseStructure<Work>> response = service.getWorkById(id);
		return response;
	}

	/**
	 * Handles a PUT request to start work for a specific vendor on a given work
	 * item.
	 *
	 * This method maps HTTP PUT requests sent to "/startWorkDate" and initiates the
	 * work process for the specified work item identified by `workId` with the
	 * vendor identified by `vendId`.
	 *
	 * @param workId The ID of the work item to start work on.
	 * @param vendId The ID of the vendor assigned to perform the work.
	 * @return response containing the response structure with the updated Work
	 *         object and appropriate HTTP status code.
	 */
	@PutMapping("/startWorkDate")
	public ResponseEntity<ResponseStructure<Work>> startWork(@RequestParam int workId, @RequestParam int vendId) {
		return service.beginWorkDate(workId, vendId);
	}

	/**
	 * Handles a PUT request to end work for a specific vendor on a given work item.
	 *
	 * This method maps HTTP PUT requests sent to "/endWorkDate" and marks the
	 * completion of work for the specified work item identified by `workId` with
	 * the vendor identified by `vendId`.
	 *
	 * @param workId The ID of the work item to end work on.
	 * @param vendId The ID of the vendor who completed the work.
	 * @return response containing the response structure with the updated Work
	 *         object and appropriate HTTP status code.
	 */
	@PutMapping("/endWorkDate")
	public ResponseEntity<ResponseStructure<Work>> endWork(@RequestParam int workId, @RequestParam int vendId) {
		return service.finishedWorkDate(workId, vendId);
	}

	/**
	 * Retrieves a list of unmapped Work records from the database.
	 *
	 * This method maps HTTP GET requests sent to "/fetchListOfUnMappedWorkData" to
	 * fetch all Work items that are unmapped by a vendor or have not been started.
	 *
	 * @param id The ID parameter used to filter unmapped Work items.
	 * @return response containing the response structure with a list of Work
	 *         objects and appropriate HTTP status code.
	 */
	@GetMapping("/fetchListOfUnMappedWorkData")
	public ResponseEntity<ResponseStructure<List<Work>>> fetchListOfUnMappedWorks(int id) {
		ResponseEntity<ResponseStructure<List<Work>>> response = service.getListOfUnMappedWorks(id);
		return response;
	}

	/**
	 * Retrieves a list of ongoing Work records from the database.
	 *
	 * This method maps HTTP GET requests sent to "/fetchListOfOnGoingWorkData" to
	 * fetch all ongoing Work records associated with the specified ID.
	 *
	 * @param id The ID parameter used to filter ongoing Work records.
	 * @return response containing the response structure with a list of Work
	 *         objects and appropriate HTTP status code.
	 */
	@GetMapping("/fetchListOfOnGoingWorkData")
	public ResponseEntity<ResponseStructure<List<Work>>> fetchListOfOnGoingWork(int id) {
		ResponseEntity<ResponseStructure<List<Work>>> response = service.getListOfOnGoingWork(id);
		return response;
	}

	/**
	 * Retrieves a list of completed Work records from the database.
	 *
	 * This method maps HTTP GET requests sent to "/fetchListOfCompletedWorkData" to
	 * fetch all completed Work records associated with the specified ID.
	 *
	 * @param id The ID parameter used to filter completed Work records.
	 * @return response containing the response structure with a list of Work
	 *         objects and appropriate HTTP status code.
	 */
	@GetMapping("/fetchListOfCompletedWorkData")
	public ResponseEntity<ResponseStructure<List<Work>>> fetchListOfCompletedWork(int id) {
		ResponseEntity<ResponseStructure<List<Work>>> response = service.getListOfCompletedWorks(id);
		return response;
	}

	/**
	 * Used to update Work data by mapping the request to save or insert data into
	 * the Work table.
	 * 
	 * @param work The work object containing updated data.
	 * @return response containing a ResponseStructure with the updated Work object.
	 */
	@PostMapping("/updateWorkData")
	public ResponseEntity<ResponseStructure<Work>> updateWork(@RequestBody Work work) {
		ResponseEntity<ResponseStructure<Work>> response = service.modifyWork(work);
		return response;
	}

}
