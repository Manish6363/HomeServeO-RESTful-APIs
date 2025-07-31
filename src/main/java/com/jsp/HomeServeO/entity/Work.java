package com.jsp.HomeServeO.entity;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class Work {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String type;
	private Date startDate; // yyyy-mm-dd
	private Date endDate;
	@OneToOne(cascade = jakarta.persistence.CascadeType.ALL)
	private Address address;
	@OneToOne
	private Image image;
	@ManyToMany
	private List<Vendor> vendors;
	@ManyToOne
	@JoinColumn
	private Customer customer;
	@OneToOne
	private ServiceCost serviceCost;
}
