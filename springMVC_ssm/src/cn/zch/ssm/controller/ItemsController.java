package cn.zch.ssm.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.zch.ssm.exception.BusinessException;
import cn.zch.ssm.po.Items;
import cn.zch.ssm.po.QueryVo;
import cn.zch.ssm.po.User;
import cn.zch.ssm.service.ItemsService;

@Controller
@RequestMapping("items")
public class ItemsController {

	@Autowired
	ItemsService service;
	
	@RequestMapping(value={"/queryItems","/items.do"},method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView queryItems(){
		List<Items> itemsList = service.findAll();
		
		//创建modelAndView对象  用于封装数据 并 响应
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("itemsList", itemsList);
		
		//添加视图
		modelAndView.setViewName("itemsList");
		return modelAndView;
	}
	
	@RequestMapping("/queryItemsByid")
	public ModelAndView queryItemsByid(Integer id){
		Items items = service.queryItemsByid(id);
		
		ModelAndView model = new ModelAndView();
		model.addObject("items", items);
		
		model.setViewName("editItems");
		return model;
	}
	@RequestMapping("/itemsEdit")
	public ModelAndView itemsEdit(Integer id){
		Items items = service.queryItemsByid(id);
		
		ModelAndView model = new ModelAndView();
		model.addObject("items", items);
		
		model.setViewName("editItems");
		return model;
	}
	
	@RequestMapping("/findItemsByid")
	public ModelAndView queryItemsByid(Items items){
		Items item = service.queryItemsByid(items.getId());
		
		ModelAndView model = new ModelAndView();
		model.addObject("items", item);
		
		model.setViewName("editItems");
		return model;
	}
	
	@RequestMapping("/updateItems")
	public ModelAndView updateItems(Items items,MultipartFile pictureFile,HttpServletRequest request,HttpServletResponse response) throws BusinessException{
		
		//获取picture的名字
		if(pictureFile != null){
			String originalFilename = pictureFile.getOriginalFilename();
			System.out.println(originalFilename);
			
			if(!StringUtils.isEmpty(originalFilename)){
				String path= "G:\\pt下载\\";
				
				//确定新名字
				String newFileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
				System.out.println(newFileName);
				
//				保存新文件
				File file = new File(path + newFileName);
				items.setPic(newFileName);
				try {
					pictureFile.transferTo(file);
				} catch (IllegalStateException | IOException e) {
					throw new BusinessException("图片保存失败");
				}
			}
		}
		
		
		service.save(items);
		
		List<Items> itemsList = service.findAll();

		// 创建modelAndView对象 用于封装数据 并 响应
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("itemsList", itemsList);
		
//		request.getRequestDispatcher("1").forward(request, response);
//		response.sendRedirect(location);

		// 添加视图
		modelAndView.setViewName("itemsList");
		return modelAndView;
	}
	
	@RequestMapping("/queryItems2")
	public ModelAndView queryItems2(QueryVo qv) throws UnsupportedEncodingException{
		System.out.println(qv.getItems());
		System.out.println(new String(qv.getItems().getName().getBytes("iso-8859-1"),"utf-8"));
		return null;
	}
	
	@RequestMapping("/deleteSel")
	public String deleteSel(Integer[] id){
		System.out.println(Arrays.toString(id));
		return "forward:queryItems";
	}
	
	@RequestMapping("/editSel")
	public String editSel(QueryVo vo){
		System.out.println(vo.getItemsList().toString());
		return "redirect:queryItems.do";
	}
	
	
	@RequestMapping("/hello")
	public void hello(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		request.setAttribute("hello", "hello");
//		response.sendRedirect(request.getContextPath() + "/jsp/hello.jsp");
		request.getRequestDispatcher("/jsp/hello.jsp").forward(request, response);
//		return "forward:jsp/hello.jsp";
	}
	
	@RequestMapping("exception")
	public void exception() throws Exception{
		throw new Exception("有异常");
	}
	
	@RequestMapping("/requestKV")
	@ResponseBody
	public Items requestKV(Items items){
		return items;
	}
	@RequestMapping("/requestJSON")
	@ResponseBody
	public Items requestJSON(@RequestBody Items items){
		return items;
	}
	
	@RequestMapping("/requestPath/{id}")
	@ResponseBody
	public Items requestPath(@PathVariable Integer id){
		Items items = service.queryItemsByid(id);
		return items;
	}
	
	@RequestMapping("/login")
	public String login(User user,HttpServletResponse response,HttpServletRequest request){
		request.getSession().setAttribute("userLogin", user);
		return "redirect:/jsp/index.jsp";
	}
}
