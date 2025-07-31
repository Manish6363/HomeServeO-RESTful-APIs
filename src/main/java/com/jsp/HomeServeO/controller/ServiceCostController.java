package com.jsp.HomeServeO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.HomeServeO.entity.ServiceCost;
import com.jsp.HomeServeO.service.ServiceCostService;
import com.jsp.HomeServeO.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.PUT })
public class ServiceCostController {
	@Autowired
	private ServiceCostService service;

	/**
	 * It handles a POST request to save ServiceCost data into the ServiceCost
	 * table.
	 * 
	 * This method maps HTTP POST requests sent to "/saveServiceCostData" and
	 * processes the insertion of ServiceCost information into the database. It
	 * expects parameters 'vendorId' and 'workId' to be passed as request/URL query.
	 * 
	 * @param vendorId The unique ID of the Vendor associated with the ServiceCost.
	 * @param workId   The unique ID of the Work associated with the ServiceCost.
	 * @return response containing the response structure with the saved ServiceCost
	 *         object and HTTP status code.
	 */
	@PostMapping("/saveServiceCostData")
	public ResponseEntity<ResponseStructure<ServiceCost>> saveServiceCost(@RequestParam int vendorId,
			@RequestParam int workId) {
		ResponseEntity<ResponseStructure<ServiceCost>> response = service.saveServiceCost(vendorId, workId);
		return response;
	}

	/**
	 * Used for initiating a payment for a service. This method process a payment
	 * transaction for a specific customer based on the provided service cost
	 * details.
	 *
	 * @param customerId  The unique ID of the customer initiating the payment.
	 * @param serviceCost The details of the service cost to be paid for.
	 * @return response containing the response structure with the payment result.
	 *         If successful, returns HTTP status 201 OK with payment details in the
	 *         body. If there's an error, returns an appropriate HTTP status with
	 *         error details.
	 */
	@PutMapping("/makePayment")
	public ResponseEntity<ResponseStructure<ServiceCost>> doPayment(@RequestParam int customerId,
			@RequestBody ServiceCost serviceCost) {
		ResponseEntity<ResponseStructure<ServiceCost>> response = service.payment(customerId, serviceCost);
		return response;
	}

}
