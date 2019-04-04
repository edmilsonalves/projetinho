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
import br.com.sisteminha.repository.ProdutoRepository;
import br.com.sisteminha.repository.filter.ProdutoFilter;
import br.com.sisteminha.service.ProdutoService;
import br.com.sisteminha.util.jsf.FacesUtil;

/**
 *
 * @author Edmilson Reis
 */
@Named(value = "produtoMBean")
@ViewScoped
public class ProdutoMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaRepository categorias;

	@Inject
	private ProdutoService cadastroProdutoService;

	@Inject
	private ProdutoRepository produtos;

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
		filtro = new ProdutoFilter();
		limpar();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostBack()) { // Se não for post back não faça a consulta
			categoriaRaizes = categorias.raizes();

			if (categoriaPai != null) {
				carregarSubcategorias();
			}
		}
	}

	public void carregarSubcategorias() {
		subcategorias = categorias.subCategoriasDe(categoriaPai);
	}

	public void salvar() {
		this.produto = cadastroProdutoService.salvar(produto);
		limpar();
		FacesUtil.addInfoMessage("Produto salvo com sucesso!");
	}

	public void pesquisar() {
		produtosFiltrados = produtos.filtrados(filtro);
	}

	public void excluir() {
		produtos.remover(produtoSelecionado);
		produtosFiltrados.remove(produtoSelecionado); // Exclui apenas o produto da lista
		FacesUtil.addInfoMessage("Produto " + produtoSelecionado.getSku() + " excluído com sucesso.");
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
		System.out.println("CHAMOOOU O MÈTODOOOOOOOOOOO");
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
