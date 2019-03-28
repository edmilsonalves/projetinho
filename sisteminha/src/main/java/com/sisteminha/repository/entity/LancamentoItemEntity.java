package com.sisteminha.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sisteminha.model.LancamentoItemModel;

@Table(name = "lancamentoItem")
@Entity
@NamedQueries({
		@NamedQuery(name = "LancamentoItemEntity.findByLanc", query = "SELECT l FROM LancamentoItemEntity l WHERE l.lancamento = :lancamento") })
public class LancamentoItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_lancamento")
	private LancamentoEntity lancamento;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_item")
	private ItemEntity item;

	// Construtor padrão
	public LancamentoItemEntity() {
	}

	// Construtor personalizado
	public LancamentoItemEntity(LancamentoItemModel lm) {
		this.id = lm.getCodigo();
		this.item = new ItemEntity(lm.getItem());
		this.lancamento = new LancamentoEntity(lm.getLancamento());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LancamentoEntity getLancamento() {
		return lancamento;
	}

	public void setLancamento(LancamentoEntity lancamento) {
		this.lancamento = lancamento;
	}

	public ItemEntity getItem() {
		return item;
	}

	public void setItem(ItemEntity item) {
		this.item = item;
	}

}