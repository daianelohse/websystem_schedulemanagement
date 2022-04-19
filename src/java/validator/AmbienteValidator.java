package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import model.entidade.Ambiente;

@FacesValidator("ValidadorAmbiente")
public class AmbienteValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) {
        Ambiente ambiente = (Ambiente) value;
        String campos = "";
        if (ambiente.getLocalizacao() == null) {
            campos += "Local;\n";
        }

        if (ambiente.getTipoAmbiente() == null) {
            campos += "Tipo do Ambiente;\n";
        }

        if (ambiente.getBloco() == null || ambiente.getBloco().isEmpty()) {
            campos += "Bloco;\n";
        }

        if (ambiente.getNumero() == null) {
            campos += "Número;\n";
        }

        if (ambiente.getAndar() == null || ambiente.getAndar() == -1) {
            campos += "Andar;\n";
        }

        if (ambiente.getCapacidade() == null) {
            campos += "Capacidade;\n";
        }
        
        String retorno = "";
        if(!campos.isEmpty()){
             retorno = "Preencha os seguintes campos obrigatórios: \n" + campos;
             FacesMessage messagem = new FacesMessage(campos);
             messagem.setSeverity(FacesMessage.SEVERITY_INFO);
             throw new ValidatorException(messagem);
        }
    }

}
