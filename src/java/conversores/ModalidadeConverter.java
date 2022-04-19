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
import model.entidade.Modalidade;
import model.entidade.TipoAmbiente;

/**
 *
 * @author Nicolas
 */
@FacesConverter(value ="modalidadeConverter", forClass = Modalidade.class)
public class ModalidadeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            return (Modalidade) component.getAttributes().get(value);
        }
        return null;
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Modalidade) {
            Modalidade modalidade = (Modalidade) value;
            if (modalidade != null && modalidade.getId() != null) {
                component.getAttributes().put(modalidade.getId().toString(), value);
                return modalidade.getId().toString();
            }
        }
        return "";
    }
}