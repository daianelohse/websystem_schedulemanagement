/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package emailservice;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author Desenvolvedor
 */
public class TesteEmail {
    public static void main(String[] args) {
        
        try {
            EmailUtils.enviarHtmlEmail(new Mensagem("nirgeonic@gmail.com", "Recovery de senha", "Acesse o seguinte link para recovery: www.google.com"));
        } catch (EmailException | MalformedURLException ex) {
            Logger.getLogger(TesteEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
