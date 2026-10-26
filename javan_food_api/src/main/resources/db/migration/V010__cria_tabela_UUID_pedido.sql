alter table pedido add column codigo varchar(36);

update pedido set codigo = gen_random_uuid()::text;

alter table pedido alter column codigo set not null;

alter table pedido add constraint uk_pedido_codigo unique (codigo);
