package br.com.sisteminha.converter;

import br.com.sisteminha.entity.Categoria;
import br.com.sisteminha.repository.CategoriaRepository;
import br.com.sisteminha.util.cdi.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Diego Arantes
 */
@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

    //@Inject   Usar EJB é muito melhor
    private CategoriaRepository categorias;

    public CategoriaConverter() {
        categorias = CDIServiceLocator.getBean(CategoriaRepository.class);
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Categoria retorno = null;
        if (string != null) {
            Long id = new Long(string);
            retorno = categorias.porId(id);
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return ((Categoria) o).getId().toString();
        }
        return "";
    }

}
