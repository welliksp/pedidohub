CREATE TABLE tb_pedido (
    id CHAR(36) NOT NULL PRIMARY KEY,
    status VARCHAR(50),
    total DECIMAL(15, 2),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT,
    CONSTRAINT fk_pedido_user FOREIGN KEY (user_id) REFERENCES tb_user(id)
);
