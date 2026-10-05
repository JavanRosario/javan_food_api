alter table pedido rename column status to status_pedido;
alter table pedido alter column status_pedido type varchar(20);
alter table pedido
    alter column status_pedido set not null;

alter table pedido rename column subtotal to sub_total;
alter table pedido alter column sub_total type decimal(10,2);
alter table pedido
    alter column sub_total set not null;

alter table pedido rename column usuario_id to usuario_cliente_id;
alter table pedido alter column usuario_cliente_id type bigint;
alter table pedido
    alter column usuario_cliente_id set not null;

alter table pedido
    alter column data_confirmacao drop not null;

alter table pedido
    alter column data_cancelamento drop not null;
