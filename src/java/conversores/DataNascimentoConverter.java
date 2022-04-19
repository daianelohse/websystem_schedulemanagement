/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Desenvolvedor
 */
@FacesConverter(forClass = LocalDate.class, value = "dataConverter")
public class DataNascimentoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value != null && !value.isEmpty() && !value.equals("")) {

            String[] variaveis = value.split("/");
            LocalDate data = LocalDate.of(Integer.parseInt(variaveis[2]), Integer.parseInt(variaveis[1]), Integer.parseInt(variaveis[0]));
            return data;
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
         if (value != null) {
            return ((LocalDate) value).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            return null;
        }
    }

}