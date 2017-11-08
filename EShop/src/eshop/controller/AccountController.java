package eshop.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import eshop.entity.Customer;
import eshop.service.CustomerService;
import eshop.service.MailerService;

@Controller
@RequestMapping("account")
public class AccountController {
	@Autowired
	CustomerService customerService;
	
	@Autowired
	MailerService mailerService;
	
	@Autowired
	ServletContext app; //tìm lại đường dẫn từ website
	
	/*
	 * Dang nhap 
	 */
	@RequestMapping("login")
	public String login() {
		return "user/account/login";
	}
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(ModelMap model,
			@RequestParam("id") String id,
			@RequestParam("password") String password,
			@RequestParam(value="remember", defaultValue="false") Boolean remember,
			HttpSession httpSession, 
			HttpServletResponse response) {
		try {
			Customer user = customerService.get(id);
			if(user.getActivated() == false){
				model.addAttribute("message", "Tài khoản chưa được kích hoạt!");
			}
			else if(user.getPassword().equals(password)){
				httpSession.setAttribute("user", user);
				//ghi thông tin user vào session 
				model.addAttribute("message", "Đăng nhập thành công!");
				/*
				 * Ghi nho tai khoan bang cookie
				 */
				Cookie ckId = new Cookie("uid", id);
				Cookie ckPw = new Cookie("pwd", password);
				if(remember == true){
					//tạo cookie gửi về client
					ckId.setMaxAge(5*24*60*60);
					ckPw.setMaxAge(5*24*60*60);
				}
				else{
					//xóa cookie
					ckId.setMaxAge(0);
					ckPw.setMaxAge(0);
				}
				response.addCookie(ckId);
				response.addCookie(ckPw);
				
				// Quay tro lai trang truy cap truoc do
				String url = (String) httpSession.getAttribute("BackUrl");
				if(url != null){
					return "redirect:"+url;
				}
				//cố tình đăng nhập một trang khi đang ở một trang khác (thay đường dẫn)
			}
			else{
				model.addAttribute("message", "Sai tên mật khẩu!");
			}
		} 
		catch (Exception e) {
			model.addAttribute("message", "Sai tên đăng nhập!");
		}
		
		return "user/account/login";
	}
	
	/*
	 * Dang xuat 
	 */
	@RequestMapping("logoff")
	public String logoff(HttpSession httpSession) {
		httpSession.removeAttribute("user");
		return "redirect:/home/index.php";
		// action chuyển sang action home/index.php
	}
	
	/*
	 * Dang ky 
	 */
	@RequestMapping("register")
	public String register(ModelMap model) {
		model.addAttribute("user", new Customer());
		return "user/account/register";
	}
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String register(ModelMap model, 
			@ModelAttribute("user") Customer user, 
			@RequestParam("password1") String password1,
			HttpServletRequest request,
			@RequestParam("upphoto") MultipartFile photo) {
		if(password1.equals(user.getPassword())){
			try {
				if(!photo.isEmpty()){
					String filename = System.currentTimeMillis() + "-" + photo.getOriginalFilename();
					//ngăn ngừa việc upload file có tên giống với file trong database -> đè lên file cũ
					// lấy thời gian của hệ thống ghép vào để không trùng
					user.setPhoto(filename);
					
					String path = app.getRealPath("/images/customers/"+user.getPhoto());
					photo.transferTo(new File(path));
				}
				else{
					user.setPhoto("user.png");
				}
				user.setActivated(false);
				customerService.insert(user);
				model.addAttribute("message", "Đăng ký thành công");
				/*
				 * Gui mail kich hoat tai khoan 
				 */
				try {
					String url = request.getRequestURL().toString().replace("register", "activate/"+user.getId());
					String to = user.getEmail();
					String subject = "Welcome to eshop";
					String body = "Click vào liên kết sau để kích hoạt tài khoản<hr>" +
							"<a href='"+url+"'>Activate you account</a>";
					mailerService.send(to, subject, body);
					model.addAttribute("message", "Vui lòng check mail và kích họat tài khoản");
				} 
				catch (Exception e) {
					model.addAttribute("message", "Không gửi được email");
				}
			} 
			catch (Exception e) {
				model.addAttribute("message", "Đăng ký thất bại");
			}	
		}
		else{
			model.addAttribute("message", "Xác nhận mật khẩu không đúng");
		}
		return "user/account/register";
	}
	
	/*
	 * Kich hoat tai khoan 
	 */
	@RequestMapping("activate/{id}")
	public String activate(@PathVariable("id") String id) {
		Customer user = customerService.get(id);
		user.setActivated(true);
		customerService.update(user);
		return "redirect:/account/login.php";
	}
	
	/*
	 * Quen mat khau 
	 */
	@RequestMapping("forgot")
	public String forgot() {
		return "user/account/forgot";
	}
	@RequestMapping(value="forgot", method=RequestMethod.POST)
	public String forgot(ModelMap model, 
			@RequestParam("id") String id,
			@RequestParam("email") String email) {
		try {
			Customer user = customerService.get(id);
			if(email.equals(user.getEmail())){
				// Gui mat khau
				try{
					mailerService.send(email, "Forgot Password", user.getPassword());
					model.addAttribute("message", "Mật khẩu đã được gửi qua email");
				}
				catch (Exception e) {
					model.addAttribute("message", "Không gửi được email");
				}
			}
			else{
				model.addAttribute("message", "Sai tên email đã đăng kí");
			}
		} 
		catch (Exception e) {
			model.addAttribute("message", "Sai tên đăng nhập");
		}
		return "user/account/forgot";
	}
	
	/*
	 * Doi mat khau 
	 */
	@RequestMapping("change")
	public String change() {
		return "user/account/change";
	}
	@RequestMapping(value="change", method=RequestMethod.POST)
	public String change(ModelMap model,
			@RequestParam("id") String id,
			@RequestParam("password") String password,
			@RequestParam("password1") String password1,
			@RequestParam("password2") String password2,
			HttpSession httpSession) {
		if(password1.equals(password2)){
			try {
				Customer user = customerService.get(id);
				if(password.equals(user.getPassword())){
					user.setPassword(password1);
					customerService.update(user);
					
					model.addAttribute("message", "Đổi mật khẩu thành công");
					httpSession.setAttribute("user", user);	
				}
				else{
					model.addAttribute("message", "Sai mật khẩu");
				}
			} 
			catch (Exception e) {
				model.addAttribute("message", "Sai tên đăng nhập");
			}
		}
		else{
			model.addAttribute("message", "Xác nhận mật khẩu không đúng");
		}
		return "user/account/change";
	}
	
	/*
	 * Cap nhat tai khoan 
	 */
	@RequestMapping("edit")
	public String edit(ModelMap model, HttpSession httpSession) {
		Customer user = (Customer) httpSession.getAttribute("user");
		model.addAttribute("user", user);
		return "user/account/edit";
	}
	@RequestMapping(value="edit", method=RequestMethod.POST)
	public String edit(ModelMap model, 
			HttpSession httpSession,
			@ModelAttribute("user") Customer user,
			@RequestParam("upphoto") MultipartFile photo) {
		try {
			if(!photo.isEmpty()){
				String path = app.getRealPath("/images/customers/"+user.getPhoto());
				File oldFile = new File(path);
				oldFile.delete();
				
				String filename = System.currentTimeMillis() + "-" + photo.getOriginalFilename();
				user.setPhoto(filename);
				path = app.getRealPath("/images/customers/"+user.getPhoto());
				photo.transferTo(new File(path));
			}
			customerService.update(user);
			httpSession.setAttribute("user", user);
			model.addAttribute("message", "Cập nhật thành công");
		} 
		catch (Exception e) {
			model.addAttribute("message", "Cập nhật thất bại");
		}
		return "user/account/edit";
	}
}
