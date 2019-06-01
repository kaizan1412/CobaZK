package dto;

import java.util.Date;

import javax.persistence.Column;

import entity.MstCity;
import entity.enumcol.GenderEnum;

public class MstCustomerDto {

	private Date createdDate;// = LocalDateTime.now();
	private String createdUser;
	private Date updatedDate;
	private String updatedUser;
	private Boolean deleted = false;
	
	private String customerCode;
	private String customerName;
	private MstCityDto city;
//	private String provinceCode;
	private MstProvinceDto province;
	private Date dateOfBirth;
	private GenderEnum gender;
	private String postalCode;
	private String address;
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public GenderEnum getGender() {
		return gender;
	}
	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
//	public String getCityCode() {
//		return cityCode;
//	}
//	public void setCityCode(String cityCode) {
//		this.cityCode = cityCode;
//	}
//	public String getProvinceCode() {
//		return provinceCode;
//	}
//	public void setProvinceCode(String provinceCode) {
//		this.provinceCode = provinceCode;
//	}
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
	public MstCityDto getCity() {
		return city;
	}
	public void setCity(MstCityDto city) {
		this.city = city;
	}
	public MstProvinceDto getProvince() {
		return province;
	}
	public void setProvince(MstProvinceDto province) {
		this.province = province;
	}
	
}
