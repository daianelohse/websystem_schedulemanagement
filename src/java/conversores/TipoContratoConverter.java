/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.entidade.TipoContrato;

/**
 *
 * @author Desenvolvedor
 */
@FacesConverter(value ="TipoContratoConverter", forClass = TipoContrato.class)
public class TipoContratoConverter implements Converter {
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            return (TipoContrato) component.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value instanceof TipoContrato){
            TipoContrato tipoContrato = (TipoContrato)value;
            if(tipoContrato != null){
                component.getAttributes().put(tipoContrato.toString(), value);
                return tipoContrato.toString();
            }
        }
        return "";
    }


   
}
