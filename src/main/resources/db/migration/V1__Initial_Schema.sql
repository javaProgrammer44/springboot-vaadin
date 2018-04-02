 CREATE TABLE receipt_code (
  id bigint auto_increment,
  code varchar(255) not null,
  primary key (id));


   CREATE TABLE operation_area (
  id bigint auto_increment,
  province_area varchar(255) not null,
    dlt_charge double not null,
      wage double not null,
  primary key (id));

CREATE TABLE operation (
  id bigint auto_increment,
  operation_code varchar(255) not null,
  operation_description varchar(50) not null,
  price double not null,
  default_dlt_charge double not null,
  default_wage double not null,
  receipt_code_id bigint not null,
  primary key (id),
  CONSTRAINT `operation_receipt_code_id` FOREIGN KEY (`receipt_code_id`) REFERENCES `receipt_code` (`id`) ON DELETE CASCADE ON UPDATE CASCADE);






INSERT INTO receipt_code (id,code)
 VALUES
  (1,'receipt Code 1'),  (2,'receipt Code 2'),  (3,'receipt Code 3'),  (4,'receipt Code 3');


INSERT INTO operation (id,operation_code,operation_description,price,default_dlt_charge,default_wage,receipt_code_id)
 VALUES
  (1,'Operation Code 1','Operation Description 1',11.1,1.1,5000,1),
    (2,'Operation Code 2','Operation Description 2',22.22,1.2,5000,2),
      (3,'Operation Code 3','Operation Description 2',33.33,1.3,5000,3);
