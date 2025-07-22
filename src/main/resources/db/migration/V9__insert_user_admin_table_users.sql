INSERT INTO tb_user (first_name, last_name, username, email, birthdate, password)
VALUES (
           'Admin',
           'Admin',
           'admin',
           'admin@example.com',
           '1990-01-01',
           '$2a$10$EQzu0e10EFGYZKM8oyC7V.v9ezcbORJvz0gUTi300tRPMozwXpIqO'
       )
ON DUPLICATE KEY UPDATE username = 'admin';