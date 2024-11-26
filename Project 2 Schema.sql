CREATE DATABASE cmpe343;
USE cmpe343;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('MANAGER', 'ENGINEER', 'TECHNICIAN', 'INTERN') NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_no VARCHAR(20),
    email VARCHAR(100),
    date_of_birth DATE,
    date_of_start DATE
);

INSERT INTO users (username, password, role, first_name, last_name, phone_no, email, date_of_birth, date_of_start) VALUES
('ilktanar', '123', 'MANAGER', 'İlktan', 'Ar', '555-1234', 'ilktanar@example.com', '1985-03-12', '2020-01-01'),
('edinkoc', '123', 'ENGINEER', 'Edin', 'Koç', '555-5678', 'edinkoc@example.com', '1990-06-15', '2021-02-01'),
('goktugates', '123', 'TECHNICIAN', 'Göktuğ', 'Ateş', '555-8765', 'goktugates@example.com', '1992-11-25', '2022-04-01'),
('mehmetkuzucu', '123', 'INTERN', 'Mehmet', 'Kuzucu', '555-4321', 'mehmetkuzucu@example.com', '2000-01-10', '2023-07-01'),
('miraykoksal', '123', 'MANAGER', 'Miray', 'Köksal', '555-1122', 'miraykoksal@example.com', '1988-04-20', '2019-08-15'),
('ozankutlar', '123', 'ENGINEER', 'Ozan', 'Kutlar', '555-3344', 'ozankutlar@example.com', '1995-09-30', '2020-11-05'),
('tunazeyneloglu', '123', 'TECHNICIAN', 'Tuna', 'Zeyneloğlu', '555-5566', 'tuna@example.com', '1993-07-14', '2021-03-10'),
('dorukegeaksu', '123', 'MANAGER', 'Doruk Ege', 'Aksu', '555-7788', 'dorukegeaksu@example.com', '2001-01-22', '2024-01-10'),
('ilhanuzunoglu', '123', 'INTERN', 'İlhan', 'Uzunoğlu', '555-7789', 'ilhan@example.com', '2001-01-22', '2024-01-10'),
('imrandurmus', '123', 'INTERN', 'İmran', 'Durmuş', '555-7790', 'imran@example.com', '2001-01-22', '2024-01-10'),
('kerembicen', '123', 'INTERN', 'Kerem', 'Biçen', '555-7791', 'kerem@example.com', '2001-01-22', '2024-01-10'),
('tagmaczekibilen', '123', 'INTERN', 'Tağmaç Zeki', 'Bilen', '555-7788', 'tagmac@example.com', '2001-01-22', '2024-01-10'),
('john_doe', 'password_1', 'MANAGER', 'John', 'Doe', '555-1234', 'john.doe@example.com', '1985-03-12', '2020-01-01'),
('jane_smith', 'password_2', 'ENGINEER', 'Jane', 'Smith', '555-5678', 'jane.smith@example.com', '1990-06-15', '2021-02-01'),
('alice_jones', 'password_3', 'TECHNICIAN', 'Alice', 'Jones', '555-8765', 'alice.jones@example.com', '1992-11-25', '2022-04-01'),
('bob_white', 'password_4', 'INTERN', 'Bob', 'White', '555-4321', 'bob.white@example.com', '2000-01-10', '2023-07-01'),
('lucas_king', 'password_5', 'MANAGER', 'Lucas', 'King', '555-1122', 'lucas.king@example.com', '1988-04-20', '2019-08-15'),
('emily_clark', 'password_6', 'ENGINEER', 'Emily', 'Clark', '555-3344', 'emily.clark@example.com', '1995-09-30', '2020-11-05'),
('david_miller', 'password_7', 'TECHNICIAN', 'David', 'Miller', '555-5566', 'david.miller@example.com', '1993-07-14', '2021-03-10'),
('susan_brown', 'password_8', 'INTERN', 'Susan', 'Brown', '555-7788', 'susan.brown@example.com', '2001-01-22', '2024-01-10');