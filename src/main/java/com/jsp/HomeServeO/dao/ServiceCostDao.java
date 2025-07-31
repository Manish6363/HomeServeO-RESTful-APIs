package com.jsp.HomeServeO.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.HomeServeO.entity.ServiceCost;
import com.jsp.HomeServeO.repo.ServiceCostRepo;

@Repository
public class ServiceCostDao {
	@Autowired
	private ServiceCostRepo servRepo;

	/**
	 * This method takes input as ServiceCost type object reference to store/save
	 * the data into the ServiceCost Entity Table with the help of an abstract
	 * non-static save() method of JpaRepository interface.
	 * 
	 * @param serviceCost
	 * @return the ServiceCost type of {@serveCost} reference to the method.
	 */
	public ServiceCost saveServiceCost(ServiceCost serviceCost) {
		ServiceCost serveCost = servRepo.save(serviceCost);
		if (serveCost != null)
			return serveCost;
		return null;
	}

	/**
	 * This method is used to pay the ServiceCost by the payment mode selected by an
	 * user which is also updated into the database of ServiceCost Entity Table
	 * simultaneously.
	 * 
	 * @return the ServiceCost type of {@service} reference to the method.
	 */
	public ServiceCost payServiceCost(ServiceCost serviceCost) {
		ServiceCost serveCost = servRepo.findById(serviceCost.getId()).get();
		if (serveCost != null) {
			serveCost.setMode(serviceCost.getMode());
			ServiceCost service = servRepo.save(serveCost);
			return service;
		}
		return null;
	}

	/**
	 * This method is used to get the actual cost for the payment from the database
	 * by using the service id with the help of an abstract non-static method of
	 * JpaRepository interface.
	 * 
	 * @param id
	 * @return the ServiceCost type of {@service} reference to the method.
	 */
	public ServiceCost getServiceCost(int id) {
		ServiceCost serviceCost = servRepo.findById(id).get();
		if (serviceCost != null) {
			return serviceCost;
		}
		return null;
	}

}
