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
import model.entidade.DiaSemana;
import model.entidade.DisponibilidadeDocente;

/**
 *
 * @author Desenvolvedor
 */
@FacesConverter(forClass = DisponibilidadeDocente.class, value = "disponibilidadeConverter")
public class DisponibilidadeDocenteConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        DisponibilidadeDocente retorno = DisponibilidadeDocente.valueOf(value);
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        for (DisponibilidadeDocente object : DisponibilidadeDocente.values()) {
            if(object.equals(value))
                return value.toString();
        }
        return "";
    }
    
}
