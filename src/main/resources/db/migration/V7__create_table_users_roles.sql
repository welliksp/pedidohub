CREATE TABLE IF NOT EXISTS tb_users_roles (
                                              user_id BIGINT NOT NULL,
                                              role_id BIGINT NOT NULL,
                                              PRIMARY KEY (user_id, role_id),
                                              FOREIGN KEY (user_id) REFERENCES tb_user(id) ON DELETE CASCADE,
                                              FOREIGN KEY (role_id) REFERENCES tb_roles(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;