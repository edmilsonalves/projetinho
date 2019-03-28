package com.sisteminha.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.sisteminha.model.ItemModel;
import com.sisteminha.repository.ItemRepository;
import com.sisteminha.repository.entity.ItemEntity;

public class ItemService implements Serializable{

	@Inject
	private ItemRepository itemRepository;
	
	/**
	 * Salvar um item no BD
	 * @param itemModel
	 */
	public boolean save(ItemModel item) {		
		return this.itemRepository.save(item);
	}
	
	/**
	 * Consultar um item pelo código
	 * @param codigo
	 * @return
	 */
	public ItemEntity getItem(Long codigo) {
		return this.itemRepository.getItem(codigo);	
	}
	
	/**
	 * Alterar o registro
	 */
	public boolean update(ItemModel item) {
		return this.itemRepository.update(item);	
	}
	
	/**
	 * Excluir o registro
	 * @param item
	 */
	public boolean delete(int codigo) {
		return this.itemRepository.delete(codigo);	
	}
	
	/**
	 * Mostrar todos
	 * @return
	 */
	public List<ItemModel> findAll(){
		return this.itemRepository.findAll();		
	}
}
