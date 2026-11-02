truncate table
    item_pedido,
    pedido,
    usuario_grupo,
    usuario,
    restaurante_usuario_responsavel,
    restaurante_forma_pagamento,
    restaurante,
    produto,
    permissao,
    grupo_permissao,
    grupo,
    forma_pagamento,
    cozinha,
    cidade,
    estado
restart identity cascade;


-- =========================================================
-- ESTADOS
-- =========================================================

insert into estado (nome)
values ('Rio de Janeiro'),
       ('São Paulo'),
       ('Paraná'),
       ('Minas Gerais'),
       ('Rio Grande do Sul'),
       ('Distrito Federal');


-- =========================================================
-- CIDADES
-- =========================================================

insert into cidade (nome, estado_id)
values ('Rio de Janeiro', 1),
       ('Niterói', 1),
       ('São Paulo', 2),
       ('Campinas', 2),
       ('Curitiba', 3),
       ('Londrina', 3),
       ('Belo Horizonte', 4),
       ('Porto Alegre', 5),
       ('Brasília', 6);


-- =========================================================
-- COZINHAS
-- =========================================================

insert into cozinha (nome)
values ('Italiana'),
       ('Japonesa'),
       ('Brasileira'),
       ('Árabe'),
       ('Mexicana'),
       ('Francesa');


-- =========================================================
-- FORMAS DE PAGAMENTO
-- =========================================================

insert into forma_pagamento (descricao)
values ('Dinheiro'),
       ('Débito'),
       ('Crédito'),
       ('PIX'),
       ('Vale Refeição');


-- =========================================================
-- PERMISSÕES
-- =========================================================

insert into permissao (nome, descricao)
values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas'),
       ('EDITAR_COZINHAS', 'Permite editar cozinhas'),
       ('CONSULTAR_RESTAURANTES', 'Permite consultar restaurantes'),
       ('EDITAR_RESTAURANTES', 'Permite editar restaurantes'),
       ('CONSULTAR_PEDIDOS', 'Permite consultar pedidos'),
       ('GERENCIAR_PEDIDOS', 'Permite gerenciar pedidos');


-- =========================================================
-- GRUPOS
-- =========================================================

insert into grupo (nome)
values ('ADMIN'),
       ('GERENTE'),
       ('ATENDENTE'),
       ('FINANCEIRO');


-- =========================================================
-- GRUPO x PERMISSÃO
-- =========================================================

insert into grupo_permissao (grupo_id, permissao_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),

       (2, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (2, 5),
       (2, 6),

       (3, 3),
       (3, 5),
       (3, 6),

       (4, 1),
       (4, 3),
       (4, 5);


-- =========================================================
-- USUÁRIOS
-- =========================================================

insert into usuario (nome,
                     email,
                     senha,
                     data_cadastro)
values ('Javan Oliveira', 'javan@javanfood.com', '123456', current_timestamp),
       ('Ana Lima', 'ana@javanfood.com', '123456', current_timestamp);


-- =========================================================
-- USUÁRIO x GRUPO
-- =========================================================

insert into usuario_grupo (usuario_id, grupo_id)
values (1, 1),
       (1, 2),
       (2, 3);


-- =========================================================
-- RESTAURANTES
-- =========================================================

insert into restaurante (nome,
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
values ('Trattoria Del Nonno',
        8.90,
        1,
        3,
        'Jardins',
        '01308-000',
        'Sobrado',
        'Rua Augusta',
        '1420',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Nakamura Sushi',
        6.50,
        2,
        3,
        'Liberdade',
        '01501-000',
        'Loja 12',
        'Rua Galvão Bueno',
        '540',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Fogão de Minas',
        4.00,
        3,
        7,
        'Savassi',
        '30130-170',
        null,
        'Rua Pernambuco',
        '200',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('La Brasserie',
        12.00,
        6,
        1,
        'Ipanema',
        '22410-003',
        'Cobertura',
        'Rua Visconde de Pirajá',
        '330',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Al Jannat',
        7.00,
        4,
        5,
        'Centro',
        '80020-180',
        null,
        'Rua XV de Novembro',
        '890',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('El Rancho',
        9.50,
        5,
        8,
        'Moinhos de Vento',
        '90570-020',
        'Sala 3',
        'Rua Padre Chagas',
        '150',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Pizzaria Napoli',
        7.90,
        1,
        3,
        'Centro',
        '01001-000',
        'Loja A',
        'Rua das Flores',
        '45',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Sushi House',
        12.50,
        2,
        3,
        'Jardins',
        '01415-001',
        null,
        'Av. Paulista',
        '1200',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Burger Prime',
        6.00,
        3,
        1,
        'Copacabana',
        '22040-002',
        null,
        'Rua Barata Ribeiro',
        '350',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Cantina Italiana',
        10.00,
        1,
        3,
        'Bela Vista',
        '01310-100',
        'Fundos',
        'Rua Treze de Maio',
        '800',
        current_timestamp,
        current_timestamp,
        true,
        false),
       ('Tempero Mineiro',
        5.50,
        3,
        7,
        'Savassi',
        '30140-091',
        null,
        'Rua Pernambuco',
        '90',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Churrascaria Boi Forte',
        15.00,
        3,
        9,
        'Asa Sul',
        '70040-010',
        null,
        'SQS 104',
        '20',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('China Express',
        8.20,
        2,
        3,
        'Liberdade',
        '01503-000',
        null,
        'Rua Galvão Bueno',
        '100',
        current_timestamp,
        current_timestamp,
        false,
        false),
       ('Taco Loco',
        9.90,
        5,
        8,
        'Menino Deus',
        '90110-120',
        null,
        'Av. Getúlio Vargas',
        '1000',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Açaí Point',
        4.00,
        3,
        3,
        'Pinheiros',
        '05422-000',
        null,
        'Rua dos Pinheiros',
        '230',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Pastel do Zé',
        3.50,
        3,
        1,
        'Tijuca',
        '20520-010',
        null,
        'Rua Conde de Bonfim',
        '650',
        current_timestamp,
        current_timestamp,
        true,
        false),
       ('Espetinho Grill',
        6.70,
        3,
        5,
        'Centro',
        '80020-310',
        null,
        'Rua XV de Novembro',
        '100',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Padaria Imperial',
        2.99,
        1,
        7,
        'Funcionários',
        '30130-150',
        null,
        'Av. Brasil',
        '200',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Casa do Café',
        1.50,
        3,
        3,
        'Moema',
        '04077-001',
        null,
        'Alameda dos Maracatins',
        '550',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Nordestino Sabor',
        8.80,
        3,
        9,
        'Asa Sul',
        '70390-000',
        null,
        'CLS 112',
        '15',
        current_timestamp,
        current_timestamp,
        false,
        false),
       ('Restaurante do Porto',
        13.90,
        3,
        8,
        'Cidade Baixa',
        '90050-170',
        null,
        'Rua Lima e Silva',
        '400',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Massa & Cia',
        9.00,
        1,
        3,
        'Vila Mariana',
        '04105-000',
        null,
        'Rua Domingos de Morais',
        '500',
        current_timestamp,
        current_timestamp,
        true,
        false),
       ('Thai Food',
        18.50,
        2,
        3,
        'Itaim Bibi',
        '04538-132',
        null,
        'Rua João Cachoeira',
        '600',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Bistrô Paris',
        22.00,
        6,
        5,
        'Batel',
        '80420-090',
        null,
        'Av. Sete de Setembro',
        '1800',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Sabor Árabe',
        7.80,
        4,
        7,
        'Lourdes',
        '30170-120',
        null,
        'Rua Curitiba',
        '450',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Dogão Express',
        5.00,
        3,
        1,
        'Méier',
        '20735-100',
        null,
        'Rua Dias da Cruz',
        '890',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Peixe & Mar',
        14.50,
        3,
        3,
        'Santos',
        '11013-400',
        null,
        'Av. Ana Costa',
        '150',
        current_timestamp,
        current_timestamp,
        true,
        false),
       ('Vegan Life',
        11.20,
        3,
        3,
        'Perdizes',
        '05015-000',
        null,
        'Rua Cardoso de Almeida',
        '980',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Frango Crocante',
        3,
        3,
        8,
        'Floresta',
        '90220-001',
        null,
        'Rua Ramiro Barcelos',
        '321',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Doces da Vovó',
        4,
        3,
        3,
        'Mooca',
        '03110-000',
        null,
        'Rua da Mooca',
        '700',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Coxinha Mania',
        2.50,
        3,
        7,
        'Centro',
        '30120-060',
        null,
        'Rua Bahia',
        '130',
        current_timestamp,
        current_timestamp,
        false,
        false),
       ('Pizza Suprema',
        8.60,
        1,
        1,
        'Botafogo',
        '22250-040',
        null,
        'Rua Voluntários da Pátria',
        '250',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Yakisoba Mix',
        10.30,
        2,
        9,
        'Asa Norte',
        '70710-540',
        null,
        'CLN 210',
        '18',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Empório Gourmet',
        16.90,
        3,
        3,
        'Vila Olímpia',
        '04551-060',
        null,
        'Rua Funchal',
        '900',
        current_timestamp,
        current_timestamp,
        true,
        true),
       ('Burguer do Chef',
        7.40,
        3,
        3,
        'Santana',
        '02020-010',
        null,
        'Rua Voluntários da Pátria',
        '1400',
        current_timestamp,
        current_timestamp,
        true,
        false);


-- =========================================================
-- RESTAURANTE x FORMA DE PAGAMENTO
-- =========================================================

insert into restaurante_forma_pagamento (restaurante_id,
                                         forma_pagamento_id)
values (1, 2),
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
       (4, 4),

       (5, 1),
       (5, 2),
       (5, 4),

       (6, 2),
       (6, 3),
       (6, 4);


-- =========================================================
-- RESPONSÁVEIS
-- =========================================================

insert into restaurante_usuario_responsavel (restaurante_id,
                                             usuario_id)
values (1, 1),
       (2, 1),
       (3, 2);


-- =========================================================
-- PRODUTOS
-- =========================================================

insert into produto (nome,
                     descricao,
                     preco,
                     ativo,
                     restaurante_id)
values ('Spaghetti alla Carbonara',
        'Massa artesanal com guanciale, ovo e pecorino romano',
        62.90,
        false,
        1),
       ('Risotto ai Funghi Porcini',
        'Arroz arbóreo cremoso com cogumelos porcini importados',
        74.90,
        true,
        1),
       ('Tiramisù',
        'Clássico italiano com mascarpone e café espresso',
        32.00,
        true,
        1),
       ('Combo Executivo 30 peças',
        'Seleção de niguiris, uramakis e hossomakis',
        98.00,
        true,
        2),
       ('Sashimi de Salmão 10 fatias',
        'Salmão norueguês fresco fatiado na hora',
        54.90,
        true,
        2),
       ('Missoshiru',
        'Sopa de missô com tofu, wakame e cebolinha',
        14.00,
        true,
        2),
       ('Frango ao Molho Pardo',
        'Frango caipira ao molho tradicional mineiro com angu',
        49.90,
        true,
        3),
       ('Feijão Tropeiro',
        'Feijão com farinha, couve, bacon e linguiça',
        38.90,
        true,
        3),
       ('Pão de Queijo Artesanal 6 un',
        'Pão de queijo mineiro assado na hora',
        22.00,
        true,
        3),
       ('Magret de Canard',
        'Peito de pato ao molho de laranja com purê de batata-doce',
        128.00,
        true,
        4),
       ('Soupe à l''oignon',
        'Sopa de cebola gratinada com croutons e gruyère',
        48.00,
        true,
        4),
       ('Crème Brûlée',
        'Creme de baunilha com casquinha de açúcar caramelizado',
        36.00,
        true,
        4),
       ('Kafta Grelhada',
        'Espeto de carne moída temperada com especiarias árabes',
        44.90,
        true,
        5),
       ('Esfiha Aberta de Carne 6 un',
        'Esfiha tradicional com carne temperada e tomate',
        29.90,
        true,
        5),
       ('Homus com Pão Sírio',
        'Pasta de grão-de-bico com azeite e páprica',
        24.00,
        true,
        5),
       ('Tacos de Carnitas',
        '3 tacos de porco desfiado com guacamole e pico de gallo',
        42.00,
        true,
        6),
       ('Burrito de Frango',
        'Wrap recheado com frango, arroz, feijão preto e queijo',
        38.00,
        true,
        6),
       ('Churros com Doce de Leite',
        'Churros crocantes com doce de leite argentino',
        26.00,
        true,
        6);


-- =========================================================
-- PEDIDOS
-- =========================================================

insert into pedido (codigo,
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
                    usuario_cliente_id)
values (gen_random_uuid()::text,
        45.90,
        8.90,
        54.80,
        'CRIADO',
        current_timestamp,
        null,
        null,
        null,
        '01308-000',
        'Rua Augusta',
        '100',
        null,
        'Jardins',
        3,
        1,
        1,
        1),
       (gen_random_uuid()::text,
        89.90,
        6.50,
        96.40,
        'CONFIRMADO',
        current_timestamp,
        current_timestamp,
        null,
        null,
        '22040-002',
        'Rua Barata Ribeiro',
        '250',
        null,
        'Copacabana',
        1,
        2,
        2,
        2),
       (gen_random_uuid()::text,
        125.00,
        9.90,
        134.90,
        'ENTREGUE',
        current_timestamp,
        current_timestamp,
        current_timestamp,
        null,
        '01501-000',
        'Rua Galvão Bueno',
        '78',
        null,
        'Liberdade',
        3,
        1,
        1,
        2),
       (gen_random_uuid()::text,
        39.90,
        4.00,
        43.90,
        'CANCELADO',
        current_timestamp,
        current_timestamp,
        null,
        current_timestamp,
        '80020-180',
        'Rua XV de Novembro',
        '45',
        null,
        'Centro',
        5,
        2,
        2,
        1),
       (gen_random_uuid()::text,
        68.50,
        7.00,
        75.50,
        'CRIADO',
        current_timestamp,
        null,
        null,
        null,
        '90570-020',
        'Rua Padre Chagas',
        '55',
        'Sala 1',
        'Moinhos de Vento',
        8,
        1,
        2,
        2),
       (gen_random_uuid()::text,
        102.90,
        12.50,
        115.40,
        'CONFIRMADO',
        current_timestamp,
        current_timestamp,
        null,
        null,
        '01310-100',
        'Rua Treze de Maio',
        '120',
        null,
        'Bela Vista',
        3,
        2,
        1,
        1),
       (gen_random_uuid()::text,
        54.30,
        5.50,
        59.80,
        'ENTREGUE',
        current_timestamp,
        current_timestamp,
        current_timestamp,
        null,
        '30130-170',
        'Rua Pernambuco',
        '78',
        null,
        'Savassi',
        7,
        1,
        2,
        2),
       (gen_random_uuid()::text,
        140.00,
        15.00,
        155.00,
        'CRIADO',
        current_timestamp,
        null,
        null,
        null,
        '70040-010',
        'SQS 104',
        '14',
        null,
        'Asa Sul',
        9,
        2,
        1,
        1),
       (gen_random_uuid()::text,
        81.20,
        6.70,
        87.90,
        'CONFIRMADO',
        current_timestamp,
        current_timestamp,
        null,
        null,
        '20520-010',
        'Rua Conde de Bonfim',
        '200',
        null,
        'Tijuca',
        1,
        1,
        2,
        2),
       (gen_random_uuid()::text,
        199.90,
        9.50,
        209.40,
        'ENTREGUE',
        current_timestamp,
        current_timestamp,
        current_timestamp,
        null,
        '05422-000',
        'Rua dos Pinheiros',
        '80',
        null,
        'Pinheiros',
        3,
        2,
        1,
        1),
       (gen_random_uuid()::text,
        35.00,
        8.90,
        43.90,
        'CRIADO',
        current_timestamp,
        null,
        null,
        null,
        '04105-000',
        'Rua Domingos de Morais',
        '55',
        null,
        'Vila Mariana',
        3,
        1,
        1,
        2),
       (gen_random_uuid()::text,
        58.40,
        6.50,
        64.90,
        'CONFIRMADO',
        current_timestamp,
        current_timestamp,
        null,
        null,
        '04077-001',
        'Alameda dos Maracatins',
        '200',
        null,
        'Moema',
        3,
        2,
        2,
        1),
       (gen_random_uuid()::text,
        90.50,
        4.00,
        94.50,
        'ENTREGUE',
        current_timestamp,
        current_timestamp,
        current_timestamp,
        null,
        '22250-040',
        'Rua Voluntários da Pátria',
        '78',
        null,
        'Botafogo',
        1,
        1,
        1,
        2),
       (gen_random_uuid()::text,
        77.00,
        12.00,
        89.00,
        'CRIADO',
        current_timestamp,
        null,
        null,
        null,
        '04551-060',
        'Rua Funchal',
        '100',
        null,
        'Vila Olímpia',
        3,
        2,
        2,
        1),
       (gen_random_uuid()::text,
        62.80,
        5.00,
        67.80,
        'CONFIRMADO',
        current_timestamp,
        current_timestamp,
        null,
        null,
        '03110-000',
        'Rua da Mooca',
        '450',
        null,
        'Mooca',
        3,
        1,
        1,
        2),
       (gen_random_uuid()::text,
        180.00,
        15.00,
        195.00,
        'ENTREGUE',
        current_timestamp,
        current_timestamp,
        current_timestamp,
        null,
        '30120-060',
        'Rua Bahia',
        '88',
        null,
        'Centro',
        7,
        2,
        2,
        1),
       (gen_random_uuid()::text,
        95.00,
        7.90,
        102.90,
        'CRIADO',
        current_timestamp,
        null,
        null,
        null,
        '02020-010',
        'Rua Voluntários da Pátria',
        '321',
        null,
        'Santana',
        3,
        1,
        1,
        2),
       (gen_random_uuid()::text,
        41.30,
        6.00,
        47.30,
        'CONFIRMADO',
        current_timestamp,
        current_timestamp,
        null,
        null,
        '20735-100',
        'Rua Dias da Cruz',
        '90',
        null,
        'Méier',
        1,
        2,
        2,
        1),
       (gen_random_uuid()::text,
        150.90,
        10.30,
        161.20,
        'ENTREGUE',
        current_timestamp,
        current_timestamp,
        current_timestamp,
        null,
        '70710-540',
        'CLN 210',
        '15',
        null,
        'Asa Norte',
        9,
        1,
        1,
        2),
       (gen_random_uuid()::text,
        88.80,
        3.50,
        92.30,
        'CRIADO',
        current_timestamp,
        null,
        null,
        null,
        '11013-400',
        'Av. Ana Costa',
        '400',
        null,
        'Santos',
        3,
        2,
        2,
        1),
       (gen_random_uuid()::text,
        56.70,
        7.40,
        64.10,
        'CONFIRMADO',
        current_timestamp,
        current_timestamp,
        null,
        null,
        '04538-132',
        'Rua João Cachoeira',
        '700',
        null,
        'Itaim Bibi',
        3,
        1,
        1,
        2),
       (gen_random_uuid()::text,
        129.90,
        8.60,
        138.50,
        'ENTREGUE',
        current_timestamp,
        current_timestamp,
        current_timestamp,
        null,
        '70390-000',
        'CLS 112',
        '25',
        null,
        'Asa Sul',
        9,
        2,
        2,
        1),
       (gen_random_uuid()::text,
        75.50,
        4.50,
        80.00,
        'CRIADO',
        current_timestamp,
        null,
        null,
        null,
        '30170-120',
        'Rua Curitiba',
        '55',
        null,
        'Lourdes',
        7,
        1,
        1,
        2),
       (gen_random_uuid()::text,
        112.00,
        11.00,
        123.00,
        'CONFIRMADO',
        current_timestamp,
        current_timestamp,
        null,
        null,
        '80420-090',
        'Av. Sete de Setembro',
        '150',
        null,
        'Batel',
        5,
        2,
        2,
        1),
       (gen_random_uuid()::text,
        65.90,
        5.90,
        71.80,
        'ENTREGUE',
        current_timestamp,
        current_timestamp,
        current_timestamp,
        null,
        '05015-000',
        'Rua Cardoso de Almeida',
        '99',
        null,
        'Perdizes',
        3,
        1,
        1,
        2),
       (gen_random_uuid()::text,
        49.90,
        6.20,
        56.10,
        'CRIADO',
        current_timestamp,
        null,
        null,
        null,
        '01415-001',
        'Av. Paulista',
        '900',
        null,
        'Jardins',
        3,
        2,
        2,
        1),
       (gen_random_uuid()::text,
        170.00,
        13.00,
        183.00,
        'CONFIRMADO',
        current_timestamp,
        current_timestamp,
        null,
        null,
        '01308-000',
        'Rua Augusta',
        '200',
        null,
        'Jardins',
        3,
        1,
        1,
        2),
       (gen_random_uuid()::text,
        99.90,
        7.80,
        107.70,
        'ENTREGUE',
        current_timestamp,
        current_timestamp,
        current_timestamp,
        null,
        '01503-000',
        'Rua Galvão Bueno',
        '650',
        null,
        'Liberdade',
        3,
        2,
        2,
        1),
       (gen_random_uuid()::text,
        52.00,
        4.80,
        56.80,
        'CRIADO',
        current_timestamp,
        null,
        null,
        null,
        '80020-310',
        'Rua XV de Novembro',
        '150',
        null,
        'Centro',
        5,
        1,
        1,
        2),
       (gen_random_uuid()::text,
        143.50,
        9.00,
        152.50,
        'CONFIRMADO',
        current_timestamp,
        current_timestamp,
        null,
        null,
        '90110-120',
        'Av. Getúlio Vargas',
        '400',
        null,
        'Menino Deus',
        8,
        2,
        2,
        1);


-- =========================================================
-- ITENS DOS PEDIDOS
-- =========================================================

insert into item_pedido (quantidade,
                         preco_unitario,
                         preco_total,
                         observacao,
                         produto_id,
                         pedido_id)
values (1, 62.90, 62.90, 'Sem pimenta', 1, 1),
       (1, 98.00, 98.00, 'Sem wasabi', 4, 2),
       (1, 49.90, 49.90, 'Bem passado', 7, 3),
       (1, 48.00, 48.00, 'Pouco sal', 11, 4),
       (1, 44.90, 44.90, 'Sem cebola', 13, 5),
       (1, 42.00, 42.00, 'Pouco picante', 16, 6),
       (1, 74.90, 74.90, 'Sem cogumelos adicionais', 2, 7),
       (1, 38.00, 38.00, 'Sem queijo', 17, 8),
       (1, 54.90, 54.90, 'Bem gelado', 5, 9),
       (1, 32.00, 32.00, 'Sem açúcar extra', 3, 10),
       (1, 22.00, 22.00, 'Quentinho', 9, 11),
       (1, 14.00, 14.00, 'Pouco sal', 6, 12),
       (1, 36.00, 36.00, 'Sem açúcar adicional', 12, 13),
       (1, 29.90, 29.90, 'Bem assada', 14, 14),
       (1, 24.00, 24.00, 'Com bastante homus', 15, 15),
       (1, 44.90, 44.90, 'Sem pimenta', 13, 16),
       (1, 62.90, 62.90, 'Sem cebola', 1, 17),
       (1, 38.00, 38.00, 'Sem feijão', 17, 18);


-- =========================================================
-- DADOS EXTRAS PARA TESTES DE VOLUME
-- Adiciona restaurantes, produtos e usuários sem depender
-- de IDs fixos para os novos restaurantes.
-- =========================================================

-- USUÁRIOS EXTRAS
insert into usuario (nome, email, senha, data_cadastro)
values
    ('Carlos Mendes', 'carlos@javanfood.com', '123456', current_timestamp),
    ('Mariana Costa', 'mariana@javanfood.com', '123456', current_timestamp),
    ('Rafael Souza', 'rafael@javanfood.com', '123456', current_timestamp),
    ('Beatriz Santos', 'beatriz@javanfood.com', '123456', current_timestamp),
    ('Lucas Ferreira', 'lucas@javanfood.com', '123456', current_timestamp),
    ('Camila Oliveira', 'camila@javanfood.com', '123456', current_timestamp),
    ('Pedro Almeida', 'pedro@javanfood.com', '123456', current_timestamp),
    ('Juliana Ribeiro', 'juliana@javanfood.com', '123456', current_timestamp),
    ('Thiago Martins', 'thiago@javanfood.com', '123456', current_timestamp),
    ('Fernanda Lima', 'fernanda@javanfood.com', '123456', current_timestamp);

-- ASSOCIANDO OS NOVOS USUÁRIOS A GRUPOS
-- Os IDs 3 a 12 são gerados após os dois usuários iniciais,
-- pois a migration reinicia a identity no início.
insert into usuario_grupo (usuario_id, grupo_id)
values
    (3, 2), (4, 3), (5, 3), (6, 4), (7, 2),
    (8, 3), (9, 4), (10, 2), (11, 3), (12, 3);

-- RESTAURANTES EXTRAS
with novos_restaurantes (
                         nome, taxa_frete, cozinha_id, cidade_id, bairro, cep,
                         complemento, logradouro, numero, ativo, aberto
    ) as (
    values
        ('Cantinho da Nonna', 7.50, 1, 1, 'Tijuca', '20511-000', null, 'Rua Conde de Bonfim', '720', true, true),
        ('Sakura Oriental', 9.90, 2, 3, 'Liberdade', '01504-000', 'Loja 5', 'Rua da Glória', '250', true, true),
        ('Sabor do Sertão', 6.00, 3, 9, 'Asa Norte', '70750-000', null, 'CLN 208', '120', true, true),
        ('Bistrô das Montanhas', 11.50, 6, 7, 'Funcionários', '30140-000', null, 'Rua dos Aimorés', '650', true, true),
        ('Pita House', 5.90, 4, 1, 'Copacabana', '22050-000', null, 'Rua Santa Clara', '180', true, true),
        ('Taco de Ouro', 8.00, 5, 3, 'Pinheiros', '05422-010', null, 'Rua dos Pinheiros', '410', true, true),
        ('Pasta Fresca', 7.00, 1, 5, 'Batel', '80420-100', null, 'Rua Comendador Araújo', '300', true, true),
        ('Tempero da Praça', 4.50, 3, 2, 'Icaraí', '24230-000', null, 'Rua Ator Paulo Gustavo', '90', true, true),
        ('Nori & Cia', 10.00, 2, 8, 'Moinhos de Vento', '90540-000', null, 'Rua 24 de Outubro', '550', true, true),
        ('La Petite Cuisine', 13.00, 6, 3, 'Moema', '04080-000', null, 'Av. Moema', '700', true, true),
        ('Chapa Quente', 5.00, 1, 1, 'Méier', '20725-000', null, 'Rua Dias da Cruz', '410', true, false),
        ('Brasa & Lenha', 12.00, 3, 9, 'Asa Sul', '70200-000', null, 'CLS 205', '30', true, true),
        ('Empório Vegano', 8.50, 3, 3, 'Vila Madalena', '05435-000', null, 'Rua Harmonia', '220', true, true),
        ('Cozinha Caiçara', 14.00, 3, 3, 'Santos', '11060-000', null, 'Rua Goiás', '100', true, true),
        ('Doce Estação', 4.00, 3, 7, 'Centro', '30110-000', null, 'Rua da Bahia', '500', true, true)
)
insert into restaurante (
    nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_bairro,
    endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero,
    data_cadastro, data_atualizacao, ativo, aberto
)
select
    nome, taxa_frete, cozinha_id, cidade_id, bairro, cep,
    complemento, logradouro, numero,
    current_timestamp, current_timestamp, ativo, aberto
from novos_restaurantes;

-- PRODUTOS EXTRAS
-- Cria 6 produtos variados para cada restaurante existente.
-- O CROSS JOIN gera um volume maior de dados para paginação,
-- filtros, busca por nome, associação e testes de produtos ativos/inativos.
insert into produto (nome, descricao, preco, ativo, restaurante_id)
select
    case gs.produto_numero
        when 1 then 'Prato da Casa'
        when 2 then 'Combo Especial'
        when 3 then 'Porcao Tradicional'
        when 4 then 'Especialidade do Chef'
        when 5 then 'Menu Executivo'
        else 'Sobremesa da Casa'
        end || ' ' || lpad(r.id::text, 2, '0'),
    case gs.produto_numero
        when 1 then 'Preparado com ingredientes selecionados e temperos da casa.'
        when 2 then 'Combinação especial preparada diariamente pela equipe.'
        when 3 then 'Porção generosa, ideal para compartilhar.'
        when 4 then 'Receita exclusiva do chef com ingredientes frescos.'
        when 5 then 'Refeição completa acompanhada de guarnições.'
        else 'Sobremesa artesanal preparada diariamente.'
        end,
    (12.90 + (r.id * 1.37) + (gs.produto_numero * 4.25))::numeric(10,2),
    case
        when gs.produto_numero = 3 and r.id % 4 = 0 then false
        when gs.produto_numero = 5 and r.id % 7 = 0 then false
        else true
        end,
    r.id
from restaurante r
         cross join generate_series(1, 6) as gs(produto_numero);

-- MAIS FORMAS DE PAGAMENTO PARA OS RESTAURANTES EXTRAS
-- O bloco usa os restaurantes inseridos por nome para não fixar IDs.
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id)
select r.id, fp.id
from restaurante r
         cross join forma_pagamento fp
where r.nome in (
                 'Cantinho da Nonna', 'Sakura Oriental', 'Sabor do Sertão',
                 'Bistrô das Montanhas', 'Pita House', 'Taco de Ouro',
                 'Pasta Fresca', 'Tempero da Praça', 'Nori & Cia',
                 'La Petite Cuisine', 'Chapa Quente', 'Brasa & Lenha',
                 'Empório Vegano', 'Cozinha Caiçara', 'Doce Estação'
    )
  and fp.id in (2, 3, 4);

-- RESPONSÁVEIS PARA OS RESTAURANTES EXTRAS
insert into restaurante_usuario_responsavel (restaurante_id, usuario_id)
select r.id, u.id
from restaurante r
         join usuario u on u.email = case
                                         when r.nome in ('Cantinho da Nonna', 'Pasta Fresca', 'La Petite Cuisine') then 'carlos@javanfood.com'
                                         when r.nome in ('Sakura Oriental', 'Nori & Cia') then 'mariana@javanfood.com'
                                         when r.nome in ('Sabor do Sertão', 'Brasa & Lenha') then 'rafael@javanfood.com'
                                         when r.nome in ('Bistrô das Montanhas', 'Doce Estação') then 'beatriz@javanfood.com'
                                         when r.nome in ('Pita House', 'Taco de Ouro') then 'lucas@javanfood.com'
                                         when r.nome in ('Tempero da Praça', 'Chapa Quente') then 'camila@javanfood.com'
                                         else 'pedro@javanfood.com'
    end
where r.nome in (
                 'Cantinho da Nonna', 'Sakura Oriental', 'Sabor do Sertão',
                 'Bistrô das Montanhas', 'Pita House', 'Taco de Ouro',
                 'Pasta Fresca', 'Tempero da Praça', 'Nori & Cia',
                 'La Petite Cuisine', 'Chapa Quente', 'Brasa & Lenha',
                 'Empório Vegano', 'Cozinha Caiçara', 'Doce Estação'
    );


-- =========================================================
-- FORMAS DE PAGAMENTO PARA TODOS OS RESTAURANTES
-- Garante que cada restaurante tenha opções válidas para os
-- pedidos de demonstração, sem duplicar associações existentes.
-- =========================================================

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id)
select r.id, fp.id
from restaurante r
         cross join forma_pagamento fp
where not exists (
    select 1
    from restaurante_forma_pagamento rfp
    where rfp.restaurante_id = r.id
      and rfp.forma_pagamento_id = fp.id
);


-- =========================================================
-- PEDIDOS EXTRAS
-- Gera 4 pedidos por restaurante:
-- 1 CRIADO, 1 CONFIRMADO, 1 ENTREGUE e 1 CANCELADO.
-- Os pedidos ficam distribuídos entre os 12 usuários e as
-- formas de pagamento disponíveis para cada restaurante.
-- =========================================================

with pedidos_base as (
    select
        r.id as restaurante_id,
        r.taxa_frete,
        r.endereco_cep,
        r.endereco_logradouro,
        r.endereco_numero,
        r.endereco_complemento,
        r.endereco_bairro,
        r.endereco_cidade_id,
        gs.numero_pedido,
        ((r.id + gs.numero_pedido - 1) % 12) + 1 as usuario_cliente_id,
    ((r.id + gs.numero_pedido - 1) % 5) + 1 as forma_pagamento_id,
    case gs.numero_pedido
    when 1 then 'CRIADO'
    when 2 then 'CONFIRMADO'
    when 3 then 'ENTREGUE'
    else 'CANCELADO'
end as status_pedido,
        current_timestamp
            - (((r.id * 3 + gs.numero_pedido * 7) % 75) * interval '1 day')
            as data_criacao
    from restaurante r
    cross join generate_series(1, 4) as gs(numero_pedido)
),
itens_calculados as (
    select
        pb.restaurante_id,
        pb.numero_pedido,
        pr.preco,
        ((pb.numero_pedido + gi.numero_item) % 3) + 1 as quantidade
    from pedidos_base pb
    cross join generate_series(1, 3) as gi(numero_item)
    join lateral (
        select p.preco
        from produto p
        where p.restaurante_id = pb.restaurante_id
        order by p.id
        offset (
            (pb.numero_pedido + gi.numero_item - 2)
            % (select count(*) from produto px where px.restaurante_id = pb.restaurante_id)
        )
        limit 1
    ) pr on true
),
totais_pedido as (
    select
        restaurante_id,
        numero_pedido,
        sum(preco * quantidade)::numeric(10,2) as sub_total
    from itens_calculados
    group by restaurante_id, numero_pedido
)
insert into pedido (
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
select
    'DEMO-' || pb.restaurante_id || '-' || pb.numero_pedido,
    tp.sub_total,
    pb.taxa_frete,
    (tp.sub_total + pb.taxa_frete)::numeric(10,2),
    pb.status_pedido,
    pb.data_criacao,
    case
        when pb.status_pedido in ('CONFIRMADO', 'ENTREGUE', 'CANCELADO')
            then pb.data_criacao + interval '5 minutes'
    else null
end,
    case
        when pb.status_pedido = 'ENTREGUE'
            then pb.data_criacao + interval '50 minutes'
        else null
end,
    case
        when pb.status_pedido = 'CANCELADO'
            then pb.data_criacao + interval '15 minutes'
        else null
end,
    pb.endereco_cep,
    pb.endereco_logradouro,
    pb.endereco_numero,
    pb.endereco_complemento,
    pb.endereco_bairro,
    pb.endereco_cidade_id,
    pb.forma_pagamento_id,
    pb.restaurante_id,
    pb.usuario_cliente_id
from pedidos_base pb
join totais_pedido tp
  on tp.restaurante_id = pb.restaurante_id
 and tp.numero_pedido = pb.numero_pedido;


-- =========================================================
-- ITENS DOS PEDIDOS EXTRAS
-- Cada pedido recebe 3 itens associados a produtos do
-- próprio restaurante. Os preços e quantidades são os
-- mesmos usados no cálculo do subtotal acima.
-- =========================================================

with pedidos_demo as (
    select
        p.id as pedido_id,
        p.restaurante_id,
        split_part(p.codigo, '-', 3)::integer as numero_pedido
    from pedido p
    where p.codigo like 'DEMO-%'
),
     itens_calculados as (
         select
             pd.pedido_id,
             pr.id as produto_id,
             ((pd.numero_pedido + gi.numero_item) % 3) + 1 as quantidade,
    pr.preco,
    gi.numero_item
from pedidos_demo pd
    cross join generate_series(1, 3) as gi(numero_item)
    join lateral (
    select p.id, p.preco
    from produto p
    where p.restaurante_id = pd.restaurante_id
    order by p.id
    offset (
    (pd.numero_pedido + gi.numero_item - 2)
    % (select count(*) from produto px where px.restaurante_id = pd.restaurante_id)
    )
    limit 1
    ) pr on true
    )
insert into item_pedido (
    quantidade,
    preco_unitario,
    preco_total,
    observacao,
    produto_id,
    pedido_id
)
select
    ic.quantidade,
    ic.preco,
    (ic.preco * ic.quantidade)::numeric(10,2),
    case ic.numero_item
        when 1 then 'Item de demonstração - preparo padrão'
        when 2 then 'Item de demonstração - conferir embalagem'
        else 'Item de demonstração - acompanhar pedido'
        end,
    ic.produto_id,
    ic.pedido_id
from itens_calculados ic;
