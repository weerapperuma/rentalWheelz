
drop database rental_wheelz;

create database rental_wheelz;

USE rental_wheelz;
CREATE TABLE employee(
                         emp_id VARCHAR(10) PRIMARY KEY,
                         e_name VARCHAR(15) NOT NULL,
                         email VARCHAR(40) NOT NULL,
                         position VARCHAR(10) NOT NULL,
                         address VARCHAR(20) NOT NULL,
                         contact VARCHAR(15) NOT NULL
);

CREATE TABLE user(
                     u_id VARCHAR(10) PRIMARY KEY,
                     u_name VARCHAR(10) NOT NULL,
                     password VARCHAR(10) NOT NULL,
                     emp_id VARCHAR(10),
                     CONSTRAINT FOREIGN KEY(emp_id) REFERENCES employee(emp_id) ON DELETE CASCADE ON UPDATE CASCADE
);



CREATE TABLE salary (
                        sal_id VARCHAR(10) PRIMARY KEY,
                        s_amount DECIMAL(10, 2),
                        emp_id VARCHAR(10),
                        CONSTRAINT FOREIGN KEY (emp_id) REFERENCES employee(emp_id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE attendance (
                            date DATE,
                            emp_id VARCHAR(10),
                            CONSTRAINT FOREIGN KEY (emp_id) REFERENCES employee(emp_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE customer (
                          cust_id VARCHAR(10) PRIMARY KEY,
                          name VARCHAR(20) NOT NULL,
                          address VARCHAR(30) NOT NULL,
                          contact_no VARCHAR(15) NOT NULL,
                          u_id VARCHAR(50),
                          CONSTRAINT FOREIGN KEY (u_id) REFERENCES user(u_id) ON DELETE CASCADE ON UPDATE CASCADE
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
                     car_name VARCHAR(10),
                     status VARCHAR(10)
);

CREATE TABLE sup_order (
                           order_id VARCHAR(10) PRIMARY KEY,
                           date DATE
);

CREATE TABLE supplier (
                          sup_id VARCHAR(10) PRIMARY KEY,
                          sup_name VARCHAR(10) NOT NULL,
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

insert into employee values ('E001','riddler','riddler@gotham.com','manager','new york','875467');

insert into user values ('U001','riddler','123','E001');
INSERT INTO car VALUES ('V001','luxury','available');
INSERT INTO customer VALUES ('C001','LOKITHA','walgama','711018201','U001');
insert into employee values ('E002','babara','babara@gotham.com','dealer','new york','846767');
INSERT INTO user VALUES ('U002','babara','345','E002');
INSERT INTO sup_order VALUES('O001','2023-10-10');
INSERT INTO supplier VALUES('S001','chandana','benz','O001');
