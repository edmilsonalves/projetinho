package com.sisteminha.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.sisteminha.repository.entity.ItemEntity;
import com.sisteminha.repository.entity.LancamentoItemEntity;

public class ItemModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codigo;
	private String descricao;
	private BigDecimal valor;
	private List<LancamentoItemEntity> itensLcto;
	
	//Construtor personalizado
	public ItemModel(ItemEntity i) {
		if (i.getId() != null)
			this.codigo = i.getId().intValue();
		this.descricao = i.getDescricao();
		this.valor = i.getValor();
		this.itensLcto = i.getItensLcto();
	}
	
	//Construtor default
	public ItemModel() {}
	
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
	
	public List<LancamentoItemEntity> getItensLcto() {
		return itensLcto;
	}

	public void setItensLcto(List<LancamentoItemEntity> itensLcto) {
		this.itensLcto = itensLcto;
	}

	@Override
	public boolean equals(Object other){ 
		return other instanceof ItemModel && (codigo != null) ? codigo.equals(((ItemModel) other).codigo) : (other == this);
	}
	@Override
	public int hashCode(){
		return codigo != null ? this.getClass().hashCode() + codigo.hashCode() : super.hashCode();
	}
	@Override
	public String toString(){
		return this.descricao;
	}
	
}