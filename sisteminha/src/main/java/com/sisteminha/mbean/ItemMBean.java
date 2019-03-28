package com.sisteminha.mbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sisteminha.dto.ItemDTO;
import com.sisteminha.repository.entity.ItemEntity;
import com.sisteminha.service.ItemService;
import com.sisteminha.util.Mensagem;

@Named
@RequestScoped
public class ItemMBean {

	@Inject
	private ItemService itemService;

	@Inject
	private ItemDTO item;

	// Listar
	private List<ItemDTO> lista = null;

	public List<ItemDTO> getLista() {
		return lista;
	}

	public ItemDTO getItem() {
		return item;
	}

	public void setItem(ItemDTO item) {
		this.item = item;
	}

	// Salvar
	public void save() {
		boolean salvou = itemService.save(this.item);

		if (salvou) {
			this.item = null;
			Mensagem.mostrarMensagem("Registro salvo com sucesso!");
		} else {
			Mensagem.mostrarMensagemErro("Ocorreu um erro ao salvar o item!");
		}
	}

	// Carrega info de um item
	public void carregarItem(ItemDTO item) {
		this.item = item;
	}

	// Editar
	public void update() {
		boolean atualizou = itemService.update(this.item);

		// Atualizar lista
		if (atualizou) {
			this.init();
		} else {
			Mensagem.mostrarMensagemErro("Ocorreu um erro ao atualizar o item!");
		}
	}

	// Deletar
	public void delete(ItemDTO item) {
		// Buscar o item
		ItemEntity i = itemService.getItem(new Long(item.getCodigo()));

		boolean removeu = itemService.delete(item.getCodigo().intValue());

		if (!removeu) {
			Mensagem.exibirDialogErro("Ocorreu um erro ao remover o item!");
			return;
		}

		// Datatable é atualizado sozinho
		this.lista.remove(item);

		Mensagem.exibirDialogInfo("O item foi removido!");
	}

	// Carrega a lista de itens
	@PostConstruct
	public void init() {
		this.lista = itemService.findAll();
	}
}
