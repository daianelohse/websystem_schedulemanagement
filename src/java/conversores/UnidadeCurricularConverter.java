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
import model.entidade.UnidadeCurricular;

/**
 *
 * @author Nicolas
 */
@FacesConverter(value ="unidadeCurricularConverter", forClass = UnidadeCurricular.class)
public class UnidadeCurricularConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            return (UnidadeCurricular) component.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof UnidadeCurricular) {
            UnidadeCurricular unidadeCurricular = (UnidadeCurricular) value;
            if (unidadeCurricular != null && unidadeCurricular.getId() != null) {
                component.getAttributes().put(unidadeCurricular.getId().toString(), value);
                return unidadeCurricular.getId().toString();
            }
        }
        return "";
    }

}