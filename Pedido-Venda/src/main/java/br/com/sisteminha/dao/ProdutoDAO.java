package br.com.sisteminha.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.sisteminha.dto.ProdutoFilter;
import br.com.sisteminha.entity.Produto;
import br.com.sisteminha.service.BusinessException;
import br.com.sisteminha.util.jpa.Transactional;

/**
 *
 * @author Edmilson Reis
 */
@Dependent
public class ProdutoDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager entityManager;

    public Produto salvar(Produto produto) {
        return entityManager.merge(produto);
    }

    @Transactional
    public void remove(Produto produto) {
        try {
            produto = findById(produto.getId()); //Busca produto
            entityManager.remove(produto);
            entityManager.flush(); //Faz todas as transações
        } catch (PersistenceException ex) {
            throw new BusinessException("Produto não pode ser excluído.");
        }

    }

    public Produto findBySku(String sku) {
        try {
            return entityManager.createQuery("from Produto where upper(sku) = :sku", Produto.class)
                    .setParameter("sku", sku.toUpperCase())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
	public List<Produto> findByFilter(ProdutoFilter filtro) {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("SELECT produto FROM Produto produto ");
    	sb.append("WHERE 1=1 ");

        if ( !"".equals(filtro.getSku()) && filtro.getSku() != null) {
            sb.append("AND produto.sku like '%" + filtro.getSku()+"%' ");
        }

        if (!"".equals(filtro.getNome()) && filtro.getNome() != null) {
        	 sb.append("AND produto.nome like '%"+filtro.getNome().toUpperCase()+"%' ");
        }
        
        sb.append("ORDER BY produto.nome");

        return this.entityManager.createQuery(sb.toString()).getResultList();
    }

    public Produto findById(Long id) {
        return entityManager.find(Produto.class, id);
    }

    public List<Produto> findByNome(String nome) {
        return this.entityManager.createQuery("from Produto where upper(nome) like :nome", Produto.class)
                .setParameter("nome", nome.toUpperCase() + "%").getResultList();
    }

}
