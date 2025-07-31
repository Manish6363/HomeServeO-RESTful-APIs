package com.jsp.HomeServeO.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.HomeServeO.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	/**
	 * Retrieves a customer by their email address.
	 *
	 * @param email The email address of the customer to retrieve.
	 * @return The Customer object with the specified email address, or {@code null}
	 *         if none exists.
	 */
	@Query("select c from Customer c where email=?1")
	public Customer findByEmail(String email);
}
