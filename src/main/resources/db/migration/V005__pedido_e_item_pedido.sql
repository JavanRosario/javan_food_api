create table item_pedido(
id bigint not null auto_increment, 
quantidade smallint(6) not null,
preco_unitario decimal(10, 2) not NULL,
preco_total decimal(10, 2)not NULL ,
observacao varchar(255) not null,

produto_id bigint not null,
pedido_id bigint not null,

constraint fk_item_pedido_produto foreign key(produto_id) references produto(id),

primary key (id),
unique key uk_item_pedido_produto (produto_id,pedido_id)
)engine = InnoDB default charset = utf8;

create table pedido (
id bigint not null auto_increment,
subtotal decimal(10, 2) not null, 
taxa_frete decimal(10, 2) not null,
valor_total decimal (10,
2) not null,

forma_pagamento_id bigint not null,
restaurante_id bigint not null,
usuario_id bigint not null,

endereco_cidade_id bigint(20) not null,
endereco_cep varchar (9),
endereco_logradouro varchar (100),
endereco_numero varchar (20),
endereco_complemento varchar (60),
endereco_bairro varchar (60),

status varchar(10) not null,
data_criacao datetime not null,
data_confirmacao datetime not null,
data_cancelamento datetime not null,
data_entrega datetime,

constraint fk_pedido_endereco_cidade foreign key (endereco_cidade_id) references cidade (id),
constraint fk_pedido_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id),
constraint fk_pedido_restaurante foreign key (restaurante_id) references restaurante (id),
constraint fk_pedido_usuario foreign key (usuario_id) references usuario (id),
primary key (id)
)engine = InnoDB default charset = utf8;