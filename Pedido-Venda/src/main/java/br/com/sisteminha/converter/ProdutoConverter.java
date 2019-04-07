package br.com.sisteminha.converter;

import br.com.sisteminha.entity.Produto;
import br.com.sisteminha.repository.ProdutoRepository;
import br.com.sisteminha.util.cdi.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Edmilson reis
 */
@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

    //@Inject   Usar EJB é muito melhor
    private ProdutoRepository produtos;

    public ProdutoConverter() {
        produtos = CDIServiceLocator.getBean(ProdutoRepository.class);
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Produto retorno = null;
        if (string != null) {
            Long id = new Long(string);
            retorno = produtos.findById(id);
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            Produto produto = (Produto) o;
            return produto.getId() == null ? null : produto.getId().toString();
        }
        return "";
    }

}
