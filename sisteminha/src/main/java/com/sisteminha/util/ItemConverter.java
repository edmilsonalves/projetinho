package com.sisteminha.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.sisteminha.model.ItemModel;
import com.sisteminha.repository.ItemRepository;
import com.sisteminha.repository.entity.ItemEntity;

@FacesConverter(value = "itemConverter")
public class ItemConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent uiComponent, String value) {
        if(value != null || !value.isEmpty()){ 
        	return (ItemModel) uiComponent.getAttributes().get(value);
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent uiComponent, Object value) { 
        if (value instanceof ItemModel) {
        	ItemModel entity= (ItemModel) value;
        	
            if (entity != null && entity instanceof ItemModel && entity.getCodigo() != null) {
                uiComponent.getAttributes().put( entity.getCodigo().toString(), entity);
                return entity.getCodigo().toString();
            }
            
        }
        return "";
    }

}
