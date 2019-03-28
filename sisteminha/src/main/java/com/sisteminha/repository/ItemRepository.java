package com.sisteminha.repository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.sisteminha.dto.ItemDTO;
import com.sisteminha.repository.entity.ItemEntity;
import com.sisteminha.util.EntityManagerProd;

public class ItemRepository implements Serializable{	
	
	@Inject
	private ItemEntity itemEntity;
		
	private EntityManager entityManager;
	
	/**
	 * Salvar um item no BD
	 * @param itemModel
	 */
	public boolean save(ItemDTO item) {
		entityManager = EntityManagerProd.JpaEntityManager();
		
		itemEntity = new ItemEntity(item);
		
		try {
			entityManager.persist(itemEntity);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Consultar um item pelo código
	 * @param codigo
	 * @return
	 */
	public ItemEntity getItem(Long codigo) {
		entityManager = EntityManagerProd.JpaEntityManager();
		
		return entityManager.find(ItemEntity.class,  codigo);		
	}
	
	/**
	 * Alterar o registro
	 */
	public boolean update(ItemDTO item) {
		entityManager = EntityManagerProd.JpaEntityManager();
		
		ItemEntity itemE = this.getItem(new Long(item.getCodigo()));	
		
		itemE.setDescricao(item.getDescricao());
		itemE.setValor(item.getValor());
		try {
			entityManager.merge(itemE);		
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Excluir o registro
	 * @param item
	 */
	public boolean delete(int codigo) {
		entityManager = EntityManagerProd.JpaEntityManager();
		
		ItemEntity itemE = this.getItem(new Long(codigo));
		try {
			entityManager.remove(itemE);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Mostrar todos
	 * @return
	 */
	public List<ItemDTO> findAll(){
		List<ItemDTO> lista = new ArrayList<ItemDTO>();
		
		entityManager = EntityManagerProd.JpaEntityManager();
				
		List<ItemEntity> list = entityManager.createQuery("SELECT item FROM ItemEntity item", ItemEntity.class).getResultList();
		
		for  (ItemEntity itemE: list) {
			ItemDTO itemM = new ItemDTO(itemE);
			lista.add(itemM);
		}
		
		return lista;		
	}

}
