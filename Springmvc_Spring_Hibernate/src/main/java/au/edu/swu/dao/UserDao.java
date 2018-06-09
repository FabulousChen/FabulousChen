package au.edu.swu.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.edu.swu.domain.User;

@Repository
public class UserDao {

	 @Autowired
	 private SessionFactory sessionFactory;
	 
	 private Session getSession() {
		 return sessionFactory.getCurrentSession();
	 }
	 /**
	  * 检查用户名密码是否正确
	  * @param username
	  * @param password
	  * @return
	  */
	 public Boolean checkUser(String username, String password) {
		 String hql = "FROM User u WHERE u.username = ? AND u.password = ?";
		@SuppressWarnings("unchecked")
		User user=(User) this.getSession().createQuery(hql).setString(0, username).setString(1, password).uniqueResult();
			if(user!=null) {
				System.out.println(user.toString());
				return true;
			}
			return false;
		}
	 /**
	  * 根据用户名查询用户
	  * @param username
	  * @return
	  */
	public User getUserByUserName(String username) {
		User user = (User) this.getSession().createQuery("FROM User u WHERE u.username = ?").setString(0, username).uniqueResult();
		return user;
	}
	/**
	 * 用于用户注册
	 * @param username
	 * @param password
	 */
	public void savaUser(String username, String password) {
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		this.getSession().save(user);
		
	}
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> getAll() {
		List list = this.getSession().createQuery("FROM User").list();
		return list;
	}
}
