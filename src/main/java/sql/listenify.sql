CREATE TABLE Users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       is_paid_user BOOLEAN DEFAULT FALSE,
                       registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
