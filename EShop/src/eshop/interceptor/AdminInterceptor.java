package eshop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import eshop.entity.Master;
import eshop.service.ActionRoleService;

import eshop.service.WebActionService;

public class AdminInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	WebActionService webActionService;
	@Autowired
	ActionRoleService actionRoleService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		RequestUrl trong httpSession: địa chỉ thực tại đang ở
//		requestUrl mới: địa chỉ truy cập
//		Trong cùng 1 phiên làm việc (1 trình duyệt), vd trước khi ta đã truy cập Chủng loại trước khi clean project và chạy lại 
//		(phiên vẫn tiếp tục) ->  RequestUrl trong httpSession: /admin/category/index.php
//		Sau khi bấm Nhà cung cấp -> requestUrl mới = /admin/supplier/index.php
		//System.out.println("7------------------");
		String cpath = request.getContextPath(); //cpath=/EShop
		String requestUrl = request.getRequestURI().replace(cpath, ""); 
		System.out.println("requestUrl="+requestUrl);
		HttpSession httpSession = request.getSession();
		Master master = (Master) httpSession.getAttribute("master");
		
		/*
		 * Khong dang nhap => khong vao trang quan tri
		 */
		if(master == null){
			//System.out.println("RequestUrlSessionF="+httpSession.getAttribute("RequestUrl"));
			httpSession.setAttribute("RequestUrl", requestUrl);
			//System.out.println("RequestUrlSessionS="+httpSession.getAttribute("RequestUrl"));
			//đưa session về địa chỉ truy cập (ở trên là /admin/supplier/index)
			response.sendRedirect(cpath + "/admin/home/login.php");
			//trả về login để đăng nhập -> chạy lại preHandler)
			return false; //không cho phép thực hiện tiếp
		}
//		7------------------
//		requestUrl=/admin/supplier/index.php
//		RequestUrlSessionF=/admin/category/index.php 	Url trước đó
//		RequestUrlSessionS=/admin/supplier/index.php	Url truy cập
//		7------------------đã đăng nhập lại -> chạy lại preHandler
//		requestUrl=/admin/supplier/index.php
//		actionname=supplier/index
//		
		
		
		//Sau khi đăng nhập rồi
		String[] paths = requestUrl.split("[./]"); // 0/1admin/2supplier/3index.4php
		String actionname = paths[2] + "/" + paths[3];
		//System.out.println("actionname="+actionname);
		/*
		 * Action khong nhap vao WebActions 
		 */
		 boolean exist = webActionService.exist(actionname);
		 if(exist == false){
			 return true; //ko có actionname đó -> vẫn thực hiện vì ko có phân quyền -> ai làm cũng được
		 }
		 
		 exist = actionRoleService.exist(master, actionname);
		 if(exist == true){
			 return true;
		 }
		 
		 httpSession.setAttribute("RequestUrl", requestUrl);
		 response.sendRedirect(cpath + "/admin/home/unauthorize.php");
		return false;
	}
}
