package br.com.sisteminha.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import br.com.sisteminha.entity.Categoria;
import br.com.sisteminha.entity.Produto;
import br.com.sisteminha.repository.CategoriaRepository;
import br.com.sisteminha.repository.filter.ProdutoFilter;
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
	private CategoriaRepository categorias;

	@Inject
	private ProdutoService produtoService;

	private ProdutoFilter filtro;

	private List<Produto> produtosFiltrados;

	private Produto produtoSelecionado;

	private Produto produto;

	@NotNull
	private Categoria categoriaPai;

	private List<Categoria> categoriaRaizes;
	private List<Categoria> subcategorias;

	@PostConstruct
	public void init() {
		
		this.produto = super.getFlashAttribute(PRODUTO);
		
		if(this.produto == null) {
			limpar();
		}else {
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
		}
		
		carregarCategorias();
		this.filtro = new ProdutoFilter();
		this.produtosFiltrados = produtoService.findByFilter(filtro);
	}

	public void carregarCategorias() {
		this.categoriaRaizes = categorias.raizes();

		if (this.categoriaPai != null) {
			carregarSubcategorias();
		}
	}
	
	public String edit(Produto produto) {
		super.putFlashAttribute(PRODUTO, produto);
		
		return PAGE_EDIT;
	}

	public void carregarSubcategorias() {
		subcategorias = categorias.subCategoriasDe(categoriaPai);
	}

	public String salvar() {
		this.produto = produtoService.salvar(produto);
		limpar();
		super.addInfoMessage("Produto salvo com sucesso!");
		
		return PAGE_LIST;
	}

	public void pesquisar() {
		produtosFiltrados = produtoService.findByFilter(filtro);
	}

	public void excluir() {
		produtoService.remove(produtoSelecionado);
		produtosFiltrados.remove(produtoSelecionado); // Exclui apenas o produto da lista
		super.addInfoMessage("Produto " + produtoSelecionado.getSku() + " excluído com sucesso.");
	}

	private void limpar() {
		produto = new Produto();
		categoriaPai = null;
		subcategorias = new ArrayList<>();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;

		if (this.produto != null) {
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
		}
	}

	public boolean isEditando() {
		return this.produto.getId() != null;
	}

	public List<Categoria> getCategoriaRaizes() {
		return categoriaRaizes;
	}

	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
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
