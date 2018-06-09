package au.edu.swu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import au.edu.swu.domain.User;
import au.edu.swu.service.FileService;
import au.edu.swu.service.UserService;
import au.edu.swu.util.Msg;

@Controller
public class EchartsController {

	@Autowired
	private UserService userService;
	@Autowired
	private FileService fileService;
	
	
	@ResponseBody
	@RequestMapping("/echarts")
	public Msg getNameAndNums() {
		
		//需要将封装 用户名--上传文件数量 封装在中
		
		//所有用户列表
		List<User> list = userService.getAll();
		Map<String,Integer> map=new HashMap<String, Integer>();
		for (User user : list) {
			//通过用户名查找文件数量
			int fileNums = fileService.findNumsByid(userService.getUserByUserName(user.getUsername()).getId());
			map.put(user.getUsername(), fileNums);
		}
		return Msg.success().add("echarts", map);
	}
	
	
	@ResponseBody
	@RequestMapping("/echarts2")
	public Msg getNameAndNums3() {
		List<Map<String, Object>> data=new ArrayList<Map<String,Object>>();
		//所有用户列表
		List<User> list = userService.getAll();
		for (User user : list) {
			//通过用户名查找文件数量
			int fileNums = fileService.findNumsByid(userService.getUserByUserName(user.getUsername()).getId());
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("value", fileNums);
			map.put("name", user.getUsername());
			data.add(map);
		}
		return Msg.success().add("echarts3", data);
	}
}














