CREATE TABLE operation (
  id bigint auto_increment,
  operation_code varchar(255) not null,
  operation_description varchar(50) not null,
  price double not null,
  default_dlt_charge double not null,
  default_wage double not null,
  primary key (id));

INSERT INTO operation (id,operation_code,operation_description,price,default_dlt_charge,default_wage)
 VALUES
  (1,'Elmo Herring','1991-01-20',55.5,5.5,5000),
    (2,'Elmo Herring','1991-01-20',55.5,5.5,5000),
      (3,'Elmo Herring','1991-01-20',55.5,5.5,5000);
