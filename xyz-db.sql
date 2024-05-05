CREATE DATABASE xyz;
use xyz;

CREATE TABLE Organization (
    org_code VARCHAR(10) PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
	address VARCHAR(500) NOT NULL,
    org_name VARCHAR(100) NOT NULL,
    phone VARCHAR(10) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL
);
CREATE TABLE Country (
    code VARCHAR(10) PRIMARY KEY NOT NULL,
    country VARCHAR(100) NOT NULL
);

CREATE TABLE City (
    code VARCHAR(10) PRIMARY KEY NOT NULL,
    country_code VARCHAR(10) NOT NULL,
    city_name VARCHAR(100),
    FOREIGN KEY (country_code) REFERENCES Country(code)
);
CREATE TABLE Theatre (
    theatre_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    theatre_code VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(500) NOT NULL,
    city_code VARCHAR(10) NOT NULL,
    org_code VARCHAR(10) NOT NULL,
    sitting_capacity INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,
   
    FOREIGN KEY (city_code) REFERENCES City(code),
    FOREIGN KEY (org_code) REFERENCES Organization(org_code),
    CONSTRAINT unique_theatre_code_org_code UNIQUE (theatre_code, org_code)
);
CREATE TABLE language (
    language_code VARCHAR(10) PRIMARY KEY NOT NULL,
    language VARCHAR(100) NOT NULL
);
CREATE TABLE Movie (
    movie_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    title VARCHAR(100) NOT NULL,
    language_code VARCHAR(10) NOT NULL,
    genre VARCHAR(100) NOT NULL,
    duration TIME NOT NULL,
    released_date DATE NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,
    FOREIGN KEY (language_code) REFERENCES language(language_code)
);
CREATE TABLE show_time (
    show_id INT AUTO_INCREMENT PRIMARY KEY,
    theatre_id INT,
    movie_id INT,
    show_start_time TIME,
    show_end_time TIME,
    show_date DATE,
	overridden_sitting_capacity int, 
	ticket_price DECIMAL(10,2),
	is_active varchar(1) default 'Y',
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,
    FOREIGN KEY (theatre_id) REFERENCES Theatre(theatre_id),
    FOREIGN KEY (movie_id) REFERENCES movie(movie_id)
);
CREATE TABLE offer (
    offer_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    offer_code VARCHAR(20),
    description TEXT,
    offer_type VARCHAR(50),
    discount_percentage DECIMAL(5, 2),
    org_code varchar(10),
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,
    FOREIGN KEY (org_code) REFERENCES organization(org_code),
    CONSTRAINT unique_offer_code_org_code UNIQUE (offer_code, org_code)
);
CREATE TABLE city_offer (
    city_offer_id INT AUTO_INCREMENT PRIMARY KEY,
    offer_id INT,
    city_code VARCHAR(10),
    org_code varchar(10),
	is_active VARCHAR(1) DEFAULT 'N' NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,
    FOREIGN KEY (org_code) REFERENCES organization(org_code),
   
    FOREIGN KEY (offer_id) REFERENCES Offer(offer_id),
    FOREIGN KEY (city_code) REFERENCES City(code)
);
CREATE TABLE theatre_offer (
    theatre_offer_id INT AUTO_INCREMENT PRIMARY KEY,
    offer_id INT,
    theatre_id INT,
	is_active VARCHAR(1) DEFAULT 'N' NOT NULL,
    org_code varchar(10),
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,
    FOREIGN KEY (org_code) REFERENCES organization(org_code),
    FOREIGN KEY (offer_id) REFERENCES Offer(offer_id),
    FOREIGN KEY (theatre_id) REFERENCES Theatre(theatre_id)
);
CREATE TABLE show_offer (
    show_offer_id INT AUTO_INCREMENT PRIMARY KEY,
    offer_id INT,
    show_id INT,
    org_code varchar(10),
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,
    FOREIGN KEY (org_code) REFERENCES organization(org_code),
    FOREIGN KEY (offer_id) REFERENCES Offer(offer_id),
    FOREIGN KEY (show_id) REFERENCES show_time(show_id)
);
CREATE TABLE booking (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    show_id INT NOT NULL,
    user_id INT,
    total_tickets INT,
    total_price DECIMAL(10, 2) NOT NULL,
    show_offer_id int ,
	booked_status VARCHAR(50),
    theatre_offer_id int,
    city_offer_id int,
    total_discount int,
    booking_date DATE,
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,
    org_code varchar(10),
    FOREIGN KEY (org_code) REFERENCES organization(org_code),
    FOREIGN KEY (show_id) REFERENCES show_time(show_id),
     FOREIGN KEY (show_offer_id) REFERENCES show_offer(show_offer_id),
    FOREIGN KEY (theatre_offer_id) REFERENCES theatre_offer(theatre_offer_id),
    FOREIGN KEY (city_offer_id) REFERENCES city_offer(city_offer_id)
);
CREATE TABLE Seat (
    seat_id INT AUTO_INCREMENT PRIMARY KEY,
    theatre_id INT NOT NULL,
    show_id INT NOT NULL,
    seat_number VARCHAR(10),
	order_no int ,
    booking_id int ,
    is_booked varchar(1) DEFAULT 'N',
    org_code varchar(10),
	
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,
	FOREIGN KEY (booking_id) REFERENCES booking(booking_id)
    FOREIGN KEY (org_code) REFERENCES organization(org_code),
    CONSTRAINT unique_show_id_theatre_id_org_code UNIQUE (show_id,theatre_id,org_code,seat_number),
    FOREIGN KEY (theatre_id) REFERENCES theatre(theatre_id),
    FOREIGN KEY (show_id) REFERENCES show_time(show_id)
);
CREATE TABLE logic_processor (
    logic_processor_id INT AUTO_INCREMENT PRIMARY KEY,
    logic_type varchar(100) NOT NULL, 
    logic_execution_listener varchar(100) NOT NULL,
    is_active varchar(1) NOT NULL default 'N', 
     
     
    org_code varchar(10),
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,
    FOREIGN KEY (org_code) REFERENCES organization(org_code)
    
);

INSERT INTO Country (code, country) VALUES ('IN', 'India');

INSERT INTO City (code, country_code, city_name) VALUES ('C1', 'IN', 'Mumbai');
INSERT INTO City (code, country_code, city_name) VALUES ('C2', 'IN', 'Delhi');
INSERT INTO City (code, country_code, city_name) VALUES ('C3', 'IN', 'Bangalore');
INSERT INTO City (code, country_code, city_name) VALUES ('C4', 'IN', 'Kolkata');
INSERT INTO City (code, country_code, city_name) VALUES ('C5', 'IN', 'Chennai');
INSERT INTO City (code, country_code, city_name) VALUES ('C6', 'IN', 'Hyderabad');
INSERT INTO City (code, country_code, city_name) VALUES ('C7', 'IN', 'Pune');
INSERT INTO City (code, country_code, city_name) VALUES ('C8', 'IN', 'Ahmedabad');
INSERT INTO City (code, country_code, city_name) VALUES ('C9', 'IN', 'Jaipur');
INSERT INTO City (code, country_code, city_name) VALUES ('C10', 'IN', 'Lucknow');


INSERT INTO language (language_code, language) VALUES ('EN', 'English');
INSERT INTO language (language_code, language) VALUES ('HI', 'Hindi');
INSERT INTO language (language_code, language) VALUES ('TA', 'Tamil');

INSERT INTO offer (offer_code, description, offer_type, discount_percentage, org_code, created_at, created_by)
VALUES ('OFF0123', 'Sample offer description', 'DISCOUNT_AFTER_3_TICKET', 50, 'ORG_0HVK', NOW(), 'TEST_USER');

INSERT INTO offer (offer_code, description, offer_type, discount_percentage, org_code, created_at, created_by)
VALUES ('OFF0124', 'Sample offer description', 'DISCOUNT_CITY', 20, 'ORG_0HVK', NOW(), 'TEST_USER');

INSERT INTO offer (offer_code, description, offer_type, discount_percentage, org_code, created_at, created_by)
VALUES ('OFF0125', 'Sample offer description', 'DISCOUNT_THEATER', 10, 'ORG_0HVK', NOW(), 'TEST_USER');

INSERT INTO offer (offer_code, description, offer_type, discount_percentage, org_code, created_at, created_by)
VALUES ('OFF0127', 'Sample offer description', 'DISCOUNT_AFTERNOON', 20, 'ORG_H2AX', NOW(), 'TEST_USER'); 

INSERT INTO offer (offer_code, description, offer_type, discount_percentage, org_code, created_at, created_by)
VALUES ('OFF0128', 'Sample offer description', 'DISCOUNT_AFTER_3_TICKET', 50, 'ORG_H2AX', NOW(), 'TEST_USER');



INSERT INTO show_offer (offer_id, show_id, org_code, created_at, created_by)
VALUES (5, 3, 'ORG_0HVK', NOW(), 'TEST_USER');

INSERT INTO theatre_offer (offer_id, theatre_id, org_code, created_at, created_by)
VALUES (4, 3, 'ORG_0HVK', NOW(), 'TEST_USER');

INSERT INTO city_offer (offer_id, city_code, org_code, created_at, created_by)
VALUES (3, 'C2', 'ORG_0HVK', NOW(), 'TEST_USER');

INSERT INTO show_offer (offer_id, show_id, org_code, created_at, created_by)
VALUES (1, 3, 'ORG_0HVK', NOW(), 'TEST_USER');

INSERT INTO logic_processor 
    (logic_type, logic_execution_listener, is_active, org_code, created_at, created_by) 
VALUES 
    ('DISCOUNT_LOGIC', 'com.xyz.common.component.processor.BookingLogicCombo', 'Y', 'ORG_H2AX', NOW(), 'TEST_USER');
	
	
INSERT INTO show_offer (offer_id, show_id, org_code, created_at, created_by)
VALUES (7, 6, 'ORG_H2AX', NOW(), 'TEST_USER');

INSERT INTO show_offer (offer_id, show_id, org_code, created_at, created_by)
VALUES (6, 6, 'ORG_H2AX', NOW(), 'TEST_USER');


