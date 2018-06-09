package au.edu.swu.dao;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.edu.swu.domain.File;

@Repository
public class FileDao {
	 @Autowired
	 private SessionFactory sessionFactory;
	 
	 private Session getSession() {
		 return sessionFactory.getCurrentSession();
	 }

	public void savaFile(File fileData) {
		this.getSession().save(fileData);
		
	}
	public List<File> getFiles(){
		 return this.getSession().createQuery("from File").list();
		
	}

	public void deleteFile(Integer id) {
		this.getSession().createQuery("delete from File where id=?")
					     .setParameter(0, id).executeUpdate();
		
	}

	public List<File> findFile(String fileName) throws UnsupportedEncodingException {
		
		String fileName1=new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
		
		List list = this.getSession().createQuery("from File where fileName like ?").setParameter(0, "%"+fileName1+"%").list();
		return list;
	}

	public List<File> findByUsername(int id) {
		List<File> list = this.getSession().createQuery("from File f where f.user.id=?").setInteger(0, id).list();
		return list;
	}
	 
	 
}
