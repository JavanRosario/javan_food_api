insert into estado (nome) values ('Rua José Vaz'),('Avenida Paulista'),('Avenida Brasil'),('Rua das Flores'),('Rua das Laranjeiras'),('Rua Augusta'),('Avenida Atlântica'),('Rua Oscar Freire'),('Avenida Ipiranga'),('Rua Consolação');

insert into cidade (nome, estado_id) values ('Rio de Janeiro', 1),('São Paulo', 2),('Curitiba', 3),('Belo Horizonte', 4),('Porto Alegre', 5),('Brasília', 6),('Salvador', 7),('Fortaleza', 8),('Recife', 9),('Manaus', 10);

insert into cozinha (nome) values ('italiana'),('japonesa'),('mexicana'),('francesa'),('brasileira'),('chinesa'),('tailandesa'),('indiana'),('árabe'),('peruana');

insert into forma_pagamento (descricao) values ('debito'),('credito'),('dinheiro'),('pix'),('vale refeicao'),('boleto'),('paypal'),('apple pay'),('google pay'),('transferencia');

insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero) values ('La Tavola', 5.5, 1, 1, 'Centro', '01001-000', 'Sala 101', 'Rua Itália', '100'),('Sushi House', 6.9, 2, 2, 'Liberdade', '01503-020', 'Loja B', 'Rua dos Orientais', '250'),('El Sombrero', 4.3, 3, 3, 'Vila México', '03045-110', 'Fundos', 'Avenida Central', '78'),('Le Gourmet', 8.2, 4, 4, 'Jardins', '01410-200', 'Cobertura', 'Alameda França', '900'),('Sabor Brasil', 3.1, 5, 5, 'Centro', '20040-010', NULL, 'Rua do Comércio', '15'),('Dragon Wok', 7.5, 6, 6, 'Chinatown', '01310-100', '2º andar', 'Rua Dragão', '666'),('Thai Spice', 6.2, 7, 7, 'Bela Vista', '01311-000', NULL, 'Rua Siam', '88'),('Taj Mahal', 5.8, 8, 8, 'Centro', '30110-080', 'Loja 3', 'Avenida Índia', '420'),('Kebab Palace', 4.9, 9, 9, 'Vila Árabe', '04020-050', 'Quiosque', 'Rua Istambul', '77'),('Cevicheria Lima', 8.5, 10, 10, 'Copacabana', '22040-002', 'Térreo', 'Avenida Pacífico', '999');

insert into permisao (nome,descricao) values ('admin','acesso total'),('gerente','acesso restrito'),('cliente','acesso basico'),('cozinheiro','acesso cozinha'),('entregador','acesso entregas'),('supervisor','supervisao geral'),('atendente','atendimento ao cliente'),('financeiro','acesso financeiro'),('marketing','acesso marketing'),('estoquista','controle de estoque');

insert into restaurante_forma_pagamento (restaurante_id,forma_pagamento_id) values (1,1),(1,2),(1,3),(2,3),(3,2),(3,3);
