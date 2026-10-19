create table estado
(
    id   bigint generated always as identity primary key,
    nome varchar(80) not null
);

insert into estado (nome)
select distinct nome_estado
from cidade;

alter table cidade
    add column estado_id bigint;

update cidade c
set estado_id = e.id from estado e
where e.nome = c.nome_estado;

alter table cidade
    alter column estado_id set not null;

alter table cidade
    add constraint fk_cidade_estado
        foreign key (estado_id) references estado (id);

alter table cidade
drop
column nome_estado;

alter table cidade
    rename column nome_cidade to nome;

alter table cidade
alter
column nome type varchar(80);

alter table cidade
    alter column nome set not null;