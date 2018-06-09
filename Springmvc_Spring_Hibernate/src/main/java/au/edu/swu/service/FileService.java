package au.edu.swu.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.edu.swu.dao.FileDao;
import au.edu.swu.domain.File;

@Service
public class FileService {

	@Autowired
	private FileDao filedao;

	/**
	 * 保存文件
	 * @param fileData
	 */
	@Transactional
	public void savaFile(File fileData) {
		filedao.savaFile(fileData);
		
	}
	/**
	 * 获取所有文件
	 * @return
	 */
	@Transactional
	public List<File> getFiles(){
		List<File> list = filedao.getFiles();
		return list;
	}
	/**
	 * 删除文件
	 * @param id
	 */
	@Transactional
	public void deleteFile(Integer id) {
		filedao.deleteFile(id);
		
	}
	/**
	 * 根据文件名查找文件
	 * @param fileName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@Transactional
	public List<File> findFile(String fileName) throws UnsupportedEncodingException {
		List<File> files= filedao.findFile(fileName);
		return files;
		
		
	}
	/**
	 * 根据用户id查找文件
	 * @param id
	 * @return
	 */
	@Transactional
	public List<File> findByUsername(int id) {
		List<File> list=filedao.findByUsername(id);
		return list;
	}
	/**
	 * 通过id返回上传的文件数
	 * @param id
	 * @return
	 */
	@Transactional
	public int findNumsByid(int id) {
		List<File> list=filedao.findByUsername(id);
		
		//System.out.println("============================"+list.size());
		return list.size();
	}
	
	
}
