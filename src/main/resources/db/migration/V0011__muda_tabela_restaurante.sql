alter table usuario
alter
column data_cadastro type timestamp with time zone
    using data_cadastro at time zone 'UTC';

alter table restaurante
alter
column data_atualizacao type timestamp with time zone
    using data_atualizacao at time zone 'UTC';

alter table restaurante
alter
column data_cadastro type timestamp with time zone
    using data_cadastro at time zone 'UTC';

alter table pedido
alter
column data_criacao type timestamp with time zone
    using data_criacao at time zone 'UTC';

alter table pedido
alter
column data_confirmacao type timestamp with time zone
    using data_confirmacao at time zone 'UTC';

alter table pedido
alter
column data_cancelamento type timestamp with time zone
    using data_cancelamento at time zone 'UTC';

alter table pedido
alter
column data_entrega type timestamp with time zone
    using data_entrega at time zone 'UTC';