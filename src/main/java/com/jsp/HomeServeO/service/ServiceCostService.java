package com.jsp.HomeServeO.service;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.HomeServeO.dao.CustomerDao;
import com.jsp.HomeServeO.dao.ServiceCostDao;
import com.jsp.HomeServeO.dao.VendorDao;
import com.jsp.HomeServeO.dao.WorkDao;
import com.jsp.HomeServeO.entity.Customer;
import com.jsp.HomeServeO.entity.ServiceCost;
import com.jsp.HomeServeO.entity.Vendor;
import com.jsp.HomeServeO.entity.Work;
import com.jsp.HomeServeO.exception.NoSuchElementFoundByCustomerException;
import com.jsp.HomeServeO.exception.NoSuchElementFoundByServiceCostException;
import com.jsp.HomeServeO.exception.NoSuchElementFoundByVendorException;
import com.jsp.HomeServeO.exception.NoSuchElementFoundForWorkException;
import com.jsp.HomeServeO.util.ResponseStructure;

@Service
public class ServiceCostService {
	@Autowired
	private ServiceCostDao serviceCostDao;

	@Autowired
	private VendorDao vendorDao;

	@Autowired
	private WorkDao workDao;

	@Autowired
	private CustomerDao customerDao;

	public ResponseEntity<ResponseStructure<ServiceCost>> saveServiceCost(int vendorId, int workId) {
		Vendor vendor = vendorDao.getVendorById(vendorId);
		if (vendor != null) {
			Work work = workDao.getWorkById(workId);
			if (work != null) {
				double costPerDay = vendor.getCostPerDay();
				Date start = work.getStartDate();
				Date end = work.getEndDate();
				Duration duration = Duration.between(start.toLocalDate().atStartOfDay(),
						end.toLocalDate().atStartOfDay());
				int days = (int) duration.toDays();
				ServiceCost cost = new ServiceCost();
				cost.setDays(days);
				cost.setTotalAmount(days * costPerDay);
				ServiceCost serviceCost = serviceCostDao.saveServiceCost(cost);
				List<ServiceCost> list = new ArrayList<ServiceCost>();
				list.add(serviceCost);
				list.addAll(vendor.getServiceCosts());
				vendor.setServiceCosts(list);
				vendorDao.updateVendor(vendor);
				work.setServiceCost(serviceCost);
				workDao.updateWork(work);
				ResponseStructure<ServiceCost> structure = new ResponseStructure<ServiceCost>();
				structure.setTime(LocalDateTime.now());
				structure.setData(serviceCost);
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setMessage("Service cost saved successfully.");
				return new ResponseEntity<ResponseStructure<ServiceCost>>(structure, HttpStatus.CREATED);
			} else {
				throw new NoSuchElementFoundForWorkException("Work not found so, service cost cannot be saved...!");
			}
		} else {
			throw new NoSuchElementFoundByVendorException("Vendor not found, so service cost cannot be saved...!");
		}
	}

	public ResponseEntity<ResponseStructure<ServiceCost>> payment(int customerId, ServiceCost serviceCost) {
		Customer customer = customerDao.getCustomerById(customerId);
		if (customer != null) {
			ServiceCost cost = serviceCostDao.getServiceCost(serviceCost.getId());
			if (cost != null) {
				System.out.println(cost.getMode());
				ResponseStructure<ServiceCost> structure = new ResponseStructure<>();
				structure.setTime(LocalDateTime.now());
				structure.setMessage("cost saved successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(serviceCostDao.payServiceCost(serviceCost));
				return new ResponseEntity<ResponseStructure<ServiceCost>>(structure, HttpStatus.CREATED);
			} else {
				throw new NoSuchElementFoundByServiceCostException(
						"There is no Service cost record id found by ID: " + serviceCost.getId());
			}
		} else {
			throw new NoSuchElementFoundByCustomerException();
		}
	}
}
