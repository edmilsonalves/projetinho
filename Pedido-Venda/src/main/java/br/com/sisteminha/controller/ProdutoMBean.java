package br.com.sisteminha.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sisteminha.dto.ProdutoFilter;
import br.com.sisteminha.entity.Categoria;
import br.com.sisteminha.entity.Produto;
import br.com.sisteminha.service.CategoriaService;
import br.com.sisteminha.service.ProdutoService;

/**
 *
 * @author Edmilson Reis
 */
@Named
@ViewScoped
public class ProdutoMBean extends BasicMBean {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGE_LIST = "/produtos/list?faces-redirect=true";
	private static final String PAGE_EDIT = "/produtos/form?faces-redirect=true";
	private static final String PRODUTO = "produto";

	@Inject
	private CategoriaService categoriaService;

	@Inject
	private ProdutoService produtoService;

	private ProdutoFilter filtro;

	private List<Produto> produtosFiltrados;

	private Produto produtoSelecionado;

	private Produto produto;

	private List<Categoria> categorias;

	@PostConstruct
	public void init() {
		
		this.produto = super.getFlashAttribute(PRODUTO);
		
		if(this.produto == null) {
			this.produto = new Produto();
		}
		
		this.categorias = this.categoriaService.findAll();
		this.filtro = new ProdutoFilter();
		this.produtosFiltrados = this.produtoService.findByFilter(filtro);
	}

	
	public String edit(Produto produto) {
		super.putFlashAttribute(PRODUTO, produto);
		
		return PAGE_EDIT;
	}

	public String salvar() {
		this.produto = produtoService.salvar(produto);
		this.produto = new Produto();
		super.addInfoMessage("Produto salvo com sucesso!");
		
		return PAGE_LIST;
	}

	public void pesquisar() {
		this.produtosFiltrados = this.produtoService.findByFilter(filtro);
	}

	public void excluir() {
		this.produtoService.remove(this.produtoSelecionado);
		this.produtosFiltrados.remove(this.produtoSelecionado); // Exclui apenas o produto da lista
		super.addInfoMessage("Produto " + this.produtoSelecionado.getSku() + " excluído com sucesso.");
	}


	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public boolean isEditando() {
		return this.produto.getId() != null;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ProdutoFilter filtro) {
		this.filtro = filtro;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

}
