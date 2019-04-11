package br.com.sisteminha.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.sisteminha.entity.Categoria;
import br.com.sisteminha.service.BusinessException;

/**
 *
 * @author Edmilson Reis
 */
@Dependent
public class CategoriaDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager entityManager;

    public List<Categoria> findAll() {
        return entityManager.createQuery("from Categoria", Categoria.class).getResultList();
    }

    public void remove(Categoria categoria) {
        try {
            categoria = findById(categoria.getId()); //Busca categoria
            entityManager.remove(categoria);
            entityManager.flush(); //Faz todas as transações
        } catch (PersistenceException ex) {
            throw new BusinessException("Categoria não pode ser excluído pois está sendo utilizada em algum produto.");
        }

    }
    
    public Categoria salvar(Categoria categoria) {
        return entityManager.merge(categoria);
    }

    
    @SuppressWarnings("unchecked")
	public List<Categoria> findByFilter(String descricao) {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("SELECT categoria FROM Categoria categoria ");
    	sb.append("WHERE 1=1 ");

        if (!"".equals(descricao) && descricao != null) {
        	 sb.append("AND categoria.descricao like '%"+descricao.toUpperCase()+"%' ");
        }
        
        sb.append("ORDER BY categoria.descricao");

        return this.entityManager.createQuery(sb.toString()).getResultList();
    }

    public Categoria findById(Long id) {
        return entityManager.find(Categoria.class, id);
    }

    public List<Categoria> findByNome(String descricao) {
        return this.entityManager.createQuery("from Categoria where upper(descricao) like :descricao", Categoria.class)
                .setParameter("descricao", descricao.toUpperCase() + "%").getResultList();
    }


}
