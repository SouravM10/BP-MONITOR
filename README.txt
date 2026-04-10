Blood Pressure Monitoring System

Name: Soumojeet Dutta, Soumya Biswas, Soumya Sharma, Sourav Kaiborta, Sourav Mukherjee
Roll No: 34,35,36,37,38
Set No: 19

---

Project Description:
This project is a console-based Java application developed to monitor and manage blood pressure readings of patients. It allows healthcare workers to record patient data and track blood pressure trends using a MySQL database connected via JDBC.

---

Technologies Used:

* Core Java
* MySQL Database
* JDBC (Java Database Connectivity)

---

Features:

1. Register a new patient (name, age, address, contact)
2. Record blood pressure readings (systolic and diastolic values)
3. Automatically flag high blood pressure readings (systolic > 140 or diastolic > 90)
4. Display the last 5 readings of a patient
5. Count the number of high BP readings for a patient

---

Database Structure:
The system uses two tables:

1. patients

   * pat_id (Primary Key)
   * name
   * age
   * address
   * contact

2. readings

   * read_id (Primary Key)
   * pat_id (Foreign Key)
   * systolic
   * diastolic
   * is_high
   * read_time

---

How to Run the Project:

1. Open MySQL Workbench and execute the schema.sql file
2. Ensure MySQL server is running
3. Update database credentials in DBConnection.java
4. Add MySQL Connector/J (.jar file) to project libraries
5. Compile and run Main.java
6. Use the menu-driven console to perform operations

---

Sample Usage:

* Register a patient
* Record BP readings
* View recent readings
* Check high BP count

---

Conclusion:
This system helps in early detection of high blood pressure trends and ensures timely medical attention, reducing the risk of health complications.

---

Author:
Soumya Sharma
