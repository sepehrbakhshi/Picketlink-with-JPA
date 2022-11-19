package AA;

import javax.ejb.Singleton;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.Identity;
import org.picketlink.authorization.annotations.Restrict;
import org.picketlink.authorization.annotations.RolesAllowed;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PermissionManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.model.basic.User;

//@Restrict("#{identity.account.partition.name != 'default'}")
@Singleton
@Named
@RequestScoped
@RolesAllowed("admin")
public class GrantUserRole {
	
	@Inject Identity identity;
	@Inject PermissionManager permissionManager;
	@Inject IdentityManager identityManager;
	@Inject RelationshipManager relationshipManager;

	@Inject FacesContext facesContext;
	
	private String loginName;
	private String roleName;
	
	
	public String grantRole()
	{
		User user=BasicModel.getUser(identityManager, this.loginName);
		Role role=BasicModel.getRole(identityManager, this.roleName);
		BasicModel.grantRole(relationshipManager, user, role);
		return "/home.xhtml";
	}
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
