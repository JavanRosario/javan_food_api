package com.javanfood.javanfood.api.exeptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada","/entidade-nao-encontrada");


	private String title;
	private String uri;


	ProblemType(String title, String path) {
		this.uri = "https://javanfood.com.br" + path;
		this.title = title;
	}
}
