create table cidade(
    id bigint generated always as identity primary key not null ,
    nome_cidade varchar(80) not null,
    nome_estado varchar(80) not null
);