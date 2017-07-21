package cn.zch.ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class BusinessExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) {
		BusinessException be = null;
		if(exception instanceof BusinessException){
			be = (BusinessException)exception;
		}else{
			be = new BusinessException("未知异常");
		}
		ModelAndView model = new ModelAndView();
		model.addObject("error", be.getMsg());
		model.setViewName("error");
		return model;
	}

}
