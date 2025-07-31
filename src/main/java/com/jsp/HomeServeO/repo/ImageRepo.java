package com.jsp.HomeServeO.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.HomeServeO.entity.Customer;
import com.jsp.HomeServeO.entity.Image;
import com.jsp.HomeServeO.entity.Vendor;
import com.jsp.HomeServeO.entity.Work;

public interface ImageRepo extends JpaRepository<Image, Integer> {

	/**
	 * Retrieves a customer whose image matches by the given image.
	 * 
	 * @param image the image to match against customers
	 * @return an Optional containing the customer with the matching image, or an
	 *         empty Optional if no customer has the matching image
	 */
	@Query("select a from Customer a where image=?1")
	Optional<Customer> deleteImageFromCustomer(Image image);

	/**
	 * Retrieves a vendor whose image matches by the given image.
	 * 
	 * @param image the image to match against vendors
	 * @return an Optional containing the vendor with the matching image, or an
	 *         empty Optional if no vendor has the matching image
	 */
	@Query("select a from Vendor a where image=?1")
	Optional<Vendor> deleteImageFromVendor(Image image);

	/**
	 * Retrieves a work whose image matches by the given image.
	 * 
	 * @param image the image to match against works
	 * @return an Optional containing the work with the matching image, or an empty
	 *         Optional if no work has the matching image
	 */
	@Query("select a from Work a where image=?1")
	Optional<Work> deleteImageFromWork(Image image);
}
