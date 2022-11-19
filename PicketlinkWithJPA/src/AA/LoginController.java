
package AA;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.Identity;
import org.picketlink.Identity.AuthenticationResult;


@Stateless
@Named
public class LoginController {

    @Inject
    private Identity identity;

   
    public void login() {
        AuthenticationResult result = identity.login();
        
    }
}
