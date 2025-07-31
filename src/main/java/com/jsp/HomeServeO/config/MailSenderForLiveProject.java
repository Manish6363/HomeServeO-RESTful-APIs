package com.jsp.HomeServeO.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.jsp.HomeServeO.entity.Customer;
import com.jsp.HomeServeO.entity.Vendor;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Component
public class MailSenderForLiveProject {
	@Autowired
	private JavaMailSender sender;

	public void sendMail(String email) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("mk84661@gmail.com");
		mail.setTo(email);
		mail.setSubject("Dummy Mail Sender");
		mail.setText("This is dummy mail sender through Java Spring Boot Project.");
		sender.send(mail);
	}

	/*
	 * ==========================================================================
	 * =========================MAIL SENDER FOR CUSTOMER=========================
	 * ==========================================================================
	 */
	/**
	 * Sends a registration email to a customer using their provided email address.
	 *
	 * This method constructs and sends a registration email to the specified email
	 * address, including the customer's registration details such as name, email,
	 * password, and address. The email is formatted in HTML to enhance readability
	 * and presentation.
	 *
	 * @param email    The recipient's email address to send the registration email
	 *                 to.
	 * @param customer The Customer object containing registration details such as
	 *                 name, email, password, and address.
	 * @throws AddressException   If there is an issue with the email address
	 *                            format.
	 * @throws MessagingException If there is an issue sending the email.
	 */
	public void sendMail(String email, Customer customer) throws AddressException, MessagingException {
		MimeMessage message = sender.createMimeMessage();
		message.setFrom(new InternetAddress("mk84661@gmail.com"));
		message.setRecipients(MimeMessage.RecipientType.TO, email);
		message.setSubject("HomeServeO Registration Email");
		String htmlContent = "<h3>Dear sir/madam,</h3><br>" + "<p style='text-align: justify;'><b>Mr. "
				+ customer.getName()
				+ "</b>, Thank you for your interest to do registration on <b>HomeServeO</b> portal as a Customer. Welcome you, now you become as a family member of our <b>HomeServeO</b>. We know it takes time and enery to register, so we appreciate you. This registration will help you related to HomeServeO portal through the email.<br> <b>Your Email: <span style='color:blue'>"
				+ customer.getEmail() + "</span><br>Your Password: <span style='color:blue'>" + customer.getPassword()
				+ "</span></b><br>" + customer.getAddress()
				+ " <br><br>You have successfully registered on HomeServeO portal.<br><br><br><i>THANK YOU for Registering...! Have a nice day.</i>";
		message.setContent(htmlContent, "text/html; charset=utf-8");
		sender.send(message);
	}

	/**
	 * Generates and sends a registration email with an OTP code to the specified
	 * customer email.
	 *
	 * This method constructs an HTML email containing an OTP (One Time Password)
	 * and sends it to the provided email address. The email includes the customer's
	 * name and a message about a new sign-in attempt on a new device.
	 *
	 * @param email    The recipient's email address.
	 * @param customer The Customer object containing details about the customer.
	 * @param otp      The One Time Password to be sent to the customer.
	 * @throws AddressException   If the email address is invalid.
	 * @throws MessagingException If there is an error in creating or sending the
	 *                            email.
	 */
	public void generateOTPCode(String email, Customer customer, int otp) throws AddressException, MessagingException {
		MimeMessage message = sender.createMimeMessage();
		message.setFrom(new InternetAddress("mk84661@gmail.com"));
		message.setRecipients(MimeMessage.RecipientType.TO, email);
		message.setSubject("OTP verification for Login");
		String htmlContent = "<h3>Dear sir/madam,</h3><br>" + "<p style='text-align: justify;'><b>Mr. "
				+ customer.getName()
				+ "</b>,<h1 style='text-align: center;'><span style='color:blue'>H</span>ome<span style='color:blue'>S</span>erve<span style='color:blue'>O</span></h1><br><h2 style='color:red; text-align: center;'>A new sign-in on new Device</h2> <br>We noticed a new sign-in to your <b>HomeServeO</b> account on a Android device. If this is you then your sign-in OTP verification code: <b>"
				+ otp
				+ "</b><br>If someone else then you don't need to do anything and don't share this OTP to anyone.<br><br><i>THANK YOU for Sign-in...! Have a nice day.</i>";
		message.setContent(htmlContent, "text/html; charset=utf-8");
		sender.send(message);
	}

	/*
	 * ========================================================================
	 * =========================MAIL SENDER FOR VENDOR=========================
	 * ========================================================================
	 */
	/**
	 * Sends a registration email to a vendor using their provided email address.
	 *
	 * This method constructs and sends a registration email to the specified email
	 * address, including the vendor's registration details such as name, email,
	 * password, and address. The email is formatted in HTML to enhance readability
	 * and presentation.
	 *
	 * @param email  The recipient's email address to send the registration email
	 *               to.
	 * @param vendor The Vendor object containing registration details such as name,
	 *               email, password, and address.
	 * @throws AddressException   If there is an issue with the email address
	 *                            format.
	 * @throws MessagingException If there is an issue sending the email.
	 */
	public void sendMail(String email, Vendor vendor) throws AddressException, MessagingException {
		MimeMessage message = sender.createMimeMessage();
		message.setFrom(new InternetAddress("mk84661@gmail.com"));
		message.setRecipients(MimeMessage.RecipientType.TO, email);
		message.setSubject("HomeServeO Registration Email");
		String htmlContent = "<h3>Dear sir/madam,</h3><br>" + "<p style='text-align: justify;'><b>Mr. "
				+ vendor.getName()
				+ "</b>, Thank you for your interest to do registration on <b>HomeServeO</b> portal as a Vendor. <br>Welcome you, now you become as a family member of our <b>HomeServeO</b>. We know it takes time and enery to register, so we appreciate you. This registration will help you related to HomeServeO portal through the email.<br> <b>Your Email: <span style='color:blue'>"
				+ vendor.getEmail() + "</span><br>Your Password: <span style='color:blue'>" + vendor.getPassword()
				+ "</span></b><br>" + vendor.getAddress()
				+ " <br><br>You have successfully registered on HomeServeO portal.<br><br><br><i>THANK YOU for Registering...! Have a nice day.</i>";
		message.setContent(htmlContent, "text/html; charset=utf-8");
		sender.send(message);
	}

	/**
	 * Generates and sends a registration email with an OTP code to the specified
	 * vendor email.
	 *
	 * This method constructs an HTML email containing an OTP (One Time Password)
	 * and sends it to the provided email address. The email includes the vendor's
	 * name and a message about a new sign-in attempt on a new device.
	 *
	 * @param email  The recipient's email address.
	 * @param vendor The Vendor object containing details about the vendor.
	 * @param otp    The One Time Password to be sent to the vendor.
	 * @throws AddressException   If the email address is invalid.
	 * @throws MessagingException If there is an error in creating or sending the
	 *                            email.
	 */
	public void generateOTPCode(String email, Vendor vendor, int otp) throws AddressException, MessagingException {
		MimeMessage message = sender.createMimeMessage();
		message.setFrom(new InternetAddress("mk84661@gmail.com"));
		message.setRecipients(MimeMessage.RecipientType.TO, email);
		message.setSubject("OTP verification for Login");
		String htmlContent = "<h3>Dear sir/madam,</h3><br>" + "<p style='text-align: justify;'><b>Mr. "
				+ vendor.getName()
				+ "</b>,<h1 style='text-align: center;'><span style='color:blue'>H</span>ome<span style='color:blue'>S</span>erve<span style='color:blue'>O</span></h1><br><h2 style='color:red; text-align: center;'>A new sign-in on new Device</h2> <br>We noticed a new sign-in to your <b>HomeServeO</b> account on a Android device. If this is you then your sign-in OTP verification code: <b>"
				+ otp
				+ "</b><br>If someone else then you don't need to do anything and don't share this OTP to anyone.<br><br><i>THANK YOU for Sign-in...! Have a nice day.</i>";
		message.setContent(htmlContent, "text/html; charset=utf-8");
		sender.send(message);
	}
}
