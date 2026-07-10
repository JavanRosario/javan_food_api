alter table pedido
    add codigo varchar(36) not NULL after id;
update pedido p
set codigo = UUID();
alter table pedido
    add constraint uk_pedido_codigo unique (codigo);