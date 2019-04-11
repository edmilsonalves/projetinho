package br.com.sisteminha.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sisteminha.entity.Categoria;
import br.com.sisteminha.service.CategoriaService;
import br.com.sisteminha.util.cdi.CDIServiceLocator;

/**
 *
 * @author Edmilson Reis
 */
@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

    //@Inject   Usar EJB é muito melhor
    private CategoriaService categoriaService;

    public CategoriaConverter() {
        this.categoriaService = CDIServiceLocator.getBean(CategoriaService.class);
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Categoria retorno = null;
        if (string != null) {
            Long id = new Long(string);
            retorno = this.categoriaService.findById(id);
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
        	Categoria categoria = (Categoria) o;
        	
            return categoria != null && categoria.getId() != null ? categoria.getId().toString() : null;
        }
        return "";
    }

}
