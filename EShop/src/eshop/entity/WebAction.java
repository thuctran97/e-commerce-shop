package eshop.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="WebActions")
public class WebAction {
	@Id
	@GeneratedValue
	Integer id;
	String name;
	String description	;
	
	@OneToMany(mappedBy="webAction")
	Collection<ActionRole> actionRoles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<ActionRole> getActionRoles() {
		return actionRoles;
	}

	public void setActionRoles(Collection<ActionRole> actionRoles) {
		this.actionRoles = actionRoles;
	}
	
}
