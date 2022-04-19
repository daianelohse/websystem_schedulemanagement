package springclasses;

import controller.dao.LoginDAO;
import model.entidade.Login;
import org.springframework.security.access.hierarchicalroles.UserDetailsWrapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component(value = "userDetailImpl")
public class UserDetailImpl implements UserDetailsService{
    private LoginDAO loginDAO;
    
    public UserDetailImpl() {
        loginDAO = new LoginDAO();
    }
    
    
    @Override
    public UserDetails loadUserByUsername(String string)  {
        Login login = loginDAO.findLoginPorUserName(string);
        if(login != null){
            return new UserDetailsPojo(login);
        }
        else{
            throw new UsernameNotFoundException("O usuário " + string + " não foi encontrado");
        }
    }

   
    
}
