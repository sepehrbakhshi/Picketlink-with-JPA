package AA;
import javax.ejb.Singleton;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Article;

import static org.picketlink.idm.model.basic.BasicModel.addToGroup;
import static org.picketlink.idm.model.basic.BasicModel.grantGroupRole;
import static org.picketlink.idm.model.basic.BasicModel.grantRole;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.PermissionManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.Group;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.model.basic.User;

@Singleton
@Named
@RequestScoped
public class UserRegistration {
 
	@Inject
    private PartitionManager partitionManager;
 

 
	  private String loginName;
	  private String firstName;
	  private String lastName;
	  private String password;
	  private String email;
 
	  public String register() {
	 IdentityManager identityManager=this.partitionManager.createIdentityManager();
    User newUser = new User(this.loginName);
 
    newUser.setFirstName(this.firstName);
    newUser.setLastName(this.lastName);
    newUser.setEmail(this.email);
    Password password = new Password(this.password);
    identityManager.add(newUser);
 

 
    identityManager.updateCredential(newUser, password);
    
  //  Create role "Admin"
 //   Role admin = new Role("admin");
  //  identityManager.add(admin);
    
    //Create group "writers"
    Group publishers = new Group("publishers");
    identityManager.add(publishers);
    
    


    RelationshipManager relationshipManager = 
    this.partitionManager.createRelationshipManager();
 // Make newUser a member of the "writers" group
    addToGroup(relationshipManager, newUser, publishers);

 // Make newUser an admin of the "writers" group
  //  grantGroupRole(relationshipManager, newUser, admin, publishers);



    PermissionManager permissionManager = partitionManager.createPermissionManager();

  //  Grant both john and mary permission to create new articles
    permissionManager.grantPermission(newUser, Article.class, "create");
  
    return "home.xhtml";
  }

		public String getLoginName() {
			return loginName;
		}
		
		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}
		
		public String getFirstName() {
			return firstName;
		}
		
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		
		public String getLastName() {
			return lastName;
		}
		
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		
		public String getPassword() {
			return password;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
		
		
		public String getEmail() {
			return email;
		}
		
		
		
		public void setEmail(String email) {
			this.email = email;
		}
		 
}