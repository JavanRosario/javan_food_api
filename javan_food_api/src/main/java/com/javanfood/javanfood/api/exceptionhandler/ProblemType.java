package com.javanfood.javanfood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    RECURSO_NAO_ENCONTRADO(
            "Recurso não encontrado",
            "/errors/resource-not-found"
    ),
    ENTIDADE_EM_USO(
            "Entidade em uso",
            "/errors/entity-in-use"
    ),
    ERRO_NEGOCIO(
            "Violação de regra de negócio",
            "/errors/business-error"
    ),
    CORPO_INCOMPREENSIVEL(
            "Corpo da requisição incompreensível",
            "/errors/unreadable-message"
    ),
    PARAMETRO_INVALIDO(
            "Parâmetro inválido",
            "/errors/invalid-parameter"
    ),
    ERRO_NO_SISTEMA(
            "Erro interno no sistema",
            "/errors/internal-server-error"
    ),
    DADOS_INVALIDOS(
            "Dados inválidos",
            "/errors/invalid-data"
    );

    private final String title;
    private final String uri;

    ProblemType(String title, String path) {
        this.uri = "https://javanfood.com.br" + path;
        this.title = title;
    }

    public String getCode() {
        return "ERR_" + this.name();
    }
}