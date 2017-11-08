package eshop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="MasterRoles")
public class MasterRole {
	@Id
	@GeneratedValue
	Integer id;
	//MasterId	nvarchar(50)
	//RoleId	nvarchar(50)
		
	@ManyToOne
	@JoinColumn(name="MasterId")
	Master master;
	
	@ManyToOne
	@JoinColumn(name="RoleId")
	Role role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Master getMaster() {
		return master;
	}

	public void setMaster(Master master) {
		this.master = master;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
