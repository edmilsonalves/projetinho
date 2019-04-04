package br.com.sisteminha.repository;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.sisteminha.entity.Categoria;

/**
 *
 * @author Edmilson Reis
 */
@Dependent
public class CategoriaRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Categoria findById(Long id) {
        return manager.find(Categoria.class, id);
    }

    public List<Categoria> raizes() {
        return manager.createQuery("from Categoria where categoriaPai is null", Categoria.class).getResultList();
    }

    public List<Categoria> subCategoriasDe(Categoria categoriaPai) {
        return manager.createQuery("from Categoria where categoriaPai = :raiz",
                Categoria.class)
                .setParameter("raiz", categoriaPai)
                .getResultList();
    }

}
