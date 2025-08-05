# HomeServeO – A Home Services Management System

## Technologies Used [Java + Spring-boot-REST APIs + Spring JPA]

**Backend Framework:** Spring Boot (Java)

**HTTP Handling:** Spring Web (REST Controllers) like GET, POST, PUT, PATCH, DELETE, REMOVE, etc.

**Data Handling:** Likely using JPA/Hibernate (based on standard practices)

**Security:** Email OTP for user/vendor verification

### Security Measures
**OTP Verification:** Ensures only genuine users/vendors can register.

**Access Control:** Segregated endpoints ensure clear role-based access.

**CORS Configuration:** Allows secure cross-origin requests from the frontend.

**Spring REST Best Practices:** Used for clear separation of concerns between controllers and services.

**Password Handling (Expected):** While not in the controllers, passwords are expected to be handled securely (e.g., encryption or hashing) in the service layer.

**Response Wrapping:** Unified ResponseStructure<T> for all responses


## Project Overview
HomeServeO is a comprehensive home services management application designed to connect customers with qualified service vendors. The application facilitates the booking, tracking, and management of service requests such as plumbing, electrical work, cleaning, etc., through a well-structured RESTful API system.

The platform supports seamless operations via modularized backend architecture developed using Spring Boot and adheres to clean coding practices and layered architecture.

The application is structured around the following modules:
1. Admin Module (Planned)
2. Customer Module
3. Vendor Module
4. Service & Work Management Module

Sensitive operations such as login and registration are secured via email-based OTP verification. The REST controllers act as secure gateways to the business logic implemented in the service layer.

## Customer Module
#### Registration & OTP Verification
    * Customers register using an email address.
    * OTP is sent to verify email authenticity.
    * Only after successful OTP verification, the customer data is persisted.
    * Email-based authentication ensures uniqueness and prevents fake entries.
#### Login
Customers log in using their registered email and password.

Authentication verifies credentials and returns user details securely.

##### Post-Login Features
**Profile Management:** Customers can update personal information like name, email, contact, etc.

**Address Management:** Multiple addresses can be added, updated, or deleted.

**Service Requesting:** Customers can raise work requests associated with specific services (e.g., electrical, plumbing).

The request is linked to their customer ID and gets stored in the work queue.

**Work Tracking:** Customers can track work statuses: unmapped, ongoing, and completed.

**Image Uploading:** Users can upload reference images (e.g., photos of faulty equipment or problem areas).


## Vendor Module
#### Registration & OTP Verification
    * Vendors register using email and basic details.
    * Email-based OTP is used to verify the vendor’s identity.
    * Post-verification, vendor details are saved in the database.
#### Login
    * Authenticated via email and password.
    * Only verified vendors are allowed access to the system.
##### Post-Login Features
**Profile Management:** Vendors can update their profiles, contact details, and shop information.

**Work Handling:** Vendors can start and complete assigned work.

Each work entry is linked to a vendor ID and customer ID.

Vendor actions update timestamps and statuses within the system.

**Work Tracking:** Vendors can view: Unmapped work (not yet assigned), Ongoing work (assigned and in-progress), Completed work (successfully delivered).


## Service & Work Management Module
#### Service Cost: 
Admin or designated users can define the cost associated with each service.

These include category and sub-category identifiers.

All CRUD operations on service pricing are available via secure endpoints.

#### Work Lifecycle
**Creation:** Initiated by the customer via /saveWorkData.

**Start Work:** Vendor sets a startDate when work is initiated.

**End Work:** Vendor sets an endDate when work is completed.

#### Filtering Work:

View ongoing, completed, and pending work using respective endpoints.

Data is filtered based on either customer or vendor ID.


## Admin Module 
While not yet implemented in the uploaded code, the structure allows for:
    - Admin approval of vendors.
    - Central monitoring of work progress.
    - Manual reassignment of vendors.
    - Service category management.
    - User/vendor account moderation.

