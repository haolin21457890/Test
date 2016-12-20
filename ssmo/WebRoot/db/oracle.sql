-- Create table
create table T_CAR
(
  id        NUMBER(6),
  name      VARCHAR2(100),
  sale_date DATE,
  price     NUMBER(12,2)
);

-- Create/Recreate primary, unique and foreign key constraints 
alter table T_CAR
  add constraint PK_CAR primary key (ID);
  
-- Create sequence 
create sequence SEQ_CAR
minvalue 1
maxvalue 999999
start with 1
increment by 1
nocache
order;