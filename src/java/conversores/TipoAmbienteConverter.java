package conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.entidade.TipoAmbiente;

@FacesConverter(value = "tipoAmbiente", forClass = TipoAmbiente.class)
public class TipoAmbienteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            return (TipoAmbiente) component.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof TipoAmbiente) {
            TipoAmbiente tipoAmbiente = (TipoAmbiente) value;
            if (tipoAmbiente != null && tipoAmbiente.getId() != null) {
                component.getAttributes().put(tipoAmbiente.getId().toString(), value);
                return tipoAmbiente.getId().toString();
            }
        }
        return "";
    }

}
