package br.com.sisteminha.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Diego Arantes
 */
@Entity
@Table(name = "item_pedido")
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 3)
    private Integer quantidade = 1;

    @Column(nullable = false, name = "valor_unitario", precision = 10, scale = 2)
    private BigDecimal valorUnitario = BigDecimal.ZERO;

    @ManyToOne(optional = false)
    private Produto produto;

    @ManyToOne(optional = false)
    private Pedido pedido;

    public ItemPedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final ItemPedido other = (ItemPedido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Transient
    public boolean isProdutoAssociado() {
        return this.getProduto() != null && this.getProduto().getId() != null;
    }

    @Transient
    public BigDecimal getValorTotal() {
        return this.getValorUnitario().multiply(new BigDecimal(this.getQuantidade()));
    }

    @Transient
    public boolean isEstoqueSulficiente() {
        return this.getPedido().isEmitido() || this.getProduto().getId() == null
                || this.getProduto().getQuantidadeEstoque() >= this.getQuantidade();
    }

    @Transient
    public boolean isEstoqueInsulficiente() {
        return !this.isEstoqueSulficiente();
    }

}
