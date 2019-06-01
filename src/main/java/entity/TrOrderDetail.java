package entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import component.BaseEntity;
import entity.pk.TrOrderDetailPk;
import entity.pk.TrOrderHeaderPk;

@Entity
@Table(name="tr_order_detail")
@IdClass(value=TrOrderDetailPk.class)
public class TrOrderDetail extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="detail_id")
	private String detailId;
	@Column(name="qty")
	private Long qty;
	@Column(name="subtotal")
	private BigDecimal subtotal;
	@Column(name="discount")
	private Integer discount;
	@Column(name="unit_price")
	private BigDecimal unitPrice;
	@Column(name="product_code")
	private String productCode;
	@Column(name="no_nota")
	private String noNota;
	
	public TrOrderDetail() {
		// TODO Auto-generated constructor stub
	}

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
	
}
