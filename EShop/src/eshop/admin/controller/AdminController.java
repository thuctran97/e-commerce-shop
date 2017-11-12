package eshop.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import eshop.entity.Master;
import eshop.service.MasterService;

@Controller
@RequestMapping("admin/home")
public class AdminController {
	@Autowired
	MasterService masterService;
	@RequestMapping("index")
	public String index() {
		return "admin/home/index";
	}
	@RequestMapping("unauthorize")
	public String unauthorize() {
		return "admin/home/unauthorize";
	}
	@RequestMapping("login")
	public String login(HttpSession httpSession,
			@RequestParam(value="out", defaultValue="false") Boolean out) {
		if(out == true){
			httpSession.removeAttribute("master");
		}
		return "admin/home/login";
	}
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(@RequestParam("id") String id, 
			@RequestParam("password") String password, ModelMap model, HttpSession httpSession) {
		try {
			Master master = masterService.get(id);
			if(master.getPassword().equals(password)){
				httpSession.setAttribute("master", master);
				model.addAttribute("message", "Dang nhap thanh cong!");
				
				String reqUrl = (String) httpSession.getAttribute("RequestUrl");
				if(reqUrl != null){
					httpSession.removeAttribute("RequestUrl");
					return "redirect:" + reqUrl;
				}
			}
			else{
				model.addAttribute("message", "Sai mat khau!");
			}
		} 
		catch (Exception e) {
			model.addAttribute("message", "Sai ten dang nhap!");
		}
		return "admin/home/login";
	}
}
