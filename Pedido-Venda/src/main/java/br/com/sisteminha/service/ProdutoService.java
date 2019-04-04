package br.com.sisteminha.service;

import br.com.sisteminha.entity.Produto;
import br.com.sisteminha.repository.ProdutoRepository;
import br.com.sisteminha.util.jpa.Transactional;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Regras de Negócio Cadastro de Produtos
 *
 * @author Edmilson Reis
 */
@Named
@RequestScoped
public class ProdutoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ProdutoRepository produtos;

    @Transactional
    public Produto salvar(Produto produto) {
        Produto produtoExistente = produtos.porSku(produto.getSku());

        if (produtoExistente != null && !produtoExistente.equals(produto)) { //E se não for o mesmo produto
            throw new NegocioException("Já existe um produto com o SKU informado.");
        }
        return produtos.guardar(produto);
    }

}
