insert into estado (nome) values ('Rua José Vaz'),('Avenida Paulista'),('Avenida Brasil'),('Rua das Flores'),('Rua das Laranjeiras'),('Rua Augusta'),('Avenida Atlântica'),('Rua Oscar Freire'),('Avenida Ipiranga'),('Rua Consolação');

insert into permissao ( nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao ( nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');
insert into cidade (nome, estado_id) values ('Rio de Janeiro', 1),('São Paulo', 2),('Curitiba', 3),('Belo Horizonte', 4),('Porto Alegre', 5),('Brasília', 6),('Salvador', 7),('Fortaleza', 8),('Recife', 9),('Manaus', 10);

insert into cozinha (nome) values ('italiana'),('japonesa'),('mexicana'),('francesa'),('brasileira'),('chinesa'),('tailandesa'),('indiana'),('árabe'),('peruana');

insert into forma_pagamento (descricao) values ('debito'),('credito'),('dinheiro'),('pix'),('vale refeicao'),('boleto'),('paypal'),('apple pay'),('google pay'),('transferencia');

insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, data_cadastro, data_atualizacao) values ('La Tavola', 5.5, 1, 1, 'Centro', '01001-000', 'Sala 101', 'Rua Itália', '100', UTC_TIMESTAMP(), UTC_TIMESTAMP()),('Sushi House', 6.9, 2, 2, 'Liberdade', '01503-020', 'Loja B', 'Rua dos Orientais', '250', UTC_TIMESTAMP(), UTC_TIMESTAMP()),('El Sombrero', 4.3, 3, 3, 'Vila México', '03045-110', 'Fundos', 'Avenida Central', '78', UTC_TIMESTAMP(), UTC_TIMESTAMP()),('Le Gourmet', 8.2, 4, 4, 'Jardins', '01410-200', 'Cobertura', 'Alameda França', '900', UTC_TIMESTAMP(), UTC_TIMESTAMP()),('Sabor Brasil', 3.1, 5, 5, 'Centro', '20040-010', NULL, 'Rua do Comércio', '15', UTC_TIMESTAMP(), UTC_TIMESTAMP()),('Dragon Wok', 7.5, 6, 6, 'Chinatown', '01310-100', '2º andar', 'Rua Dragão', '666', UTC_TIMESTAMP(), UTC_TIMESTAMP()),('Thai Spice', 6.2, 7, 7, 'Bela Vista', '01311-000', NULL, 'Rua Siam', '88', UTC_TIMESTAMP(), UTC_TIMESTAMP()),('Taj Mahal', 5.8, 8, 8, 'Centro', '30110-080', 'Loja 3', 'Avenida Índia', '420', UTC_TIMESTAMP(), UTC_TIMESTAMP()),('Kebab Palace', 4.9, 9, 9, 'Vila Árabe', '04020-050', 'Quiosque', 'Rua Istambul', '77', UTC_TIMESTAMP(), UTC_TIMESTAMP()),('Cevicheria Lima', 8.5, 10, 10, 'Copacabana', '22040-002', 'Térreo', 'Avenida Pacífico', '999', UTC_TIMESTAMP(), UTC_TIMESTAMP());


insert into restaurante_forma_pagamento (restaurante_id,forma_pagamento_id) values (1,1),(1,2),(1,3),(2,3),(3,2),(3,3);


insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);
