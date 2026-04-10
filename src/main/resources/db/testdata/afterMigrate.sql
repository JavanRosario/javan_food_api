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
FROM usuario;
DELETE
FROM usuario_grupo;

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

SET
FOREIGN_KEY_CHECKS = 1;

INSERT INTO usuario (nome, email, senha, data_cadastro)
values ('javan', 'corno@gmail.com', '123', UTC_TIMESTAMP());
INSERT
INTO estado (nome)
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

INSERT INTO restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_bairro, endereco_cep,
                         endereco_complemento, endereco_logradouro, endereco_numero, data_cadastro, data_atualizacao,
                         ativo)
VALUES ('Trattoria Del Nonno', 8.90, 1, 3, 'Jardins', '01308-000', 'Sobrado', 'Rua Augusta', '1420', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true);

INSERT INTO restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_bairro, endereco_cep,
                         endereco_complemento, endereco_logradouro, endereco_numero, data_cadastro, data_atualizacao,
                         ativo)
VALUES ('Nakamura Sushi', 6.50, 2, 3, 'Liberdade', '01501-000', 'Loja 12', 'Rua Galvão Bueno', '540', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true);

INSERT INTO restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_bairro, endereco_cep,
                         endereco_complemento, endereco_logradouro, endereco_numero, data_cadastro, data_atualizacao,
                         ativo)
VALUES ('Fogão de Minas', 4.00, 3, 7, 'Savassi', '30130-170', NULL, 'Rua Pernambuco', '200', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true);

INSERT INTO restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_bairro, endereco_cep,
                         endereco_complemento, endereco_logradouro, endereco_numero, data_cadastro, data_atualizacao,
                         ativo)
VALUES ('La Brasserie', 12.00, 6, 1, 'Ipanema', '22410-003', 'Cobertura', 'Rua Visconde de Pirajá', '330',
        UTC_TIMESTAMP(), UTC_TIMESTAMP(), true);

INSERT INTO restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_bairro, endereco_cep,
                         endereco_complemento, endereco_logradouro, endereco_numero, data_cadastro, data_atualizacao,
                         ativo)
VALUES ('Al Jannat', 7.00, 4, 5, 'Centro', '80020-180', NULL, 'Rua XV de Novembro', '890', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true);

INSERT INTO restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_bairro, endereco_cep,
                         endereco_complemento, endereco_logradouro, endereco_numero, data_cadastro, data_atualizacao,
                         ativo)
VALUES ('El Rancho', 9.50, 5, 8, 'Moinhos de Vento', '90570-020', 'Sala 3', 'Rua Padre Chagas', '150', UTC_TIMESTAMP(),
        UTC_TIMESTAMP(), true);

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

INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id)
VALUES ('Spaghetti alla Carbonara', 'Massa artesanal com guanciale, ovo e pecorino romano', 62.90, 1, 1);
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