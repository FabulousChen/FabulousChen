package au.edu.swu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import au.edu.swu.util.Msg;

@Controller
public class LookPDFController {
	String FileName="";
	
	/**
	 * 在线预览pdf
	 * @param response
	 */
	@RequestMapping(value="/displayPDF.do")
    public void displayPDF(HttpServletResponse response,@RequestParam("pdfFileName")String pdfFileName) {
        try {
        	System.out.println("D:/upload/"+pdfFileName);
            File file = new File("D:/upload/"+pdfFileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment;fileName=Java.pdf");
            response.setContentType("multipart/form-data");
            OutputStream outputStream = response.getOutputStream();
            IOUtils.write(IOUtils.toByteArray(fileInputStream), outputStream);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
	/**
	 * 处理浏览文件请求
	 */
	@RequestMapping(value="/lookPDF")
	public String lookPDF(@RequestParam("fileName") String fileName,Map<String,Object>map) {
		 map.put("fileName", fileName);
		// System.out.println(fileName);
		 FileName=fileName;
		 return "redirect:/pdfjs/web/viewer.html";
	}
	/**
	 * 返回Json数据用于html使用Ajax获取文件名
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getPDFFileName")
	public Msg getFileName() {
		
		return Msg.success().add("FileName", FileName);
		
	}
	
}
