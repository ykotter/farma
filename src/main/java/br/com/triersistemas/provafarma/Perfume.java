package br.com.triersistemas.provafarma;

import java.util.SplittableRandom;
import java.math.BigDecimal;

public class Perfume {
	
	private Long id;
	private String nome;
	private BigDecimal valor;
	private EnumTipoPerfume tipo;
	
	public Perfume(String nome, BigDecimal valor, EnumTipoPerfume tipo) {
		this.id = new SplittableRandom().nextLong(9, 100);
		this.nome = nome;
		this.valor = valor;
		this.tipo = tipo;
	}
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public EnumTipoPerfume getTipo() {
		return tipo;
	}
}
