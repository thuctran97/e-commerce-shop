package eshop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import eshop.entity.Customer;

public class AuthenticateInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession httpSession = request.getSession();
		Customer user = (Customer) httpSession.getAttribute("user");
		if(user == null){
			String cpath = request.getContextPath();
			String url = request.getRequestURI().replace(cpath, "");
			httpSession.setAttribute("BackUrl", url);
			response.sendRedirect(cpath+"/account/login.php");
		}
		return true;
	}
}
