package br.com.sisteminha.repository.filter;

import java.io.Serializable;

import br.com.sisteminha.validation.SKU;

/**
 *
 * @author Diego Arantes
 */
public class ProdutoFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    @SKU
    private String sku;

    private String nome;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.toUpperCase(); //Verifica se é Nulo
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
