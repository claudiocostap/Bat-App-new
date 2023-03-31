package com.example.algamoney.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bateria")
public class Bateria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	private String nome;

	private int quantidade;

	private double peso;

	private String descricao;

	private double v_avista;

	private double v_apraso;

	private double v_ctroca;

	private double v_total;

	private boolean usadinha;

	private boolean sucata;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bateria other = (Bateria) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getV_avista() {
		return v_avista;
	}

	public void setV_avista(double v_avista) {
		this.v_avista = v_avista;
	}

	public double getV_apraso() {
		return v_apraso;
	}

	public void setV_apraso(double v_apraso) {
		this.v_apraso = v_apraso;
	}

	public double getV_ctroca() {
		return v_ctroca;
	}

	public void setV_ctroca(double v_ctroca) {
		this.v_ctroca = v_ctroca;
	}

	public double getV_total() {
		return v_total;
	}

	public void setV_total(double v_total) {
		this.v_total = v_total;
	}

	public boolean isUsadinha() {
		return usadinha;
	}

	public void setUsadinha(boolean usadinha) {
		this.usadinha = usadinha;
	}

	public boolean isSucata() {
		return sucata;
	}

	public void setSucata(boolean sucata) {
		this.sucata = sucata;
	}

}
