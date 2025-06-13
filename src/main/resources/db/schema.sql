CREATE DATABASE IF NOT EXISTS cms;

USE cms;

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255),
    role VARCHAR(255)
);

CREATE TABLE complaints (
        complaint_id INT PRIMARY KEY AUTO_INCREMENT,
        employee_id INT,
        description TEXT,
        date_submitted DATE,
        status VARCHAR(20) DEFAULT 'New'
);
