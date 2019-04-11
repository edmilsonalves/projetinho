package br.com.sisteminha.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sisteminha.dao.CategoriaDAO;
import br.com.sisteminha.entity.Categoria;

/**
 * Regras de Negócio Cadastro de Produtos
 *
 * @author Edmilson Reis
 */
@Named
@RequestScoped
public class CategoriaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaDAO categoriaDao;

	public Categoria salvar(Categoria categoria) {
		return this.categoriaDao.salvar(categoria);
	}

	public void remove(Categoria categoria) {
		this.categoriaDao.remove(categoria);
	}

	public List<Categoria> findByFilter(String descricao) {
		return this.categoriaDao.findByFilter(descricao);
	}

	public Categoria findById(Long id) {
		return this.categoriaDao.findById(id);
	}

	public List<Categoria> findByNome(String descricao) {
		return this.categoriaDao.findByNome(descricao);
	}

	public List<Categoria> findAll() {
		return this.categoriaDao.findAll();
	}

}
