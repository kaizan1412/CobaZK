package dto;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;

public class MstUserDto {
	
	private Date createdDate;// = LocalDateTime.now();
	private String createdUser;
	private Date updatedDate;
	private String updatedUser;
	private Boolean deleted = false;
	
	private String id;
	private String username;
	private String password;
	
//	public LocalDateTime getCreatedDate() {
//		return createdDate;
//	}
//	public void setCreatedDate(LocalDateTime createdDate) {
//		this.createdDate = createdDate;
//	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
//	public LocalDateTime getUpdatedDate() {
//		return updatedDate;
//	}
//	public void setUpdatedDate(LocalDateTime updatedDate) {
//		this.updatedDate = updatedDate;
//	}
	public String getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Override
	public String toString() {
		return "MstUserDto [createdDate=" + createdDate + ", createdUser="
				+ createdUser + ", updatedDate=" + updatedDate
				+ ", updatedUser=" + updatedUser + ", deleted=" + deleted
				+ ", id=" + id + ", username=" + username + ", password="
				+ password + "]";
	}

}
