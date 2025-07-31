package com.jsp.HomeServeO.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.HomeServeO.entity.Work;
import com.jsp.HomeServeO.repo.WorkRepo;

@Repository
public class WorkDao {

	@Autowired
	WorkRepo workRepo;

	/**
	 * This method takes input as Work type object reference to store/save the data
	 * into the Work Entity Table with the help of an abstract non-static save()
	 * method of JpaRepository interface.
	 * 
	 * @param work
	 * @return the Work type of {@w} reference to the method.
	 */
	public Work saveWork(Work work) {
		Work w = workRepo.save(work);
		if (w != null)
			return w;
		return null;
	}

	/**
	 * This method is used to find/get all the works which are available in the Work
	 * Entity table with the help of an abstract non-static findAll() method of
	 * JpaRepository interface.
	 * 
	 * @return the List<Work> type of {@workList} reference to the method.
	 */
	public List<Work> getAllWork() {
		List<Work> workList = workRepo.findAll();
		if (workList != null) {
			return workList;
		}
		return null;
	}

	/**
	 * This method is used to find/fetch the details of a single Work by using Work
	 * id with the help of abstract non-static findById() method which returns
	 * Optional tpye of {@opt} Generic class. Now with the help of {@opt} reference
	 * we will call the non-static get() method of Optional class to change/convert
	 * into generic type and return the generic class reference.
	 * 
	 * @param id
	 * @return {@work} If the record is present into the database.
	 * @return null, If {@work} is null that means the record is not present into
	 *         the database.
	 */
	public Work getWorkById(int id) {
		Optional<Work> opt = workRepo.findById(id);
		Work work = opt.get();
		if (work != null)
			return work;
		return null;
	}

	/**
	 * This method is used to fetch/find all those work list which are not mapped by
	 * vender or has not been started.
	 * 
	 * @return the List<Customer> type of {@workList} reference to the method.
	 */
	public List<Work> listOfUnMappedWorks() {
		List<Work> workList = workRepo.unmappedWorkList();
		if (workList != null) {
			return workList;
		}
		return null;
	}

	/**
	 * This method is used to fetch/find all those work list which are on going now.
	 * 
	 * @return the List<Customer> type of {@workList} reference to the method.
	 */
	public List<Work> listOfOnGoingWork() {
		List<Work> workList = workRepo.ongoingWorks();
		if (workList != null) {
			return workList;
		}
		return null;
	}

	/**
	 * This method is used to fetch/find all those work list which has been
	 * completed.
	 * 
	 * @return the List<Customer> type of {@workList} reference to the method.
	 */
	public List<Work> listOfCompletedWorks() {
		List<Work> workList = workRepo.completedWorks();
		if (workList != null) {
			return workList;
		}
		return null;
	}

	/**
	 * This method is used to modify/update the record based on given Work record
	 * object reference. Before updating we have to check the data from database
	 * with the help of work id for validation purpose. If we will get the object
	 * from database then every time we have to check for null value of each data
	 * member, if it is not null then have to update {@workDb} object. And finally
	 * we need to update the database record with the {@workDb} object.
	 * 
	 * @param work
	 * @return the Work type of {@w} reference to the method.
	 */
	public Work updateWork(Work work) {
		Work workDb = workRepo.findById(work.getId()).get();
		if (workDb != null) {
			if (work.getType() != null) {
				workDb.setType(work.getType());
			}
			if (work.getStartDate() != null) {
				workDb.setStartDate(work.getStartDate());
			}
			if (work.getEndDate() != null) {
				workDb.setEndDate(work.getEndDate());
			}
			if (work.getAddress() != null) {
				workDb.setAddress(work.getAddress());
			}
			if (work.getImage() != null) {
				workDb.setImage(work.getImage());
			}
			if (work.getVendors() != null) {
				workDb.setVendors(work.getVendors());
			}
			if (work.getCustomer() != null) {
				workDb.setCustomer(work.getCustomer());
			}
			if (work.getServiceCost() != null) {
				workDb.setServiceCost(work.getServiceCost());
			}

			Work w = workRepo.save(workDb);
			return w;
		}
		return null;
	}

}
