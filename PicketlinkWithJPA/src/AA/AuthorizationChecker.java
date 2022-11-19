
package AA;

import org.picketlink.Identity;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.Group;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.model.basic.User;

import javax.inject.Inject;
import javax.inject.Named;

import static org.picketlink.idm.model.basic.BasicModel.*;

@Named 
public class AuthorizationChecker {
    
    @Inject
    private Identity identity;
    
    @Inject 
    private IdentityManager identityManager;

    @Inject
    private RelationshipManager relationshipManager;

    public boolean hasApplicationRole(String roleName) {
        Role role = getRole(this.identityManager, roleName);
        return BasicModel.hasRole(this.relationshipManager, this.identity.getAccount(), role);
    }

    public boolean isMember(String groupName) {
        Group group = getGroup(this.identityManager, groupName);
        return BasicModel.isMember(this.relationshipManager, this.identity.getAccount(), group);
    }

    public boolean hasGroupRole(String roleName, String groupName) {
        Group group = getGroup(this.identityManager, groupName);
        Role role = getRole(this.identityManager, roleName);
        return BasicModel.hasGroupRole(this.relationshipManager, this.identity.getAccount(), role, group);
    }
    
    public boolean canGrant(String userName)
    {
    	
    	User user = BasicModel.getUser(identityManager,userName );
    	
    	System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    	System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    	System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    	System.out.println("\n     "+"\n     "+user.getFirstName());
    	System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    	System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    	System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    	Role role = getRole(this.identityManager, "admin");
    	return BasicModel.hasRole(relationshipManager, user, role);
    }
    public boolean hasRole(String roleName,String userName) {
        Role role = getRole(this.identityManager, roleName);
        User user = BasicModel.getUser(identityManager,userName ); 	
        return BasicModel.hasRole(relationshipManager, user, role)  ;
        }
   }
    
   
