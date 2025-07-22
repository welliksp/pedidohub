INSERT INTO tb_users_roles (user_id, role_id)
VALUES (
           (SELECT id FROM tb_users WHERE username = 'admin'),
           (SELECT id FROM tb_roles WHERE name = 'ADMIN')
       )
ON DUPLICATE KEY UPDATE user_id = user_id;