package springclasses;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.entidade.Login;
import model.entidade.Permissao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsPojo implements UserDetails {

    private Login login;

    public UserDetailsPojo(Login login) {
        this.login = login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> result = new ArrayList<>();
        for (Permissao regra : login.getPermissoes()) {
            result.add(new GrantedAuthorityImpl(regra.getRegra().toString()));
        }
        return result;
    }

    @Override
    public String getPassword() {
        return login.getSenha();
    }

    @Override
    public String getUsername() {
        return login.getUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Login getLogin() {
        return login;
    }

}
