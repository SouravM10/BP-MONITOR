CREATE DATABASE bp_monitor;
USE bp_monitor;

CREATE TABLE patients (
    pat_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    age INT,
    address VARCHAR(200),
    contact VARCHAR(15)
);

CREATE TABLE readings (
    read_id INT PRIMARY KEY AUTO_INCREMENT,
    pat_id INT,
    systolic INT,
    diastolic INT,
    is_high BOOLEAN,
    read_time DATETIME DEFAULT NOW(),
    FOREIGN KEY (pat_id) REFERENCES patients(pat_id)
);
