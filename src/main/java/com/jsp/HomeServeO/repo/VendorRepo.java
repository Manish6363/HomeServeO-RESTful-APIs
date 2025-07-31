package com.jsp.HomeServeO.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.HomeServeO.entity.Vendor;

public interface VendorRepo extends JpaRepository<Vendor, Integer> {
	/**
	 * Retrieves a vendor entity from the database based on the provided email
	 * address.
	 *
	 * @param email The unique email address of the vendor to retrieve.
	 * @return A Vendor object encapsulating the details of the vendor with the
	 *         specified email address, or null if no such vendor exists.
	 */
	@Query("select v from Vendor v where email=?1")
	public Vendor findByEmail(String email);

}
