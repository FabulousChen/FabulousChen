package au.edu.swu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.edu.swu.domain.File;
import au.edu.swu.domain.Page;

@Repository
public class PageDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 分页查询，返回需要页的数据的list
	 * @param offset
	 * @param length
	 * @return
	 */
	public List<File> queryForPage(int offset, int length) {
		// TODO Auto-generated method stub
		List<File> entitylist = null;
		try {
			Query query = this.getSession().createQuery("from File");
			query.setFirstResult(offset);
			query.setMaxResults(length);
			entitylist = query.list();

		} catch (RuntimeException re) {
			throw re;
		}

		return entitylist;
	}

	/**
	 * 获取所有记录数，用于计算总页数
	 * 
	 * @return
	 */
	public int getCounts() {
		List<File> list = this.getSession().createQuery("from File").list();
		return list.size();

	}
	
}
