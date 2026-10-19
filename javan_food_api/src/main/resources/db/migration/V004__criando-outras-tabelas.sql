create table forma_pagamento
(
    id        bigint generated always as identity primary key,
    descricao varchar(60) not null
);

create table grupo
(
    id   bigint generated always as identity primary key,
    nome varchar(60) not null
);

create table permissao
(
    id        bigint generated always as identity primary key,
    descricao varchar(60) not null,
    nome      varchar(60) not null
);

create table usuario
(
    id            bigint generated always as identity primary key,
    nome          varchar(80)  not null,
    email         varchar(255) not null,
    senha         varchar(255) not null,
    data_cadastro timestamp    not null
);

create table restaurante
(
    id                   bigint generated always as identity primary key,
    nome                 varchar(80)    not null,
    taxa_frete           decimal(10, 2) not null,
    data_atualizacao     timestamp      not null,
    data_cadastro        timestamp      not null,
    endereco_cidade_id   bigint,
    endereco_cep         varchar(9),
    endereco_logradouro  varchar(100),
    endereco_numero      varchar(20),
    endereco_complemento varchar(60),
    endereco_bairro      varchar(60),
    cozinha_id           bigint         not null,
    constraint fk_restaurante_cozinha foreign key (cozinha_id) references cozinha (id)
);

create table produto
(
    id             bigint generated always as identity primary key,
    nome           varchar(80)    not null,
    descricao      text           not null,
    preco          decimal(10, 2) not null,
    ativo          boolean        not null,
    restaurante_id bigint         not null,
    constraint fk_produto_restaurante foreign key (restaurante_id) references restaurante (id)
);

create table grupo_permissao
(
    grupo_id     bigint not null,
    permissao_id bigint not null,
    constraint fk_grupo_permissao_permissao foreign key (permissao_id) references permissao (id),
    constraint fk_grupo_permissao_grupo foreign key (grupo_id) references grupo (id),
    primary key (grupo_id, permissao_id)
);

create table restaurante_forma_pagamento
(
    restaurante_id     bigint not null,
    forma_pagamento_id bigint not null,
    constraint fk_restaurante_forma_pagamento foreign key (restaurante_id) references restaurante (id),
    constraint fk_forma_pagamento_restaurante foreign key (forma_pagamento_id) references forma_pagamento (id),
    primary key (restaurante_id, forma_pagamento_id)
);

create table usuario_grupo
(
    usuario_id bigint not null,
    grupo_id   bigint not null,
    constraint fk_usuario_grupo_usuario foreign key (usuario_id) references usuario (id),
    constraint fk_usuario_grupo_grupo foreign key (grupo_id) references grupo (id),
    primary key (usuario_id, grupo_id)
);
