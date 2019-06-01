package dto;

import java.time.LocalDateTime;
import java.util.Date;





import entity.MstDepartment;
import entity.MstCity;
import entity.enumcol.GenderEnum;

public class MstEmployeeDto {
	
	private Date createdDate;// = LocalDateTime.now();
	private String createdUser;
	private Date updatedDate;
	private String updatedUser;
	private Boolean deleted = false;
	
	private Integer id;
	private String employeeName;
	private Date dateOfBirth;
	private MstDepartment department;
	private GenderEnum gender;
	private String birthPlace;
	private String cityCode;
	private String provinceCode;
	private String address;
	private String postalCode;
	private String username;
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public MstDepartment getDepartment() {
		return department;
	}
	public void setDepartment(MstDepartment department) {
		this.department = department;
	}
	public GenderEnum getGender() {
		return gender;
	}
	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	@Override
	public String toString() {
		return "MstEmployeeDto [createdDate=" + createdDate + ", createdUser="
				+ createdUser + ", updatedDate=" + updatedDate
				+ ", updatedUser=" + updatedUser + ", deleted=" + deleted
				+ ", id=" + id + ", employeeName=" + employeeName
				+ ", dateOfBirth=" + dateOfBirth + ", department=" + department
				+ ", gender=" + gender + ", birthPlace=" + birthPlace
				+ ", cityCode=" + cityCode + ", provinceCode=" + provinceCode
				+ ", address=" + address + ", postalCode=" + postalCode + "]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}


}
