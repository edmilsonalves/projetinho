package br.com.sisteminha.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sisteminha.entity.Categoria;
import br.com.sisteminha.service.CategoriaService;
import br.com.sisteminha.util.jsf.FacesUtil;

/**
 *
 * @author Edmilson Reis
 */
@Named(value = "categoriaMBean")
@ViewScoped
public class CategoriaMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaService categoriaService;

	private List<Categoria> categoriasFiltrados;

	private Categoria categoriaSelecionado;

	private Categoria categoria;

	private String descricao;

	@PostConstruct
	public void init() {
		limpar();
		this.categoriasFiltrados = this.categoriaService.findByFilter(this.descricao);
	}

	public void salvar() {
		this.categoria = this.categoriaService.salvar(this.categoria);
		limpar();
		FacesUtil.addInfoMessage("Categoria salvo com sucesso!");
	}

	public void pesquisar() {
		this.categoriasFiltrados = this.categoriaService.findByFilter(this.descricao);
	}

	public void excluir() {
		this.categoriaService.remove(this.categoriaSelecionado);
		this.categoriasFiltrados.remove(this.categoriaSelecionado); // Exclui apenas o categoria da lista
		FacesUtil.addInfoMessage("Categoria excluído com sucesso.");
	}

	private void limpar() {
		categoria = new Categoria();
		categoriaSelecionado = new Categoria();
	}

	public boolean isEditando() {
		return this.categoria.getId() != null;
	}

	public List<Categoria> getCategoriasFiltrados() {
		return categoriasFiltrados;
	}

	public Categoria getCategoriaSelecionado() {
		return categoriaSelecionado;
	}

	public void setCategoriaSelecionado(Categoria categoriaSelecionado) {
		this.categoriaSelecionado = categoriaSelecionado;
	}

	public CategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setCategoriasFiltrados(List<Categoria> categoriasFiltrados) {
		this.categoriasFiltrados = categoriasFiltrados;
	}

}
