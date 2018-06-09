package au.edu.swu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import au.edu.swu.domain.File;
import au.edu.swu.domain.Page;
import au.edu.swu.service.PageService;
import au.edu.swu.util.Msg;

/**
 * 因为如果登录成功的话直接显示列表页面,第一次登录的数据用的是 在UserController调用PageService封装好的数据
 * 
 * @author chen
 *
 */
@Controller
public class PageController {

	@Autowired
	private PageService pageService;

	/**
	 * 分页的文件列表Json数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getPageFile")
	public Msg PageFile(@RequestParam(value="pageNo") Integer pageNo) {
		Page page = new Page();
		try {
			if (pageNo == null) {
				pageNo = 1;
			}
			 page = pageService.queryForPage(pageNo, 10);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Msg.success().add("pageMsg", page);
	}
	
	@RequestMapping(value = "/getPageFileJsp")
	public String PageFileJsp(@RequestParam("pageNo") Integer pageNo,
								Map<String,Object>map) {
		
		 List files = pageService.queryForPage(pageNo, 10).getList();
		 map.put("files", files);
		
		return "/loginSuccess";
	}

}
