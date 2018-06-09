package au.edu.swu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import au.edu.swu.domain.File;
import au.edu.swu.domain.Page;
import au.edu.swu.domain.User;
import au.edu.swu.service.FileService;
import au.edu.swu.service.PageService;
import au.edu.swu.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userservice;
	@Autowired
	private FileService fileservice;
	@Autowired
	private PageService pageService;
	/**
	 * 返回登录成功的首页
	 * @param map
	 * @return
	 */
	@RequestMapping("/loginSuccess")
	public String loginSuccess(Map<String,Object>map) {
		//直接去第一页，并且一页10条数据
		List<File> files = pageService.queryForPage(1, 10).getList();
		map.put("files", files);
//		List<File> files = fileservice.getFiles();
//		map.put("files", files);
		return "loginSuccess";
	}
	/**
	 * 验证用户名密码是否正确
	 * @param username
	 * @param password
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(@RequestParam("username")String username,
						@RequestParam("password")String password,Map<String,Object>map,
						HttpSession session) {
		
		Boolean b=userservice.checkUser(username,password);
		ModelAndView mav=null;
		if(b) {
			session.setAttribute("username", username);
			
			map.put("username", username);
//			List<File> files = fileservice.getFiles();
//			map.put("files", files);
			
			//直接去第一页，并且一页10条数据
			List<File> files = pageService.queryForPage(1, 10).getList();
			map.put("files", files);
			
			
			mav = new ModelAndView("loginSuccess");
			return mav;
		}else {
			mav = new ModelAndView("redirect:/index.jsp");
			mav.addObject("login_msg","密码错误");
			return mav;
			
		}
		
			
	}
	/**
	 * 注册跳转页面
	 * @return
	 */
	@RequestMapping("/signUp")
	public String signUp() {
		return "signUp";
	}
	@RequestMapping(value="/sign",method=RequestMethod.POST)
	public String sign(@RequestParam("username")String username,
			@RequestParam("password")String password,Map<String,Object>map) {
		User user = userservice.getUserByUserName(username);
		if(user==null) {
			userservice.savaUser(username,password);
			map.put("msg", "注册成功请登录");
			return "redirect:/index.jsp";
		}else {
			map.put("msg", "用户已经存在,请重新注册");
			return "signUp";
		}
		
		
		
	}
}
