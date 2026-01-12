package com.javanfood.javanfood.api.exeptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	RECURSO_NAO_ENCONTRADO("Recurso não encontrado", "/Recurso-nao-encontrado"),
	ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso"),
	ERRO_NEGOCIO("Violação de regra de negócio", "/erro-negocio"),
	CORPO_INCOMPREENSIVEL("Corpo da menssagem incompreensivel", "/corpo-incompreensivel"),
	PARAMETRO_INVALIDO("Parâmetro inválido", "/parametro-invalido"),
	ERRO_NO_SISTEMA("Erro no sistema", "/erro-no-sistema");

	private String title;
	private String uri;


	ProblemType(String title, String path) {
		this.uri = "https://javanfood.com.br" + path;
		this.title = title;
	}
}
