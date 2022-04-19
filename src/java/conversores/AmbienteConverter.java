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
import model.entidade.Ambiente;
import model.entidade.Curso;
import model.entidade.Modalidade;

/**
 *
 * @author Nicolas
 */
@FacesConverter(value ="ambienteConverter", forClass = Ambiente.class)
public class AmbienteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            return (Ambiente) component.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Ambiente) {
            Ambiente curso = (Ambiente) value;
            if (curso != null && curso.getId() != null) {
                component.getAttributes().put(curso.getId().toString(), value);
                return curso.getId().toString();
            }
        }
        return "";
    }

}