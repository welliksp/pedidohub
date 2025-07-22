INSERT INTO tb_produto (id, nome, descricao, preco, quantidade_estoque, category_id, data_criacao, data_atualizacao) VALUES
-- Informática (3 itens)
(UUID(), 'Notebook Dell Inspiron', 'Notebook para uso doméstico e empresarial', 3499.90, 30, '61907c16-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Mouse Gamer Redragon', 'Mouse óptico com RGB e alta precisão', 199.90, 30, '61907c16-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Teclado Mecânico', 'Teclado mecânico RGB com switches azuis', 299.90, 30, '61907c16-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),

-- Eletrodomésticos (3 itens)
(UUID(), 'Geladeira Brastemp Frost Free', 'Geladeira com tecnologia frost free', 2899.90, 30, '61908077-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Micro-ondas Electrolux 20L', 'Micro-ondas prático e moderno', 499.90, 30, '61908077-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Fogão 4 bocas Atlas', 'Fogão com acendimento automático', 699.90, 30, '61908077-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),

-- Celulares e Telefonia (3 itens)
(UUID(), 'iPhone 14', 'Smartphone Apple com 128GB', 5299.90, 30, '61908226-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Samsung Galaxy S23', 'Smartphone com câmera tripla e 256GB', 4799.90, 30, '61908226-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Xiaomi Redmi Note 12', 'Celular intermediário com bom custo-benefício', 1399.90, 30, '61908226-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),

-- Eletrônicos (3 itens)
(UUID(), 'Smart TV Samsung 50\"', 'Smart TV 4K com Tizen OS', 2899.90, 30, '619083a4-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Caixa de som JBL', 'Som portátil com bluetooth', 599.90, 30, '619083a4-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Fone de ouvido Bluetooth', 'Fone sem fio com cancelamento de ruído', 299.90, 30, '619083a4-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),

-- Móveis (3 itens)
(UUID(), 'Sofá Retrátil 3 lugares', 'Sofá confortável com tecido suede', 1899.90, 30, '61908507-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Mesa de Jantar 6 cadeiras', 'Mesa com tampo de vidro temperado', 1299.90, 30, '61908507-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Cama Box Queen', 'Cama box com colchão incluso', 1599.90, 30, '61908507-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),

-- Decoração (3 itens)
(UUID(), 'Abajur de mesa', 'Abajur decorativo com LED', 149.90, 30, '61908695-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Quadro decorativo floral', 'Conjunto com 3 quadros', 199.90, 30, '61908695-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Cortina Blackout', 'Cortina para sala ou quarto', 299.90, 30, '61908695-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),

-- Utilidades Domésticas (3 itens)
(UUID(), 'Jogo de Panelas Tramontina', 'Jogo com 5 peças antiaderentes', 499.90, 30, '619087c8-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Liquidificador Arno', 'Liquidificador potente com 2 velocidades', 199.90, 30, '619087c8-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Escorredor de Louça Inox', 'Escorredor de pratos e talheres', 129.90, 30, '619087c8-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),

-- Beleza e Perfumaria (3 itens)
(UUID(), 'Perfume Hugo Boss', 'Perfume masculino 100ml', 399.90, 30, '61908913-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Secador de cabelo Mondial', 'Secador 2000W com difusor', 189.90, 30, '61908913-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Kit Maquiagem Ruby Rose', 'Kit com sombras e pincéis', 129.90, 30, '61908913-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),

-- Moda (3 itens)
(UUID(), 'Camiseta básica masculina', 'Camiseta algodão diversas cores', 49.90, 30, '61908a42-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Tênis feminino esportivo', 'Tênis leve para caminhada', 199.90, 30, '61908a42-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Calça jeans skinny', 'Calça jeans com elastano', 159.90, 30, '61908a42-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),

-- Esporte e Lazer (3 itens)
(UUID(), 'Bicicleta Aro 29 Caloi', 'Bicicleta com freio a disco e quadro de alumínio', 1599.90, 30, '61908b15-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Barraca 4 pessoas', 'Ideal para camping com proteção UV', 399.90, 30, '61908b15-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Kit Halteres 10kg', 'Par de halteres ajustáveis', 249.90, 30, '61908b15-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),

-- Brinquedos (3 itens)
(UUID(), 'Boneca Baby Alive', 'Boneca com funções interativas', 199.90, 30, '61908bda-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Carrinho Hot Wheels', 'Carrinho metálico de brinquedo', 29.90, 30, '61908bda-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Lego Classic 500 peças', 'Blocos coloridos para montar', 349.90, 30, '61908bda-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),

-- Bebês e Primeira Infância (3 itens)
(UUID(), 'Kit Higiene Bebê', 'Kit com escova, pente e tesoura', 89.90, 30, '61908ca5-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Banheira Dobrável', 'Banheira prática para recém-nascido', 229.90, 30, '61908ca5-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Cadeirão de Alimentação', 'Cadeira com bandeja removível', 399.90, 30, '61908ca5-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),

-- Papelaria e Escritório (3 itens)
(UUID(), 'Papel Sulfite A4 500 folhas', 'Papel branco 75g/m²', 39.90, 30, '61908d6d-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Caneta Esferográfica Azul', 'Caixa com 10 unidades', 19.90, 30, '61908d6d-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Caderno 10 matérias', 'Capa dura, espiral e 200 folhas', 49.90, 30, '61908d6d-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),

-- Ferramentas e Construção (3 itens)
(UUID(), 'Furadeira de Impacto Bosch', 'Furadeira elétrica com maleta', 299.90, 30, '61908e3a-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Jogo de Chaves Allen', 'Conjunto com 10 peças', 59.90, 30, '61908e3a-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Serra Mármore Makita', 'Ferramenta profissional de corte', 399.90, 30, '61908e3a-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),

-- Automotivo (3 itens)
(UUID(), 'Filtro de Óleo Fram', 'Filtro de óleo para automóveis', 79.90, 30, '61908eff-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Óleo Lubrificante Mobil 1L', 'Óleo sintético 5W-40', 69.90, 30, '61908eff-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW()),
(UUID(), 'Jogo de Tapetes Automotivos', 'Tapetes em borracha universal', 129.90, 30, '61908eff-66f9-11f0-88b2-d6513ef7b682', NOW(), NOW());
