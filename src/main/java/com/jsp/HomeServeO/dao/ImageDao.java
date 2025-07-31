package com.jsp.HomeServeO.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.HomeServeO.entity.Customer;
import com.jsp.HomeServeO.entity.Image;
import com.jsp.HomeServeO.entity.Vendor;
import com.jsp.HomeServeO.entity.Work;
import com.jsp.HomeServeO.repo.ImageRepo;

@Repository
public class ImageDao {
	@Autowired
	private ImageRepo imgRepo;

	@Autowired
	private CustomerDao custDao;

	@Autowired
	private VendorDao vendDao;

	@Autowired
	private WorkDao workDao;

	/**
	 * This method takes input as Image type object reference to store/save the data
	 * into the Image Entity Table with the help of an abstract non-static save()
	 * method of JpaRepository interface.
	 * 
	 * @param image
	 * @return the Image type of {@img} reference to the method.
	 */
	public Image saveImage(Image image) {
		Image img = imgRepo.save(image);
		if (img != null)
			return img;
		return null;
	}

	/**
	 * This method is used to find/fetch the details of a single Image by using
	 * image id with the help of abstract non-static findById() method which returns
	 * Optional tpye of {@opt} Generic class. Now with the help of {@opt} reference
	 * we will call the non-static get() method of Optional class to change/convert
	 * into generic type and return the generic class reference.
	 * 
	 * @param id
	 * @return {@Image} If the record is present into the database.
	 * @return null, If {@Image} is null that means the record is not present into
	 *         the database.
	 */
	public Image getImageById(int id) {
		Optional<Image> opt = imgRepo.findById(id);
		if (opt.isPresent()) {
			Image image = opt.get();

			return image;
		}
		return null;
	}

	/**
	 * Removes an image from the database using the provided image ID.
	 * 
	 * This method retrieves the image by its unique identifier and removes it from
	 * associated entities (Customer, Vendor, Work) if they exist. It then deletes
	 * the image from the repository and returns the deleted image object.
	 *
	 * @param id The unique identifier of the Image object to be deleted.
	 * @return The deleted Image object, or null if no image with the given ID is
	 *         found.
	 */
	public Image removeImage(int id) {
		Image image = imgRepo.findById(id).get();
		if (image != null) {
			Optional<Customer> customer = imgRepo.deleteImageFromCustomer(image);
			if (customer.isPresent()) {
				customer.get().setImage(null);
				custDao.updateCustomer(customer.get());
			}
			Optional<Vendor> vendor = imgRepo.deleteImageFromVendor(image);
			if (vendor.isPresent()) {
				vendor.get().setImage(null);
				vendDao.updateVendor(vendor.get());
			}
			Optional<Work> work = imgRepo.deleteImageFromWork(image);
			if (work.isPresent()) {
				work.get().setImage(null);
				workDao.updateWork(work.get());
			}
			imgRepo.delete(image);
			return image;
		}
		return null;
	}

	/**
	 * Updates an existing image record in the database based on the provided Image
	 * object.
	 * 
	 * This method retrieves the existing image record from the database using the
	 * Image ID from the provided Image object. It validates and updates the record
	 * with new data if any fields are non-null. Finally, it saves the updated image
	 * record back to the database.
	 * 
	 * @param image The Image object containing updated data.
	 * @return The updated Image object after saving to the database, or null if no
	 *         record was found.
	 */
	public Image updateImage(Image image) {
		Image imgDb = imgRepo.findById(image.getId()).get();
		if (imgDb != null) {
			if (image.getName() != null) {
				imgDb.setName(image.getName());
			}
			if (image.getData() != null) {
				imgDb.setData(image.getData());
			}
			Image pic = imgRepo.save(imgDb);
			return pic;
		}
		return null;
	}
}
