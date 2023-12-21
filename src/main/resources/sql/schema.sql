
drop database rental_wheelz;

create database rental_wheelz;

USE rental_wheelz;
CREATE TABLE employee(
                         emp_id VARCHAR(10) PRIMARY KEY,
                         e_name VARCHAR(100) NOT NULL,
                         email VARCHAR(200) NOT NULL,
                         position VARCHAR(20) NOT NULL,
                         address VARCHAR(200) NOT NULL,
                         contact VARCHAR(15) NOT NULL,
                         INDEX(e_name),
                         INDEX(email)
);

CREATE TABLE user(
                     u_id VARCHAR(10) PRIMARY KEY,
                     u_name VARCHAR(100) NOT NULL,
                     password VARCHAR(50) NOT NULL,
                     emp_id VARCHAR(10),
                     email VARCHAR(200),
                     CONSTRAINT FOREIGN KEY(emp_id) REFERENCES employee(emp_id) ON DELETE CASCADE ON UPDATE CASCADE,
                     CONSTRAINT FOREIGN KEY(email) REFERENCES employee(email) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE customer (
                          cust_id VARCHAR(10) PRIMARY KEY,
                          name VARCHAR(50) NOT NULL,
                          address VARCHAR(200) NOT NULL,
                          contact_no VARCHAR(15) NOT NULL,
                          u_id VARCHAR(10),
                          CONSTRAINT FOREIGN KEY (u_id) REFERENCES user(u_id) ON DELETE CASCADE ON UPDATE CASCADE
);



CREATE TABLE salary (
                        sal_id VARCHAR(10) PRIMARY KEY,
                        s_amount DECIMAL(10, 2),
                        emp_id VARCHAR(10),
                        CONSTRAINT FOREIGN KEY (emp_id) REFERENCES employee(emp_id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE attendance (
                            attendId VARCHAR(10) PRIMARY KEY ,
                            date DATE,
                            empName VARCHAR(100),
                            CONSTRAINT FOREIGN KEY (empName) REFERENCES employee(e_name) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE rent (
                      rent_id VARCHAR(10) PRIMARY KEY,
                      amount VARCHAR(10) NOT NULL,
                      cust_id VARCHAR(50),
                      starting_date DATE,
                      ending_date DATE,
                      CONSTRAINT FOREIGN KEY (cust_id) REFERENCES customer(cust_id) ON DELETE CASCADE ON UPDATE CASCADE
);



CREATE TABLE car (
                     car_id VARCHAR(10) PRIMARY KEY,
                     car_name VARCHAR(50),
                     status VARCHAR(10)
);

CREATE TABLE sup_order (
                           order_id VARCHAR(10) PRIMARY KEY,
                           date DATE
);

CREATE TABLE supplier (
                          sup_id VARCHAR(10) PRIMARY KEY,
                          sup_name VARCHAR(50) NOT NULL,
                          avlable_car VARCHAR(20) NOT NULL,
                          order_id VARCHAR(10),
                          CONSTRAINT FOREIGN KEY (order_id) REFERENCES sup_order(order_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE sup_order_detail (
                                  order_id VARCHAR(10),
                                  sup_id VARCHAR(10),
                                  CONSTRAINT FOREIGN KEY (order_id) REFERENCES sup_order(order_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                  CONSTRAINT FOREIGN KEY (sup_id) REFERENCES supplier(sup_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE rent_detail (
                             rent_id VARCHAR(10) NOT NULL ,
                             car_id VARCHAR(10) NOT NULL ,
                             rent_fee DECIMAL   NOT NULL,
                             CONSTRAINT FOREIGN KEY (rent_id) REFERENCES rent(rent_id) ON DELETE CASCADE ON UPDATE CASCADE,
                             CONSTRAINT FOREIGN KEY (car_id) REFERENCES car(car_id) ON DELETE CASCADE ON UPDATE CASCADE
);
-- add Employees
INSERT INTO employee (emp_id, e_name, email, position, address, contact)
VALUES
    ('E001', 'Oswald Cobblepot', 'sdweerapperuma@gmail.com', 'Manager', 'baddegama,galle', '0771456789'),
    ('E002', 'Babara Kean', '888sasmithmanawadu@gmail.com', 'Manager', 'wathugedara,ambalangoda', '0778263491'),
    ('E003', 'Bob Johnson', 'bobjohnson@Ggmail.com', 'Mechanic', 'weligama,matara', '0775092846'),
    ('E004', 'Alice Brown', 'alicebrown@gmail.com', 'Mechanic', 'Rainforest Street,Badulla', '0777315829'),
    ('E005', 'Charlie Davis', 'charliedavis@gmail.com', 'Driver', 'TempleRoad,Jaffna', '0774075123'),
    ('E006', 'Eva Wilson', 'evawilson@gmail.com', 'Driver', ' HillStreet,NuwaraEliya', '0776982350'),
    ('E007', 'Frank Miller', 'frankmiller@gmail.com', 'Driver', 'walgama,matara', '0719563827'),
    ('E008', 'Grace Lee', 'gracelee@gmail.com', 'Driver', 'Nawala road,rajagiriya', '0712038475'),
    ('E009', 'David Clark', 'davidclark@gmail.com', 'Driver', 'baddegama,galle', '0717259346'),
    ('E010', 'Helen Turner', 'helenturner@gmail.com', 'Driver', 'madamulana,srilanka', '0718492063');

-- add users
INSERT INTO user VALUES ('U001', 'riddler', '123', 'E001','sdweerapperuma@gmail.com'),
       ('U002', 'babara', '345', 'E002','888sasmithmanawadu@gmail.com');

INSERT INTO car (car_id, car_name, status) VALUES
    ('V002', 'toyota', 'available'),
    ('V003', 'honda', 'available'),
    ('V004', 'nissan', 'available'),
    ('V005', 'ford', 'available'),
    ('V006', 'chevrolet', 'available'),
    ('V007', 'hyundai', 'available'),
    ('V008', 'volkswagen', 'available'),
    ('V009', 'bmw', 'available'),
    ('V010', 'mercedes', 'available'),
    ('V011', 'audi', 'available'),
    ('V012', 'kia', 'available'),
    ('V013', 'mazda', 'available'),
    ('V014', 'subaru', 'available'),
    ('V015', 'lexus', 'available'),
    ('V016', 'acura', 'available'),
    ('V017', 'infiniti', 'available'),
    ('V018', 'jaguar', 'available'),
    ('V019', 'porsche', 'available'),
    ('V020', 'tesla', 'available')
;
-- add customers
INSERT INTO customer (cust_id, name, address, contact_no, u_id) VALUES
    ('C001', 'Samantha kariyawasam', 'Colombo', '7712345678', 'U001'),
    ('C002', 'nadun tharaka', 'Kandy', '7723456789', 'U002'),
    ('C003', 'Chathura madusanka', 'Galle', '7734567890', 'U002'),
    ('C004', 'Malith silve', 'Negombo', '7745678901', 'U001'),
    ('C005', 'Thilini piyumali', 'Matara', '7756789012', 'U001'),
    ('C006', 'Prasad silva', 'Jaffna', '7767890123', 'U001'),
    ('C007', 'Dilini silva', 'Anuradhapura', '7778901234', 'U001'),
    ('C008', 'Kasun harshana', 'Badulla', '7789012345', 'U002'),
    ('C009', 'Shanika madumali', 'Ratnapura', '7790123456', 'U002'),
    ('C010', 'Dinesh muthugala', 'Kurunegala', '7101234567', 'U002'),
    ('C011', 'Rukmal silva', 'Trincomalee', '7112345678', 'U001'),
    ('C012', 'Madushan kariyawasam', 'Batticaloa', '7123456789', 'U002'),
    ('C013', 'Anuradha sigera', 'Polonnaruwa', '7134567890', 'U001'),
    ('C014', 'Sachin maduhansa', 'Kalutara', '7145678901', 'U001'),
    ('C015', 'Nuwan lomba', 'Gampaha', '7156789012', 'U002'),
    ('C016', 'Nisansala kariyawasam', 'Hambantota', '7167890123', 'U001'),
    ('C017', 'Lahiru silva', 'Matale', '7178901234', 'U001'),
    ('C018', 'Chandima deeptha', 'Puttalam', '7189012345', 'U002'),
    ('C019', 'Sachith liyanage', 'Ampara', '7190123456', 'U002'),
    ('C020', 'Sanduni sansala', 'Kegalle', '7201234567', 'U001');


#  INSERT INTO user VALUES ('U002','babara','345','E002');
INSERT INTO sup_order VALUES('O001','2023-10-10');
INSERT INTO supplier VALUES('S001','chandana','benz','O001');

