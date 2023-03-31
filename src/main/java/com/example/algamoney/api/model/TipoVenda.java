package com.example.algamoney.api.model;

public enum TipoVenda {
	PRAZO("prazo"), AVISTA("avista"), CARTAOD("cartaod"), CARTAOC("cartaoc");

	private final String descricao;

	TipoVenda(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
