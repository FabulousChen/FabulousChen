package au.edu.swu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.edu.swu.dao.UserDao;
import au.edu.swu.domain.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 检查用户名是否正确
	 * @param username
	 * @param password
	 * @return
	 */
	@Transactional
	public Boolean checkUser(String username, String password) {
		return userDao.checkUser(username, password);
		
	}
	/**
	 * 通过名字查找用户
	 * @param username
	 * @return
	 */
	@Transactional
	public User getUserByUserName(String username) {
		User user=userDao.getUserByUserName(username);
		return user;
	}
	/**
	 * 保存用户
	 * @param username
	 * @param password
	 */
	@Transactional
	public void savaUser(String username, String password) {
		userDao.savaUser(username,password);
		
	}
	/**
	 * 查找所有用户
	 * @return
	 */
	@Transactional
	public List<User> getAll() {
		List<User> list=userDao.getAll();
		return list;
	}
	
}
