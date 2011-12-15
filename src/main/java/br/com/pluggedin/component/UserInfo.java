package br.com.pluggedin.component;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.pluggedin.model.User;

/**
 * Represents the user logged in the system.
 * @author Raphael Lacerda
 */
@Component
@SessionScoped
public class UserInfo {

    private User user;

    public User getUser() {
        return user;
    }

    public void login(User user) {
        this.user = user;
    }

    public void logout() {
        this.user = null;
    }

}
