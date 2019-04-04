package br.com.sisteminha.service;

import br.com.sisteminha.entity.Produto;
import br.com.sisteminha.repository.ProdutoRepository;
import br.com.sisteminha.repository.filter.ProdutoFilter;
import br.com.sisteminha.util.jpa.Transactional;

import java.io.Serializable;
import java.util.List;

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
    private ProdutoRepository produtoRepository;

    @Transactional
    public Produto salvar(Produto produto) {
        Produto produtoExistente = this.produtoRepository.findBySku(produto.getSku());

        if (produtoExistente != null && !produtoExistente.equals(produto)) { //E se não for o mesmo produto
            throw new NegocioException("Já existe um produto com o SKU informado.");
        }
        return this.produtoRepository.salvar(produto);
    }
    
    public List<Produto> findByFilter(ProdutoFilter filtro) {
    	return this.produtoRepository.findByFilter(filtro);
    }
    
    public void remove(Produto produto) {
    	this.produtoRepository.remove(produto);
    }

}
