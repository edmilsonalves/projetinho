package br.com.sisteminha.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Diego Arantes
 */
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;

    @Column(columnDefinition = "text")  //Criar um tipo texto no banco ao invés de varchar
    private String observacao;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "data_entrega", nullable = false)
    private Date dataEntrega;

    @NotNull
    @Column(nullable = false, name = "valor_frete", precision = 10, scale = 2)
    private BigDecimal valorFrete = BigDecimal.ZERO;

    @NotNull
    @Column(nullable = false, name = "valor_desconto", precision = 10, scale = 2)
    private BigDecimal valorDesconto = BigDecimal.ZERO;

    @NotNull
    @Column(nullable = false, name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusPedido status = StatusPedido.ORCAMENTO;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", nullable = false, length = 20)
    private FormaPagamento formaPagamento;

    @NotNull
    @ManyToOne(optional = false)
    private Usuario vendedor;

    @NotNull
    @ManyToOne(optional = false)
    private Cliente cliente;

    @Embedded //Embutir classe endereço entrega na mesma tabela
    private EnderecoEntrega enderecoEntrega;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true) //Excluir itens órfãos
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EnderecoEntrega getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Transient
    public boolean isNovo() {
        return getId() == null;
    }

    @Transient
    public boolean isExistente() {
        return !isNovo();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Transient
    public BigDecimal getValorSubtotal() {
        return this.getValorTotal().subtract(this.getValorFrete()).add(this.getValorDesconto());
    }

    public void recalcularValorTotal() {
        BigDecimal total = BigDecimal.ZERO;

        //Adiciona o Frete e tira o Desconto
        total = total.add(getValorFrete()).subtract(getValorDesconto());

        //Calcula os Valores dos Itens
        for (ItemPedido item : this.getItens()) {
            if (item.getProduto() != null && item.getProduto().getId() != null) {
                total = total.add(item.getValorTotal());
            }
        }

        setValorTotal(total);
    }

    public void adicionarItemVazio() {
        if (this.isOrcamento()) {

            Produto produto = new Produto();

            ItemPedido item = new ItemPedido();

            item.setProduto(produto);

            item.setPedido(this);

            this.getItens().add(0, item);
        }
    }

    @Transient
    private boolean isOrcamento() {
        return StatusPedido.ORCAMENTO.equals(this.getStatus());
    }

    public void removerItemVazio() {
        ItemPedido primeiroItem = this.getItens().get(0);

        if (primeiroItem != null && primeiroItem.getProduto().getId() == null) { //Verifica se é um item vazio
            this.getItens().remove(0);
        }
    }

    @Transient
    public boolean isValorTotalNegativo() {
        return this.getValorTotal().compareTo(BigDecimal.ZERO) < 0; //Verifica se o valor total é menor que 0
    }

    @Transient
    boolean isEmitido() {
       return StatusPedido.EMITIDO.equals(this.getStatus());
    }

}
