package entity.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MstCustomerPk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customerCode;

	public MstCustomerPk() {
		// TODO Auto-generated constructor stub
	}
	
	public MstCustomerPk(String customerCode) {
		this.customerCode = customerCode;
	}
	
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
	
}
