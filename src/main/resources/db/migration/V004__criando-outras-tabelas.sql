	create table forma_pagamento(
		id bigint not null auto_increment,
		descricao varchar (60) not null,
		
		primary key (id)
	)engine = InnoDB default charset = utf8;
	
	CREATE TABLE grupo (
	    id BIGINT NOT NULL AUTO_INCREMENT, 
	    nome VARCHAR(60) NOT NULL,
	    
	    PRIMARY KEY (id)
	) ENGINE = InnoDB DEFAULT CHARSET = utf8;
	
	create table permissao(
	id bigint not null auto_increment, 
	descricao varchar(60) not null,	
	nome varchar(60) not null,
	
	primary key (id)
	)engine = InnoDB default charset = utf8;
	
	create table usuario (
		id bigint not null auto_increment,
		nome varchar(80) not null,
		email varchar(255) not null,
		senha varchar(255) not null,
		data_cadastro datetime not null,
		
		primary key (id)
	) engine = InnoDB default charset = utf8;
	
	create table restaurante(
	id bigint not null auto_increment,
	nome varchar(80) not null, 
	taxa_frete decimal(10,2) not null, 
	data_atualizacao datetime not null,
	data_cadastro datetime not null,
	
	endereco_cidade_id bigint,
	endereco_cep varchar (9),
	endereco_logradouro varchar(100),
	endereco_numero varchar(20),
	endereco_complemento varchar(60),
	endereco_bairro varchar(60),
	
	cozinha_id bigint not null,
	
	constraint fk_restaurante_cozinha foreign key (cozinha_id) references cozinha(id),
	
	primary key(id)
	
	)engine = InnoDB default charset = utf8;
	
	create table produto (
	id bigint not null auto_increment, 
	nome varchar(80) not null,
	descricao text not null, 
	preco decimal(10, 2) not null, 
	ativo tinyint(1) not null, 
	
	restaurante_id bigint not null,
	
	constraint fk_produto_restaurante foreign key (restaurante_id) references restaurante(id),
	
	primary key(id)
	)engine = InnoDB default charset = utf8;
	
	create table grupo_permissao(
	grupo_id bigint not null,
	permissao_id bigint not NULL, 
	
	CONSTRAINT fk_grupo_permissao_permissao foreign key (permissao_id) references permissao(id),
	constraint fk_grupo_permissao_grupo foreign key(grupo_id) references grupo(id),
	
	primary key(grupo_id, permissao_id)
	)engine = InnoDB default charset = utf8;
	
	create table restaurante_forma_pagamento (
		restaurante_id bigint not null,
		forma_pagamento_id bigint not null,
		
		constraint fk_restaurante_forma_pagamento foreign key (restaurante_id) references restaurante(id),
		constraint fk_forma_pagamento_resrautante foreign key (forma_pagamento_id) references forma_pagamento (id),
		
		primary key (restaurante_id, forma_pagamento_id)
	) engine=InnoDB default charset=utf8;
	
	create table usuario_grupo (
		usuario_id bigint not null,
		grupo_id bigint not null,
		
		constraint fk_usurario_grupo_usuario foreign key (usuario_id) references usuario(id),
		constraint fk_usuario_grupo_grupo foreign key(grupo_id) references grupo(id),
		
		primary key (usuario_id, grupo_id)
	) engine=InnoDB default charset=utf8;
	
	
	
	
