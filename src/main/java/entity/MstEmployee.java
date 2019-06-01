package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import component.BaseEntity;
import entity.enumcol.GenderEnum;
import entity.pk.MstEmployeePk;

@Entity
@Table(name="mst_employee")
@IdClass(value=MstEmployeePk.class)
public class MstEmployee extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 209321702337294118L;

	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="employee_name")
	private String employeeName;
	
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	
	@Column(name="gender")
	private GenderEnum gender;
	
	@ManyToOne
	private MstDepartment department;
	
	@Column(name="birth_place")
	private String birthPlace;
	
	@Column(name="city_code")
	private String cityCode;
	
	@Column(name="province_code")
	private String provinceCode;
	
	@Column(name="address")
	private String address;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@Column(name="username")
	private String username;
	
	public void setId(Integer id) {
		this.id = id;
	}

//	@Override
	public Integer getId() {
		return this.id;
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

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
