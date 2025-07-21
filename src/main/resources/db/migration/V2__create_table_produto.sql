CREATE TABLE tb_produto (
    id CHAR(36) NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    preco DECIMAL(10, 2) NOT NULL,
    category_id CHAR(36) NOT NULL,
    quantidade_estoque INT NOT NULL DEFAULT 0,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_produto_categoria FOREIGN KEY (category_id)
            REFERENCES tb_categoria(id)
            ON DELETE RESTRICT
            ON UPDATE CASCADE

);