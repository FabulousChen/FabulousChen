package au.edu.swu.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import au.edu.swu.domain.User;
import au.edu.swu.service.FileService;
import au.edu.swu.service.UserService;

@Controller
public class FileController {

	@Autowired
	private FileService fileService;
	@Autowired
	private UserService userService;
	
	/**
	 * 上传文件
	 * @param request
	 * @param file
	 * @param username
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file,
			@RequestParam("username") String username) throws Exception {

		if (!file.isEmpty()) {
			User user = userService.getUserByUserName(username);
			
			String storePath = "D:"  +File.separator + "upload";

			File store = null; // 目的文件
			String filename = file.getOriginalFilename();
			// 存在每个用户有一个自己名字命名的文件夹
			store = new File(storePath + File.separator + username, filename);

			long size = file.getSize(); // 上传文件的大小
			// 上传文件到本地硬盘
			file.transferTo(new File(storePath + File.separator + filename));
			// 将文件信息保存在数据库
			au.edu.swu.domain.File fileData=new au.edu.swu.domain.File();
			fileData.setFileName(filename);
			fileData.setFilePath(storePath);
			fileData.setFileSize(String.valueOf(size));
			fileData.setCreateTime(new Date());
			fileData.setUser(user);
			fileService.savaFile(fileData);
			return "redirect:/loginSuccess";
		}else {
			return "redirect:/uploadfail.jsp";
		}
	}
	
	/**
	 * 删除文件
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value="/deleteFile")
	public String deleteFile(@RequestParam("id") Integer id,@RequestParam("username") String username,
								@RequestParam("author")String author) {
		if(author.equals(username)) {
			//上传者和登录者相同可以删除
			fileService.deleteFile(id);
			return "redirect:/loginSuccess";
		}else {
			//没有权限删除
			return "deleteFail";
		}	
	}
	/**
	 * 文件下载
	 * @param request
	 * @param fileName
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/download")
	public ResponseEntity<byte[]> download(HttpServletRequest request,
            @RequestParam("fileName") String fileName,
            Model model)throws Exception {
       //下载文件路径
       String path = "D:"  +File.separator + "upload";
       File file = new File(path + File.separator + fileName);
       HttpHeaders headers = new HttpHeaders();  
       //下载显示的文件名，解决中文名称乱码问题  
       String downloadFielName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
       //通知浏览器以attachment（下载方式）打开
       headers.setContentDispositionFormData("attachment", downloadFielName); 
       //application/octet-stream ： 二进制流数据（最常见的文件下载）。
       headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
       return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
               headers, HttpStatus.CREATED);  
    }
	/**
	 * 文件搜索
	 * @param fileName
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/searchFile",method=RequestMethod.POST)
	public String searchFile(@RequestParam(value="fileName") String fileName,
							Map<String,Object>map) throws UnsupportedEncodingException {
		List<au.edu.swu.domain.File> files = fileService.findFile(fileName);
		map.put("files", files);
		
		return "findSuccess";
	}
	/**
	 * 根据用户名查找文件
	 * 转换成根据id查找文件
	 * @return
	 */
	@RequestMapping(value="/myFile",method=RequestMethod.POST)
	public String myFile(@RequestParam("username")String username,Map<String,Object>map) {
		User user = userService.getUserByUserName(username);
		List<au.edu.swu.domain.File> list=fileService.findByUsername(user.getId());
		map.put("files", list);
		return "MyFileList";
	}
}
