package dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

public class TrOrderDetailDto {
	
	private Date createdDate;// = LocalDateTime.now();
	private String createdUser;
	private Date updatedDate;
	private String updatedUser;
	private Boolean deleted;
	
	private String detailId;
	private Long qty;
	private BigDecimal subtotal;
	private Integer discount;
	private BigDecimal unitPrice;
	private String productCode;
	private String noNota;
	private MstProductDto product;
	
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getNoNota() {
		return noNota;
	}
	public void setNoNota(String noNota) {
		this.noNota = noNota;
	}
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
	public MstProductDto getProduct() {
		return product;
	}
	public void setProduct(MstProductDto product) {
		this.product = product;
	}

}
