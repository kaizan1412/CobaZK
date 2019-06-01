package entity.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MstProductPk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productCode;
	
	public MstProductPk() {
		// TODO Auto-generated constructor stub
	}
	
	public MstProductPk(String productCode) {
		this.productCode = productCode;
	}
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	
	
}
