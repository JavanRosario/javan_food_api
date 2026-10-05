ALTER TABLE pedido
    CHANGE COLUMN status status_pedido VARCHAR (20) NOT NULL,
    CHANGE COLUMN subtotal sub_total DECIMAL (10,2) NOT NULL,
    CHANGE COLUMN usuario_id usuario_cliente_id BIGINT NOT NULL,
    MODIFY COLUMN data_confirmacao DATETIME NULL,
    MODIFY COLUMN data_cancelamento DATETIME NULL;