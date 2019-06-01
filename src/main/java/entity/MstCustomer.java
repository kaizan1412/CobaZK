package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import component.BaseEntity;
import entity.enumcol.GenderEnum;
import entity.pk.MstCustomerPk;

@Entity
@Table(name="mst_customer")
@IdClass(value=MstCustomerPk.class)
public class MstCustomer extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="customer_code")
	private String customerCode;
	@Column(name="customer_name")
	private String customerName;
	@Column(name="city_code")
	private String cityCode;
	@Column(name="province_code")
	private String provinceCode;
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	@Column(name="gender")
	private GenderEnum gender;
	@Column(name="postal_code")
	private String postalCode;
	@Column(name="address")
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


}
