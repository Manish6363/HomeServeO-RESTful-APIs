package com.jsp.HomeServeO.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Component
public class ServiceCost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String mode;
	private double totalAmount;
	private int days;
}
