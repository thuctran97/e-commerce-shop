package eshop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ActionRoles")
public class ActionRole {
	@Id
	@GeneratedValue
	Integer id;
	//RoleId	nvarchar(50)
	//WebActionId	int
	
	@ManyToOne
	@JoinColumn(name="RoleId")
	Role role;
	
	@ManyToOne
	@JoinColumn(name="WebActionId")
	WebAction webAction;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public WebAction getWebAction() {
		return webAction;
	}

	public void setWebAction(WebAction webAction) {
		this.webAction = webAction;
	}
	
}
