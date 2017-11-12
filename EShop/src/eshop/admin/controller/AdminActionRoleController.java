package eshop.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import eshop.entity.ActionRole;
import eshop.entity.Role;
import eshop.entity.WebAction;
import eshop.service.ActionRoleService;
import eshop.service.RoleService;
import eshop.service.WebActionService;

@Controller
@RequestMapping("admin/actionrole")
public class AdminActionRoleController {
	@Autowired
	RoleService roleService;
	@Autowired
	WebActionService webActionService;
	@Autowired
	ActionRoleService actionRoleService;
	
	@RequestMapping("index")
	public String index() {
		return "admin/actionrole/index";
	}
	
	@ResponseBody
	@RequestMapping("get-web-action-ids")
	public String getWebActionIds(@RequestParam("roleId") String roleId) {
		List<Integer> idList = actionRoleService.getWebActionIds(roleId);
		try {
			ObjectMapper mapper = new ObjectMapper();
			String response = mapper.writeValueAsString(idList);
			return response;
		} 
		catch (Exception e) {
			return "[]";
		}
	}
	
	@ResponseBody
	@RequestMapping("insert-or-delete")
	public void insertOrDelete(ActionRole actionRole) {
		actionRoleService.insertOrDelete(actionRole);
	}
	
	@ModelAttribute("roles")
	public List<Role> getRoles() {
		return roleService.list();
	}
	
	@ModelAttribute("webActions")
	public List<WebAction> getWebActions() {
		return webActionService.list();
	}
}
