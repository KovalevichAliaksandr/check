insert INTO job.department(name_department) VALUE ('credit');
insert INTO job.department(name_department) VALUE ('monetary');
insert INTO job.department(name_department) VALUE ('retail');
insert INTO job.department(name_department) VALUE ('cash');
insert INTO job.department(name_department) VALUE ('automation');

SELECT * FROM job.department ORDER BY  id;
INSERT INTO job.employee (first_name, last_name, dob, salary, id_department) VALUES ('Scott','Wolf','1944-05-18',5000,1);
INSERT INTO job.employee (first_name, last_name, dob, salary, id_department) VALUES ('Henry','Jackson','1974-02-11',3500,2);
INSERT INTO job.employee (first_name, last_name, dob, salary, id_department) VALUES ('Raul','Chan','1988-11-15',2500,3);
INSERT INTO job.employee (first_name, last_name, dob, salary, id_department) VALUES ('Amanda','Turner','1985-10-20',4800,5);
INSERT INTO job.employee (first_name, last_name, dob, salary, id_department) VALUES ('Sam','Dikson','1986-04-11',3700,4);
INSERT INTO job.employee (first_name, last_name, dob, salary, id_department) VALUES ('Tiner','Simon','1949-08-30',2300,3);
INSERT INTO job.employee (first_name, last_name, dob, salary, id_department) VALUES ('Henry','Simon','1969-08-15',6300,1);
INSERT INTO job.employee (first_name, last_name, dob, salary, id_department) VALUES ('Sasha','Schaefer','1973-05-15',8600,2);
INSERT INTO job.employee (first_name, last_name, dob, salary, id_department) VALUES ('Andrew','Bouly','1977-09-22',1200,5);
INSERT INTO job.employee (first_name, last_name, dob, salary, id_department) VALUES ('Hall','Hall','1988-03-07',1400,2);
INSERT INTO job.employee (first_name, last_name, dob, salary, id_department) VALUES ('Ava','Taylor','1967-04-12',1400,3);
INSERT INTO job.employee (first_name, last_name, dob, salary, id_department) VALUES ('Elizabeth','Martin','1965-02-02',1400,4);
INSERT INTO job.employee (first_name, last_name, dob, salary, id_department) VALUES ('Sofia','Baker ','1961-01-01',1400,4);
INSERT INTO job.employee (first_name, last_name, dob, salary, id_department) VALUES ('Lillian','Mitchel','1962-03-03',1400,5);

COMMIT ;
SELECT * FROM job.employee;
