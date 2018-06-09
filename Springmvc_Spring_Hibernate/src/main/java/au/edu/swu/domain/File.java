package au.edu.swu.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="File")
public class File implements Serializable{
	private int id;
	private String fileName;
	private String filePath;
	private String fileSize;
	private Date createTime;
	private User user;
	
	@Id
	@Column(name="ID")
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="fileName")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Column(name="filePath")
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Column(name="fileSize")
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	@Column(name="createTime")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@JoinColumn(name="UserId")
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public File() {
		super();
	}
	public File(int id, String fileName, String filePath, String fileSize, Date createTime) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.createTime = createTime;
	}
	
	
}
