package eshop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import eshop.service.CategoryService;
import eshop.service.SupplierService;

public class EShopInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	CategoryService categoryService;
	@Autowired
	SupplierService supplierService;
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("cates", categoryService.list());
		request.setAttribute("supps", supplierService.list());
	}
}
