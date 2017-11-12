package eshop.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import eshop.entity.Master;
import eshop.entity.MasterRole;
import eshop.entity.Role;
import eshop.service.MasterRoleService;
import eshop.service.MasterService;
import eshop.service.RoleService;

@Controller
@RequestMapping("admin/masterrole")
public class AdminMasterRoleController {
	@Autowired
	MasterService masterService;
	@Autowired
	RoleService roleService;
	@Autowired
	MasterRoleService masterRoleService;

	@RequestMapping("index")
	public String index() {
		return "admin/masterrole/index";
	}
	//đưa ra danh sách các master
	@ModelAttribute("masters")
	public List<Master> getMasters() {
		return masterService.list();  
	}
	//đưa ra danh sách các vai trò (role)
	@ModelAttribute("roles")
	public List<Role> getRoles() {
		return roleService.list();
	}
	//trả về chuỗi ajax các role của manager đang hiển thị
	// admin/masterrole/index.jsp 
	@ResponseBody
	@RequestMapping("get-role-ids")
	public String getMasterRoles(@RequestParam("masterId") String masterId) {
		try {
			List<String> IdList = masterRoleService.getRoleIds(masterId);
			//trả về role của master có masterId
			ObjectMapper mapper = new ObjectMapper();
			String response = mapper.writeValueAsString(IdList);
			return response;
		} catch (Exception e) {
			return "[]";
		}
	}
	@ResponseBody
	@RequestMapping("update")
	public void update(MasterRole masRole) {
			masterRoleService.insertOrDelete(masRole);
	}

}
