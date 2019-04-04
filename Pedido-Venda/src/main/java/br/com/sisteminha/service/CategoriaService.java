package br.com.sisteminha.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sisteminha.entity.Categoria;
import br.com.sisteminha.repository.CategoriaRepository;

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
	private CategoriaRepository categoriaRepository;

	public Categoria salvar(Categoria categoria) {
		return this.categoriaRepository.salvar(categoria);
	}

	public void remove(Categoria categoria) {
		this.categoriaRepository.remove(categoria);
	}

	public List<Categoria> findByFilter(String descricao) {
		return this.categoriaRepository.findByFilter(descricao);
	}

	public Categoria findById(Long id) {
		return this.categoriaRepository.findById(id);
	}

	public List<Categoria> findByNome(String descricao) {
		return this.categoriaRepository.findByNome(descricao);
	}

}
