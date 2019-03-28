package com.sisteminha.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.sisteminha.repository.entity.ItemEntity;

public class ItemDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codigo;
	private String descricao;
	private BigDecimal valor;

	// Construtor personalizado
	public ItemDTO(ItemEntity i) {
		if (i.getId() != null)
			this.codigo = i.getId().intValue();
		this.descricao = i.getDescricao();
		this.valor = i.getValor();
	}

	// Construtor default
	public ItemDTO() {
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof ItemDTO && (codigo != null) ? codigo.equals(((ItemDTO) other).codigo)
				: (other == this);
	}

	@Override
	public int hashCode() {
		return codigo != null ? this.getClass().hashCode() + codigo.hashCode() : super.hashCode();
	}

	@Override
	public String toString() {
		return this.descricao;
	}

}