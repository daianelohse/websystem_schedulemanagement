
package util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncriptadorUtil {
    
    public static String encriptarSenha(String senha){
        String pw = BCrypt.hashpw(senha, BCrypt.gensalt());
        return pw;
    }
    
    public static boolean validarSenha(String senhaDigitada, String senhaEncriptada){
        return BCrypt.checkpw(senhaDigitada, senhaEncriptada);
    }
    
//    public static void main(String[] args) {
//        String senha = "#Wt5y3w88";
//        String senhaEnc = encriptarSenha(senha);
//        System.out.println("Senha encriptada: " + senhaEnc);
//        System.out.println(validarSenha(senha + "", senhaEnc));
//    }
    
}
