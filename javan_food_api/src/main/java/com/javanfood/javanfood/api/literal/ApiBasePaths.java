package com.javanfood.javanfood.api.literal;


public class ApiBasePaths {
    public static final String API_VERSION = "v1";
    public static final String API_BASE_PATH = "api/" + API_VERSION;


    public static final String API_PEDIDOS = API_BASE_PATH + "/pedidos/{codigoPedido}";
    public static final String PEDIDOS_CONFIRMAR_PEDIDO = "/confirmacao";
    public static final String PEDIDOS_ENTREGAR_PEDIDO = "/entregar";
    public static final String PEDIDOS_CANCELAR_PEDIDO = "/cancelar";
}
