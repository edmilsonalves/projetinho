package com.sisteminha.repository.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sisteminha.dto.ItemDTO;

@Entity
@Table(name = "item")
public class ItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "descricao", length = 255)
	private String descricao;

	@Column(name = "valor", precision = 8, scale = 2)
	private BigDecimal valor;

	// Construtor default
	public ItemEntity() {
	}

	// Construtor personalizado
	public ItemEntity(ItemDTO im) {
		if (im.getCodigo() != null)
			this.id = new Long(im.getCodigo());
		this.descricao = im.getDescricao();
		this.valor = im.getValor();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}