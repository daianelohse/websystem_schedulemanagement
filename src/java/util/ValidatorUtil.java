
package util;

import java.util.List;

public class ValidatorUtil {

     private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"; 
     
    public boolean validarList(List lista){
        return lista != null &&
               lista.size() > 0;
    }
    
    public static boolean validarEmail(String email){
        return email.matches(EMAIL_PATTERN);
    }
    
}
