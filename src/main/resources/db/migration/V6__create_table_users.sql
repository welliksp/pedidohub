CREATE TABLE IF NOT EXISTS tb_users (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        first_name VARCHAR(100) NOT NULL,
                                        last_name VARCHAR(100) NOT NULL,
                                        username VARCHAR(50) NOT NULL UNIQUE,
                                        email VARCHAR(100) NOT NULL UNIQUE,
                                        birthdate DATE,
                                        password VARCHAR(255) NOT NULL,
                                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;