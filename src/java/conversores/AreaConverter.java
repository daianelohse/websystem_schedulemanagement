package conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.entidade.Area;

/**
 *
 * @author Desenvolvedor
 */
@FacesConverter(value ="AreaConverter", forClass = Area.class)
public class AreaConverter implements Converter {
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            return (Area) component.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value instanceof Area){
            Area area = (Area)value;
            if(area != null){
                component.getAttributes().put(area.toString(), value);
                return area.toString();
            }
        }
        return "";
    }

   
}
