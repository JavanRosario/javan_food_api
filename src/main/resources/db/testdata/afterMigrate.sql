
SET FOREIGN_KEY_CHECKS = 0;


DELETE FROM cidade;
DELETE FROM cozinha;
DELETE FROM estado;	
DELETE FROM forma_pagamento;
DELETE FROM grupo;
DELETE FROM grupo_permissao;
DELETE FROM permissao;
DELETE FROM produto;
DELETE FROM restaurante;
DELETE FROM restaurante_forma_pagamento;
DELETE FROM usuario;
DELETE FROM usuario_grupo;


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


SET FOREIGN_KEY_CHECKS = 1;


insert into estado (nome) values ('Rio de Janeiro');
insert into estado (nome) values ('São Paulo');
insert into estado (nome) values ('Paraná');

insert into permissao (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into cidade (nome, estado_id) values ('Rio de Janeiro', 1);
insert into cidade (nome, estado_id) values ('São Paulo', 2);
insert into cidade (nome, estado_id) values ('Curitiba', 3);

insert into cozinha (nome) values ('Italiana');
insert into cozinha (nome) values ('Japonesa');
insert into cozinha (nome) values ('Brasileira');

insert into forma_pagamento (descricao) values ('Débito');
insert into forma_pagamento (descricao) values ('Crédito');
insert into forma_pagamento (descricao) values ('PIX');

insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, data_cadastro, data_atualizacao) 
values ('La Tavola', 5.50, 1, 1, 'Centro', '01001-000', 'Sala 101', 'Rua Itália', '100', UTC_TIMESTAMP(), UTC_TIMESTAMP());
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, data_cadastro, data_atualizacao) 
values ('Sushi House', 6.90, 2, 2, 'Liberdade', '01503-020', 'Loja B', 'Rua dos Orientais', '250', UTC_TIMESTAMP(), UTC_TIMESTAMP());
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, data_cadastro, data_atualizacao) 
values ('Sabor Brasil', 3.10, 3, 3, 'Centro', '80040-010', NULL, 'Rua do Comércio', '15', UTC_TIMESTAMP(), UTC_TIMESTAMP());

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 2), (2, 3), (3, 1), (3, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110.00, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sushi variado', 'Combo com 20 peças de sushi e sashimi', 95.00, 1, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Feijoada completa', 'Feijoada tradicional com acompanhamentos', 45.00, 1, 3);