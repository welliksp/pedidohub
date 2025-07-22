CREATE TABLE tb_item_pedido (
    id CHAR(36) NOT NULL PRIMARY KEY,
    produto_id CHAR(36) NOT NULL,
    quantidade INT NOT NULL,
    pedido_id CHAR(36) NOT NULL,

    CONSTRAINT fk_item_pedido FOREIGN KEY (pedido_id) REFERENCES tb_pedido(id),
    CONSTRAINT fk_item_pedido_produto FOREIGN KEY (produto_id) REFERENCES tb_produto(id)
);
