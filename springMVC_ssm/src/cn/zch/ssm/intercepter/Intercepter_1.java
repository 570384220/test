package cn.zch.ssm.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.zch.ssm.po.User;

public class Intercepter_1 implements HandlerInterceptor{

	/*执行完handler之后执行*/
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("after_1");
	}

	//执行handler  但在 返回modelandvoiew之前执行
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("post_1");
	}

	//执行handler之前执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		System.out.println("pre_1");
		//获取请求的url
		String requestURI = request.getRequestURI();
		//如果用户直接登录 则放行
		if(requestURI.contains("login")){
			return true;
		}
		
		//若用户以前登录过     也放行
		User user = (User) request.getSession().getAttribute("userLogin");
		if(user != null){
			return true;
		}
		
		//否者转发到登录页面
		response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
		return false;
	}

}
