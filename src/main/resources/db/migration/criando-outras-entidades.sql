create table forma_pagamento(
	id bigint not null auto_increment,
	descricao(60) not null,
	primary key (id)
)engine