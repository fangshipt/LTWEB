package vn.iotstar.model;

import java.io.Serializable;
import java.sql.Date;

public class UserModel implements Serializable{
	
	private static final long serialVersionUID = 1956971091551224915L;
	private int id;
	private String username;
	private String password;
	private String fullname;
	private String avatar;
	private String email;
	private String phone;
	private int roleid;
	private String code;
	

	private Date createDate;
	
	public UserModel() {
		super();
	}

	public UserModel(int id, String username, String password, String fullname, String avatar, String email,
			String phone, int roleid, Date createDate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.email = email;
		this.phone = phone;
		this.roleid = roleid;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public UserModel(int id, String username, String password, String fullname, String avatar, String email,
			String phone, int roleid, String code, Date createDate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.email = email;
		this.phone = phone;
		this.roleid = roleid;
		this.code = code;
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", username=" + username + ", password=" + password + ", fullname=" + fullname
				+ ", avatar=" + avatar + ", email=" + email + ", phone=" + phone + ", roleid=" + roleid
				+ ", createDate=" + createDate + "]";
	}
	
	
	
	
		
}
