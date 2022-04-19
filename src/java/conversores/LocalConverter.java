package conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.entidade.Localizacao;

@FacesConverter(value = "localConverter", forClass = Localizacao.class)
public class LocalConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            return (Localizacao) component.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value instanceof Localizacao){
            Localizacao localizacao = (Localizacao)value;
            if(localizacao != null && localizacao.getId() != null){
                component.getAttributes().put(localizacao.getId().toString(), value);
                return localizacao.getId().toString();
            }
        }
        return "";
    }

}
