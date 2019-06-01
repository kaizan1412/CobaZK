package dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;

public class TrOrderHeaderDto {

	private Date createdDate;// = LocalDateTime.now();
	private String createdUser;
	private Date updatedDate;
	private String updatedUser;
	private Boolean deleted;
	
	private String noNota;	
	private Date transactionDate;
	private BigDecimal totalPrice;
	private Integer globalDiscount;
//	private String customerCode;
//	private String employeeCode;
	private MstCustomerDto customer;
	private MstEmployeeDto employee;
	
	public String getNoNota() {
		return noNota;
	}
	public void setNoNota(String noNota) {
		this.noNota = noNota;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getGlobalDiscount() {
		return globalDiscount;
	}
	public void setGlobalDiscount(Integer globalDiscount) {
		this.globalDiscount = globalDiscount;
	}
//	public String getCustomerCode() {
//		return customerCode;
//	}
//	public void setCustomerCode(String customerCode) {
//		this.customerCode = customerCode;
//	}
//	public String getEmployeeCode() {
//		return employeeCode;
//	}
//	public void setEmployeeCode(String employeeCode) {
//		this.employeeCode = employeeCode;
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
	public MstCustomerDto getCustomer() {
		return customer;
	}
	public void setCustomer(MstCustomerDto customer) {
		this.customer = customer;
	}
	public MstEmployeeDto getEmployee() {
		return employee;
	}
	public void setEmployee(MstEmployeeDto employee) {
		this.employee = employee;
	}
	
}
