package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

import component.BaseEntity;
import entity.pk.TrOrderHeaderPk;

@Entity
@Table(name="tr_order_header")
@IdClass(value=TrOrderHeaderPk.class)
public class TrOrderHeader extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="no_nota")
	private String noNota;	
	@Column(name="transaction_date")
	private Date transactionDate;
	@Column(name="total_price")
	private BigDecimal totalPrice;
	@Column(name="global_discount")
	private Integer globalDiscount;
	@Column(name="customer_code")
	private String customerCode;
	@Column(name="employee_code")
	private Integer employeeCode;

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
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public Integer getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(Integer employeeCode) {
		this.employeeCode = employeeCode;
	}
	
}
