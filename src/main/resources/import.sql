insert into cozinha (nome) values ('italiana'),('japonesa'),('mexicana'),('francesa'),('brasileira');
insert into restaurante (nome, taxa_frete,cozinha_id) values ('la tavola', 5.5,1), ('sushi house', 6.9,2), ('el sombrero', 4.3,3), ('le gourmet', 8.2,4), ('sabor brasil', 3.1,5);
insert into forma_pagamento (descricao) values ('debito'),('credito'),('dinheiro'),('pix'),('vale refeicao');
insert into permisao (nome,descricao) values ('admin','acesso total'), ('gerente','acesso restrito'),('cliente','acesso basico'),('cozinheiro','acesso cozinha'),('entregador','acesso entregas');
insert into endereco (nome) values ('Rua José Vaz'),('Avenida Paulista'),('Avenida Brasil'),('Rua das Flores'),('Rua das Laranjeiras');
insert into cidade (nome, endereco_id) values ('Rio de Janeiro', 1),('São Paulo', 2),('Curitiba', 3),('Belo Horizonte', 4),('Porto Alegre', 5);
