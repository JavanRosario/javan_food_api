SET
FOREIGN_KEY_CHECKS = 0;

DELETE
FROM cidade;
DELETE
FROM cozinha;
DELETE
FROM estado;
DELETE
FROM forma_pagamento;
DELETE
FROM grupo;
DELETE
FROM grupo_permissao;
DELETE
FROM permissao;
DELETE
FROM produto;
DELETE
FROM restaurante;
DELETE
FROM restaurante_forma_pagamento;
DELETE
FROM restaurante_usuario_responsavel;
DELETE
FROM usuario;
DELETE
FROM usuario_grupo;
DELETE
FROM pedido;
DELETE
FROM item_pedido;

ALTER TABLE cidade AUTO_INCREMENT = 1;
ALTER TABLE cozinha AUTO_INCREMENT = 1;
ALTER TABLE estado AUTO_INCREMENT = 1;
ALTER TABLE forma_pagamento AUTO_INCREMENT = 1;
ALTER TABLE grupo AUTO_INCREMENT = 1;
ALTER TABLE grupo_permissao AUTO_INCREMENT = 1;
ALTER TABLE permissao AUTO_INCREMENT = 1;
ALTER TABLE produto AUTO_INCREMENT = 1;
ALTER TABLE restaurante AUTO_INCREMENT = 1;
ALTER TABLE restaurante_forma_pagamento AUTO_INCREMENT = 1;
ALTER TABLE usuario AUTO_INCREMENT = 1;
ALTER TABLE usuario_grupo AUTO_INCREMENT = 1;
ALTER TABLE pedido AUTO_INCREMENT = 1;
ALTER TABLE item_pedido AUTO_INCREMENT = 1;

SET
FOREIGN_KEY_CHECKS = 1;

INSERT INTO estado (nome)
VALUES ('Rio de Janeiro');
INSERT INTO estado (nome)
VALUES ('São Paulo');
INSERT INTO estado (nome)
VALUES ('Paraná');
INSERT INTO estado (nome)
VALUES ('Minas Gerais');
INSERT INTO estado (nome)
VALUES ('Rio Grande do Sul');

INSERT INTO cidade (nome, estado_id)
VALUES ('Rio de Janeiro', 1);
INSERT INTO cidade (nome, estado_id)
VALUES ('Niterói', 1);
INSERT INTO cidade (nome, estado_id)
VALUES ('São Paulo', 2);
INSERT INTO cidade (nome, estado_id)
VALUES ('Campinas', 2);
INSERT INTO cidade (nome, estado_id)
VALUES ('Curitiba', 3);
INSERT INTO cidade (nome, estado_id)
VALUES ('Londrina', 3);
INSERT INTO cidade (nome, estado_id)
VALUES ('Belo Horizonte', 4);
INSERT INTO cidade (nome, estado_id)
VALUES ('Porto Alegre', 5);

INSERT INTO cozinha (nome)
VALUES ('Italiana');
INSERT INTO cozinha (nome)
VALUES ('Japonesa');
INSERT INTO cozinha (nome)
VALUES ('Brasileira');
INSERT INTO cozinha (nome)
VALUES ('Árabe');
INSERT INTO cozinha (nome)
VALUES ('Mexicana');
INSERT INTO cozinha (nome)
VALUES ('Francesa');

INSERT INTO forma_pagamento (descricao)
VALUES ('Dinheiro');
INSERT INTO forma_pagamento (descricao)
VALUES ('Débito');
INSERT INTO forma_pagamento (descricao)
VALUES ('Crédito');
INSERT INTO forma_pagamento (descricao)
VALUES ('PIX');
INSERT INTO forma_pagamento (descricao)
VALUES ('Vale Refeição');

INSERT INTO permissao (nome, descricao)
VALUES ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permissao (nome, descricao)
VALUES ('EDITAR_COZINHAS', 'Permite editar cozinhas');
INSERT INTO permissao (nome, descricao)
VALUES ('CONSULTAR_RESTAURANTES', 'Permite consultar restaurantes');
INSERT INTO permissao (nome, descricao)
VALUES ('EDITAR_RESTAURANTES', 'Permite editar restaurantes');
INSERT INTO permissao (nome, descricao)
VALUES ('CONSULTAR_PEDIDOS', 'Permite consultar pedidos');
INSERT INTO permissao (nome, descricao)
VALUES ('GERENCIAR_PEDIDOS', 'Permite gerenciar pedidos');

INSERT INTO grupo (nome)
VALUES ('Administrador');
INSERT INTO grupo (nome)
VALUES ('Gerente');
INSERT INTO grupo (nome)
VALUES ('Atendente');
INSERT INTO grupo (nome)
VALUES ('Financeiro');

INSERT INTO grupo_permissao (grupo_id, permissao_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6);
INSERT INTO grupo_permissao (grupo_id, permissao_id)
VALUES (2, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (2, 5),
       (2, 6);
INSERT INTO grupo_permissao (grupo_id, permissao_id)
VALUES (3, 3),
       (3, 5),
       (3, 6);
INSERT INTO grupo_permissao (grupo_id, permissao_id)
VALUES (4, 1),
       (4, 3),
       (4, 5);

INSERT INTO usuario (nome, email, senha, data_cadastro)
VALUES ('Javan Oliveira', 'javan@javanfood.com', '123456', UTC_TIMESTAMP());
INSERT INTO usuario (nome, email, senha, data_cadastro)
VALUES ('Ana Lima', 'ana@javanfood.com', '123456', UTC_TIMESTAMP());

INSERT INTO usuario_grupo (usuario_id, grupo_id)
VALUES (1, 1),
       (1, 2);
INSERT INTO usuario_grupo (usuario_id, grupo_id)
VALUES (2, 3);

INSERT INTO restaurante (nome,
                         taxa_frete,
                         cozinha_id,
                         endereco_cidade_id,
                         endereco_bairro,
                         endereco_cep,
                         endereco_complemento,
                         endereco_logradouro,
                         endereco_numero,
                         data_cadastro,
                         data_atualizacao,
                         ativo,
                         aberto)
VALUES ('Trattoria Del Nonno', 8.90, 1, 3, 'Jardins', '01308-000', 'Sobrado', 'Rua Augusta', '1420', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Nakamura Sushi', 6.50, 2, 3, 'Liberdade', '01501-000', 'Loja 12', 'Rua Galvão Bueno', '540', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Fogão de Minas', 4.00, 3, 7, 'Savassi', '30130-170', NULL, 'Rua Pernambuco', '200', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('La Brasserie', 12.00, 4, 1, 'Ipanema', '22410-003', 'Cobertura', 'Rua Visconde de Pirajá', '330',
        UTC_TIMESTAMP(), UTC_TIMESTAMP(), true, true),
       ('Al Jannat', 7.00, 5, 5, 'Centro', '80020-180', NULL, 'Rua XV de Novembro', '890', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('El Rancho', 9.50, 5, 8, 'Moinhos de Vento', '90570-020', 'Sala 3', 'Rua Padre Chagas', '150', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Pizzaria Napoli', 7.90, 1, 3, 'Centro', '01001-000', 'Loja A', 'Rua das Flores', '45', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Sushi House', 12.50, 2, 2, 'Jardins', '01415-001', NULL, 'Av. Paulista', '1200', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Burger Prime', 6.00, 3, 4, 'Copacabana', '22040-002', NULL, 'Rua Barata Ribeiro', '350', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Cantina Italiana', 10.00, 4, 5, 'Bela Vista', '01310-100', 'Fundos', 'Rua Treze de Maio', '800',
        UTC_TIMESTAMP(), UTC_TIMESTAMP(), true, false),
       ('Tempero Mineiro', 5.50, 5, 6, 'Savassi', '30140-091', NULL, 'Rua Pernambuco', '90', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Churrascaria Boi Forte', 15.00, 1, 7, 'Centro', '70040-010', NULL, 'SQS 104', '20', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('China Express', 8.20, 2, 3, 'Liberdade', '01503-000', NULL, 'Rua Galvão Bueno', '100', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), false, false),
       ('Taco Loco', 9.90, 3, 8, 'Menino Deus', '90110-120', NULL, 'Av. Getúlio Vargas', '1000', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Açaí Point', 4.00, 4, 2, 'Pinheiros', '05422-000', NULL, 'Rua dos Pinheiros', '230', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Pastel do Zé', 3.50, 5, 4, 'Tijuca', '20520-010', NULL, 'Rua Conde de Bonfim', '650', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, false),
       ('Espetinho Grill', 6.70, 1, 1, 'Centro', '80020-310', NULL, 'Rua XV de Novembro', '100', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Padaria Imperial', 2.99, 2, 6, 'Funcionários', '30130-150', NULL, 'Av. Brasil', '200', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Casa do Café', 1.50, 3, 5, 'Moema', '04077-001', NULL, 'Alameda dos Maracatins', '550', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Nordestino Sabor', 8.80, 4, 7, 'Asa Sul', '70390-000', NULL, 'CLS 112', '15', UTC_TIMESTAMP(), UTC_TIMESTAMP(),
        false, false),
       ('Restaurante do Porto', 13.90, 5, 8, 'Cidade Baixa', '90050-170', NULL, 'Rua Lima e Silva', '400',
        UTC_TIMESTAMP(), UTC_TIMESTAMP(), true, true),
       ('Massa & Cia', 9.00, 1, 3, 'Vila Mariana', '04105-000', NULL, 'Rua Domingos de Morais', '500', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, false),
       ('Thai Food', 18.50, 2, 2, 'Itaim Bibi', '04538-132', NULL, 'Rua João Cachoeira', '600', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Bistrô Paris', 22.00, 3, 1, 'Batel', '80420-090', NULL, 'Av. Sete de Setembro', '1800', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Sabor Árabe', 7.80, 4, 6, 'Lourdes', '30170-120', NULL, 'Rua Curitiba', '450', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Dogão Express', 5.00, 5, 4, 'Méier', '20735-100', NULL, 'Rua Dias da Cruz', '890', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Peixe & Mar', 14.50, 1, 5, 'Santos', '11013-400', NULL, 'Av. Ana Costa', '150', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, false),
       ('Vegan Life', 11.20, 2, 2, 'Perdizes', '05015-000', NULL, 'Rua Cardoso de Almeida', '980', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Frango Crocante', 6.40, 3, 8, 'Floresta', '90220-001', NULL, 'Rua Ramiro Barcelos', '321', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Doces da Vovó', 3.90, 4, 3, 'Mooca', '03110-000', NULL, 'Rua da Mooca', '700', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Coxinha Mania', 2.50, 5, 6, 'Centro', '30120-060', NULL, 'Rua Bahia', '130', UTC_TIMESTAMP(), UTC_TIMESTAMP(),
        false, false),
       ('Pizza Suprema', 8.60, 1, 4, 'Botafogo', '22250-040', NULL, 'Rua Voluntários da Pátria', '250', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Yakisoba Mix', 10.30, 2, 7, 'Asa Norte', '70710-540', NULL, 'CLN 210', '18', UTC_TIMESTAMP(), UTC_TIMESTAMP(),
        true, true),
       ('Empório Gourmet', 16.90, 3, 5, 'Vila Olímpia', '04551-060', NULL, 'Rua Funchal', '900', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true, true),
       ('Burguer do Chef', 7.40, 4, 3, 'Santana', '02020-010', NULL, 'Rua Voluntários da Pátria', '1400',
        UTC_TIMESTAMP(), UTC_TIMESTAMP(), true, false);

INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id)
VALUES (1, 2),
       (1, 3),
       (1, 4),
       (2, 2),
       (2, 3),
       (2, 4),
       (2, 5),
       (3, 1),
       (3, 2),
       (3, 4),
       (4, 2),
       (4, 3),
       (5, 1),
       (5, 2),
       (5, 4),
       (6, 2),
       (6, 3),
       (6, 4);

INSERT INTO restaurante_usuario_responsavel (restaurante_id, usuario_id)
VALUES (1, 1),
       (2, 1),
       (3, 2);

INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id)
VALUES ('Spaghetti alla Carbonara', 'Massa artesanal com guanciale, ovo e pecorino romano', 62.90, 0, 1);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id)
VALUES ('Risotto ai Funghi Porcini', 'Arroz arbóreo cremoso com cogumelos porcini importados', 74.90, 1, 1);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id)
VALUES ('Tiramisù', 'Clássico italiano com mascarpone e café espresso', 32.00, 1, 1);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id)
VALUES ('Combo Executivo 30 peças', 'Seleção de niguiris, uramakis e hossomakis', 98.00, 1, 2);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id)
VALUES ('Sashimi de Salmão 10 fatias', 'Salmão norueguês fresco fatiado na hora', 54.90, 1, 2);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id)
VALUES ('Missoshiru', 'Sopa de missô com tofu, wakame e cebolinha', 14.00, 1, 2);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id)
VALUES ('Frango ao Molho Pardo', 'Frango caipira ao molho tradicional mineiro com angu', 49.90, 1, 3);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id)
VALUES ('Feijão Tropeiro', 'Feijão com farinha, couve, bacon e linguiça', 38.90, 1, 3);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id)
VALUES ('Pão de Queijo Artesanal 6 un', 'Pão de queijo mineiro assado na hora', 22.00, 1, 3);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id)
VALUES ('Magret de Canard', 'Peito de pato ao molho de laranja com purê de batata-doce', 128.00, 1, 4);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id)
VALUES ('Soupe à l\'oignon', 'Sopa de cebola gratinada com croutons e gruyère', 48.00, 1, 4);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Crème Brûlée', 'Creme de baunilha com casquinha de açúcar caramelizado', 36.00, 1, 4);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Kafta Grelhada', 'Espeto de carne moída temperada com especiarias árabes', 44.90, 1, 5);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Esfiha Aberta de Carne 6 un', 'Esfiha tradicional com carne temperada e tomate', 29.90, 1, 5);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Homus com Pão Sírio', 'Pasta de grão-de-bico com azeite e páprica', 24.00, 1, 5);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Tacos de Carnitas', '3 tacos de porco desfiado com guacamole e pico de gallo', 42.00, 1, 6);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Burrito de Frango', 'Wrap recheado com frango,
        arroz, feijão preto e queijo', 38.00, 1, 6);
INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Churros com Doce de Leite', 'Churros crocantes com doce de leite argentino', 26.00, 1, 6);

INSERT INTO pedido (
    codigo,
    sub_total,
    taxa_frete,
    valor_total,
    status_pedido,
    data_criacao,
    data_confirmacao,
    data_entrega,
    data_cancelamento,
    endereco_cep,
    endereco_logradouro,
    endereco_numero,
    endereco_complemento,
    endereco_bairro,
    endereco_cidade_id,
    forma_pagamento_id,
    restaurante_id,
    usuario_cliente_id
)
VALUES
(UUID(), 45.90, 8.90, 54.80, 'CRIADO', UTC_TIMESTAMP(), NULL, NULL, NULL, '01308-000', 'Rua Augusta', '100', NULL, 'Jardins', 1, 1, 1, 1),
(UUID(), 89.90, 6.50, 96.40, 'CONFIRMADO', UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, NULL, '22040-002', 'Rua Barata Ribeiro', '250', NULL, 'Copacabana', 2, 2, 2, 2),
(UUID(), 125.00, 9.90, 134.90, 'ENTREGUE', UTC_TIMESTAMP(), UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, '01501-000', 'Rua Galvão Bueno', '78', NULL, 'Liberdade', 1, 1, 1, 2),
(UUID(), 39.90, 4.00, 43.90, 'CANCELADO', UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, UTC_TIMESTAMP(), '80020-180', 'Rua XV de Novembro', '45', NULL, 'Centro', 2, 2, 2, 1),
(UUID(), 68.50, 7.00, 75.50, 'CRIADO', UTC_TIMESTAMP(), NULL, NULL, NULL, '90570-020', 'Rua Padre Chagas', '55', 'Sala 1', 'Moinhos de Vento', 1, 1, 2, 2),
(UUID(), 102.90, 12.50, 115.40, 'CONFIRMADO', UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, NULL, '01310-100', 'Rua Treze de Maio', '120', NULL, 'Bela Vista', 2, 2, 1, 1),
(UUID(), 54.30, 5.50, 59.80, 'ENTREGUE', UTC_TIMESTAMP(), UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, '30130-170', 'Rua Pernambuco', '78', NULL, 'Savassi', 1, 1, 2, 2),
(UUID(), 140.00, 15.00, 155.00, 'CRIADO', UTC_TIMESTAMP(), NULL, NULL, NULL, '70040-010', 'SQS 104', '14', NULL, 'Centro', 2, 2, 1, 1),
(UUID(), 81.20, 6.70, 87.90, 'CONFIRMADO', UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, NULL, '20520-010', 'Rua Conde de Bonfim', '200', NULL, 'Tijuca', 1, 1, 2, 2),
(UUID(), 199.90, 9.50, 209.40, 'ENTREGUE', UTC_TIMESTAMP(), UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, '05422-000', 'Rua dos Pinheiros', '80', NULL, 'Pinheiros', 2, 2, 1, 1),

(UUID(), 35.00, 8.90, 43.90, 'CRIADO', UTC_TIMESTAMP(), NULL, NULL, NULL, '04105-000', 'Rua Domingos de Morais', '55', NULL, 'Vila Mariana', 1, 1, 1, 2),
(UUID(), 58.40, 6.50, 64.90, 'CONFIRMADO', UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, NULL, '04077-001', 'Alameda dos Maracatins', '200', NULL, 'Moema', 2, 2, 2, 1),
(UUID(), 90.50, 4.00, 94.50, 'ENTREGUE', UTC_TIMESTAMP(), UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, '22250-040', 'Rua Voluntários da Pátria', '78', NULL, 'Botafogo', 1, 1, 1, 2),
(UUID(), 77.00, 12.00, 89.00, 'CRIADO', UTC_TIMESTAMP(), NULL, NULL, NULL, '04551-060', 'Rua Funchal', '100', NULL, 'Vila Olímpia', 2, 2, 2, 1),
(UUID(), 62.80, 5.00, 67.80, 'CONFIRMADO', UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, NULL, '03110-000', 'Rua da Mooca', '450', NULL, 'Mooca', 1, 1, 1, 2),
(UUID(), 180.00, 15.00, 195.00, 'ENTREGUE', UTC_TIMESTAMP(), UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, '30120-060', 'Rua Bahia', '88', NULL, 'Centro', 2, 2, 2, 1),
(UUID(), 95.00, 7.90, 102.90, 'CRIADO', UTC_TIMESTAMP(), NULL, NULL, NULL, '02020-010', 'Rua Voluntários da Pátria', '321', NULL, 'Santana', 1, 1, 1, 2),
(UUID(), 41.30, 6.00, 47.30, 'CONFIRMADO', UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, NULL, '20735-100', 'Rua Dias da Cruz', '90', NULL, 'Méier', 2, 2, 2, 1),
(UUID(), 150.90, 10.30, 161.20, 'ENTREGUE', UTC_TIMESTAMP(), UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, '70710-540', 'CLN 210', '15', NULL, 'Asa Norte', 1, 1, 1, 2),
(UUID(), 88.80, 3.50, 92.30, 'CRIADO', UTC_TIMESTAMP(), NULL, NULL, NULL, '11013-400', 'Av. Ana Costa', '400', NULL, 'Santos', 2, 2, 2, 1),

(UUID(), 56.70, 7.40, 64.10, 'CONFIRMADO', UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, NULL, '04538-132', 'Rua João Cachoeira', '700', NULL, 'Itaim Bibi', 1, 1, 1, 2),
(UUID(), 129.90, 8.60, 138.50, 'ENTREGUE', UTC_TIMESTAMP(), UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, '70390-000', 'CLS 112', '25', NULL, 'Asa Sul', 2, 2, 2, 1),
(UUID(), 75.50, 4.50, 80.00, 'CRIADO', UTC_TIMESTAMP(), NULL, NULL, NULL, '30170-120', 'Rua Curitiba', '55', NULL, 'Lourdes', 1, 1, 1, 2),
(UUID(), 112.00, 11.00, 123.00, 'CONFIRMADO', UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, NULL, '80420-090', 'Av. Sete de Setembro', '150', NULL, 'Batel', 2, 2, 2, 1),
(UUID(), 65.90, 5.90, 71.80, 'ENTREGUE', UTC_TIMESTAMP(), UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, '05015-000', 'Rua Cardoso de Almeida', '99', NULL, 'Perdizes', 1, 1, 1, 2),
(UUID(), 49.90, 6.20, 56.10, 'CRIADO', UTC_TIMESTAMP(), NULL, NULL, NULL, '01415-001', 'Av. Paulista', '900', NULL, 'Jardins', 2, 2, 2, 1),
(UUID(), 170.00, 13.00, 183.00, 'CONFIRMADO', UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, NULL, '01308-000', 'Rua Augusta', '200', NULL, 'Jardins', 1, 1, 1, 2),
(UUID(), 99.90, 7.80, 107.70, 'ENTREGUE', UTC_TIMESTAMP(), UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, '01503-000', 'Rua Galvão Bueno', '650', NULL, 'Liberdade', 2, 2, 2, 1),
(UUID(), 52.00, 4.80, 56.80, 'CRIADO', UTC_TIMESTAMP(), NULL, NULL, NULL, '80020-310', 'Rua XV de Novembro', '150', NULL, 'Centro', 1, 1, 1, 2),
(UUID(), 143.50, 9.00, 152.50, 'CONFIRMADO', UTC_TIMESTAMP(), UTC_TIMESTAMP(), NULL, NULL, '90110-120', 'Av. Getúlio Vargas', '400', NULL, 'Menino Deus', 2, 2, 2, 1);

INSERT INTO item_pedido (quantidade, preco_unitario, preco_total, observacao, produto_id, pedido_id)
VALUES (1, 62.90, 62.90, 'Sem pimenta', 1, 1);