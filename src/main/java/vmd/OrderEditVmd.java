package vmd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Include;
import org.zkoss.zul.Messagebox;

import dto.MstCustomerDto;
import dto.MstEmployeeDto;
import dto.MstProductDto;
import dto.TrOrderDetailDto;
import dto.TrOrderHeaderDto;
import service.MstCustomerService;
import service.MstEmployeeService;
import service.MstProductService;
import service.TrOrderDetailService;
import service.TrOrderHeaderService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class OrderEditVmd {
	
	@WireVariable
	private TrOrderHeaderService trOrderHeaderSvc;

	@WireVariable
	private TrOrderDetailService trOrderDetailSvc;
	
	@WireVariable
	private MstProductService mstProductSvc;
	
	@WireVariable
	private MstCustomerService mstCustomerSvc;
	
	@WireVariable
	private MstEmployeeService mstEmployeeSvc;
	
	private TrOrderHeaderDto trOrderHeaderDto = new TrOrderHeaderDto();
	private TrOrderDetailDto trOrderDetailDto = new TrOrderDetailDto();
	private MstProductDto mstProductDto = new MstProductDto();
	private MstCustomerDto mstCustomerDto = new MstCustomerDto();
	private MstEmployeeDto mstEmployeeDto = new MstEmployeeDto();
	
	private List<TrOrderDetailDto> listOrderDetails = new ArrayList<TrOrderDetailDto>();
	private List<MstProductDto> listProducts = new ArrayList<MstProductDto>();
	private List<MstCustomerDto> listCustomers = new ArrayList<MstCustomerDto>();
	private List<MstEmployeeDto> listEmployees = new ArrayList<MstEmployeeDto>();
	
	private Boolean visible = true;
	private Boolean statusPopup = false;
	private Boolean readonly = false;
	private BigDecimal tampungSubTotal;
	private BigDecimal tampung;// = 0;
	private BigDecimal tampungHargaTotal;
	private BigDecimal totalSebelumDiskon;
	private Long stok = 0L;
	
	@Init
	@NotifyChange(value={"trOrderHeaderDto", "mstEmployeeDto", "listCustomers", "mstCustomerDto", "listOrderDetails", "listProducts", "visible", "readonly"})
	public void load(){
		trOrderHeaderDto = (TrOrderHeaderDto)Sessions.getCurrent().getAttribute("objOrderHeader");
		mstEmployeeDto = (MstEmployeeDto)Sessions.getCurrent().getAttribute("user");
		
		if(trOrderHeaderDto != null && trOrderHeaderDto.getNoNota() != null && !trOrderHeaderDto.getNoNota().equalsIgnoreCase("")){
			try {
				mstCustomerDto = mstCustomerSvc.findByCustomerCode(trOrderHeaderDto.getCustomer().getCustomerCode());
				listCustomers = mstCustomerSvc.findAllDeletedFalse();
				
				if(!trOrderHeaderDto.getNoNota().equalsIgnoreCase("")){
//					setVisible(Boolean.FALSE);
					setReadonly(Boolean.TRUE);
				}
				
				trOrderHeaderDto.setEmployee(mstEmployeeDto);
				listOrderDetails = trOrderDetailSvc.findByNoNota(trOrderHeaderDto.getNoNota());
				listProducts = mstProductSvc.findAllDeletedFalse();

			} catch (Exception e) {
				Messagebox.show("Error found. Msg: " + e.getCause());
			}
		}
		else{
			try {
				listCustomers = mstCustomerSvc.findAllDeletedFalse();

				if(trOrderHeaderDto.getNoNota() != null && !trOrderHeaderDto.getNoNota().equalsIgnoreCase("")){
//					setVisible(Boolean.FALSE);
					setReadonly(Boolean.TRUE);
				}
				
				trOrderHeaderDto.setEmployee(mstEmployeeDto);
				if(trOrderDetailSvc.findByNoNota(trOrderHeaderDto.getNoNota()) != null
					&& !trOrderDetailSvc.findByNoNota(trOrderHeaderDto.getNoNota()).isEmpty()
					&& trOrderDetailSvc.findByNoNota(trOrderHeaderDto.getNoNota()).size() > 0){
					listOrderDetails = trOrderDetailSvc.findByNoNota(trOrderHeaderDto.getNoNota());
				}
				else{
					listOrderDetails = new ArrayList<>();
				}
				listProducts = mstProductSvc.findAllDeletedFalse();
			} catch (Exception e) {
				Messagebox.show("Error found. Msg: " + e.getCause());
			}
		}
		
	}
	
	@Command(value="save")
	public void save(){
		TrOrderHeaderDto findHeader = trOrderHeaderSvc.findByNoNota(trOrderHeaderDto.getNoNota());
		
		if(findHeader == null && findHeader.getNoNota().equalsIgnoreCase("")){
			try {
				trOrderHeaderSvc.save(trOrderHeaderDto);

				for(TrOrderDetailDto detail : listOrderDetails){
					String productCode = trOrderDetailDto.getProductCode();
//					MstProductDto mstProductDto = new MstProductDto();
					mstProductDto = mstProductSvc.findByProductCode(productCode);
					
					Long stokAkhir = mstProductDto.getQuantity() - detail.getQty();

					if(stokAkhir < 0){
						Messagebox.show("Pembelian tidak dapat melebihi jumlah stok.");
					}
					else{
						mstProductDto.setQuantity(stokAkhir);
						mstProductDto.setProductCode(productCode);
						trOrderDetailSvc.save(detail);
						TrOrderDetailDto detailDto = trOrderDetailSvc.findOneById(detail.getDetailId());
						if(detailDto != null){
							mstProductSvc.update(mstProductDto);
						}
						Clients.showNotification("Data berhasil disimpan." ,
								Clients.NOTIFICATION_TYPE_INFO, null, null, 1500);
						Include inc = (Include)Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
						inc.setSrc("/transaksi/order.zul");
					}
					
				}
				
			} catch (Exception e) {
				Messagebox.show("Error found. Msg: " + e.getCause());
			}
		}
		else if(findHeader != null && !findHeader.getNoNota().equalsIgnoreCase("")){
			trOrderHeaderSvc.update(trOrderHeaderDto);
			Clients.showNotification("Data berhasil diupdate.", Clients.NOTIFICATION_TYPE_INFO, null, null, 1500);
			Include inc = (Include)Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
			inc.setSrc("/transaksi/order.zul");
		}
	}
	
	@Command(value="back")
	public void back(){
//		Executions.sendRedirect("/transaksi/order.zul");
		Include inc = (Include) Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
		inc.setSrc("/transaksi/order.zul");
	}
	
	@Command(value="addDetail")
	@NotifyChange(value={"statusPopup", "trOrderDetailDto", "stok"})
	public void addDetail(){
		setStatusPopup(Boolean.TRUE);
		trOrderDetailDto = new TrOrderDetailDto();
		trOrderDetailDto.setDiscount(0);
		setStok(0L);
	}
	
	@Command(value="saveDetail")
	@NotifyChange(value={"listOrderDetails", "statusPopup", "trOrderHeaderDto", "tampungHargaTotal"})
	public void saveDetail(){
		listOrderDetails.add(trOrderDetailDto);
		
		BigDecimal hasil = new BigDecimal(0);
		for(TrOrderDetailDto detail : listOrderDetails){
			hasil = hasil.add(detail.getSubtotal());
		}
		tampungHargaTotal = hasil;
		trOrderHeaderDto.setTotalPrice(hasil);
		setStatusPopup(Boolean.FALSE);
		
	}
	
	@Command(value="backDetail")
	@NotifyChange(value={"statusPopup"})
	public void backDetail(){
		setStatusPopup(Boolean.FALSE);
	}
	
	@Command(value="removeDetail")
	@NotifyChange(value={"listOrderDetails"})
	public void removeDetail(){
		listOrderDetails.remove(trOrderDetailDto);
	}
	
	@Command(value="hitungSubTotal")
	@NotifyChange(value={"trOrderDetailDto", "tampungSubTotal"})
	public void hitungSubTotal(){
		if(trOrderDetailDto.getUnitPrice().doubleValue() != 0 && trOrderDetailDto.getQty().intValue() != 0 && trOrderDetailDto.getDiscount().intValue() == 0){
			Double tampungSub = trOrderDetailDto.getUnitPrice().multiply(new BigDecimal(trOrderDetailDto.getQty().intValue())).doubleValue();
			tampungSubTotal = new BigDecimal(tampungSub);
//			tampungSubTotal = trOrderDetailDto.getUnitPrice().multiply(new BigDecimal(trOrderDetailDto.getQty().intValue()));
//			tampungSubTotal = trOrderDetailDto.getUnitPrice().multiply(new BigDecimal(trOrderDetailDto.getQty()));
			System.out.println("SubTotal without discount");
			trOrderDetailDto.setSubtotal(tampungSubTotal);
		}
		else if(trOrderDetailDto.getUnitPrice().doubleValue() != 0 && trOrderDetailDto.getQty().intValue() != 0 && trOrderDetailDto.getDiscount().intValue() != 0){
			System.out.println("Subtotal with discount.");
			Double tampungSub = trOrderDetailDto.getUnitPrice().multiply(new BigDecimal(trOrderDetailDto.getQty().intValue())).doubleValue();
//			tampungSubTotal = trOrderDetailDto.getUnitPrice().multiply(new BigDecimal(trOrderDetailDto.getQty().intValue()));
//			tampungSubTotal = trOrderDetailDto.getUnitPrice().multiply(new BigDecimal(trOrderDetailDto.getQty()));
			System.out.println("Unit Price: " + trOrderDetailDto.getUnitPrice());
			System.out.println("Qty: " + trOrderDetailDto.getQty());
			Double discount = Double.valueOf(trOrderDetailDto.getDiscount().intValue() / 100.0);
			System.out.println("Discount Perc Sub: " + discount);
			Double tampungDisc = trOrderDetailDto.getUnitPrice().multiply(new BigDecimal(trOrderDetailDto.getQty().intValue())).multiply(new BigDecimal((trOrderDetailDto.getDiscount().intValue() / 100.0))).doubleValue();
			System.out.println("Discount subtotal: " + tampungDisc);
//			BigDecimal tampungDisc = trOrderDetailDto.getUnitPrice().multiply(new BigDecimal(trOrderDetailDto.getQty())).multiply(new BigDecimal(trOrderDetailDto.getDiscount()).divide(new BigDecimal(100)));
			tampungSub = tampungSub - tampungDisc;
			tampungSubTotal = new BigDecimal(tampungSub);//tampungSubTotal.subtract(tampungDisc);
			trOrderDetailDto.setSubtotal(tampungSubTotal);
		}
	}
	
	@Command(value="hitungHargaTotal")
	@NotifyChange(value={"trOrderHeaderDto", "tampungHargaTotal"})
	public void hitungHargaTotal(){
		Double tampungSub = 0.0;
		if(!trOrderHeaderDto.getGlobalDiscount().equals(0)){
//			BigDecimal tampungDisc = trOrderHeaderDto.getTotalPrice().multiply(new BigDecimal(trOrderHeaderDto.getGlobalDiscount()).divide(new BigDecimal(100)));
			Double discount = (double) (trOrderHeaderDto.getGlobalDiscount() / 100.0);
			System.out.println("Discount Percentage: " + discount);
			Double tampungDisc = trOrderHeaderDto.getTotalPrice().multiply(new BigDecimal(discount)).doubleValue();
			System.out.println("Discount: " + tampungDisc);
			tampungSub = trOrderHeaderDto.getTotalPrice().doubleValue() - tampungDisc;
			tampungHargaTotal = new BigDecimal(tampungSub);//trOrderHeaderDto.getTotalPrice().subtract(tampungDisc);
			trOrderHeaderDto.setTotalPrice(tampungHargaTotal);
		}
		else{
			BigDecimal hasil = new BigDecimal(0);
			for(TrOrderDetailDto detail : listOrderDetails){
				hasil = hasil.add(detail.getSubtotal());
			}
			tampungHargaTotal = hasil;
			trOrderHeaderDto.setTotalPrice(tampungHargaTotal);
		}
	}
	
	@Command(value="tampilStok")
	@NotifyChange(value={"stok", "trOrderDetailDto"})
	public void tampilStock(){
		if(trOrderDetailDto.getProduct() != null && trOrderDetailDto.getProduct().getProductCode() != null && !trOrderDetailDto.getProduct().getProductCode().equalsIgnoreCase("")){
			mstProductDto = new MstProductDto();
			mstProductDto = mstProductSvc.findByProductCode(trOrderDetailDto.getProduct().getProductCode());
			stok = mstProductDto.getQuantity();
			trOrderDetailDto.setUnitPrice(mstProductDto.getUnitPrice());
		}
	}

	
	/*-----SETTER GETTER-----*/
	public TrOrderHeaderDto getTrOrderHeaderDto() {
		return trOrderHeaderDto;
	}

	public void setTrOrderHeaderDto(TrOrderHeaderDto trOrderHeaderDto) {
		this.trOrderHeaderDto = trOrderHeaderDto;
	}

	public TrOrderDetailDto getTrOrderDetailDto() {
		return trOrderDetailDto;
	}

	public void setTrOrderDetailDto(TrOrderDetailDto trOrderDetailDto) {
		this.trOrderDetailDto = trOrderDetailDto;
	}

	public MstProductDto getMstProductDto() {
		return mstProductDto;
	}

	public void setMstProductDto(MstProductDto mstProductDto) {
		this.mstProductDto = mstProductDto;
	}

	public MstCustomerDto getMstCustomerDto() {
		return mstCustomerDto;
	}

	public void setMstCustomerDto(MstCustomerDto mstCustomerDto) {
		this.mstCustomerDto = mstCustomerDto;
	}

	public MstEmployeeDto getMstEmployeeDto() {
		return mstEmployeeDto;
	}

	public void setMstEmployeeDto(MstEmployeeDto mstEmployeeDto) {
		this.mstEmployeeDto = mstEmployeeDto;
	}

	public List<TrOrderDetailDto> getListOrderDetails() {
		return listOrderDetails;
	}

	public void setListOrderDetails(List<TrOrderDetailDto> listOrderDetails) {
		this.listOrderDetails = listOrderDetails;
	}

	public List<MstProductDto> getListProducts() {
		return listProducts;
	}

	public void setListProducts(List<MstProductDto> listProducts) {
		this.listProducts = listProducts;
	}

	public List<MstCustomerDto> getListCustomers() {
		return listCustomers;
	}

	public void setListCustomers(List<MstCustomerDto> listCustomers) {
		this.listCustomers = listCustomers;
	}

	public List<MstEmployeeDto> getListEmployees() {
		return listEmployees;
	}

	public void setListEmployees(List<MstEmployeeDto> listEmployees) {
		this.listEmployees = listEmployees;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getStatusPopup() {
		return statusPopup;
	}

	public void setStatusPopup(Boolean statusPopup) {
		this.statusPopup = statusPopup;
	}

	public Boolean getReadonly() {
		return readonly;
	}

	public void setReadonly(Boolean readonly) {
		this.readonly = readonly;
	}

	public Long getStok() {
		return stok;
	}

	public void setStok(Long stok) {
		this.stok = stok;
	}

	public BigDecimal getTampungSubTotal() {
		return tampungSubTotal;
	}

	public void setTampungSubTotal(BigDecimal tampungSubTotal) {
		this.tampungSubTotal = tampungSubTotal;
	}

	public BigDecimal getTampung() {
		return tampung;
	}

	public void setTampung(BigDecimal tampung) {
		this.tampung = tampung;
	}

	public BigDecimal getTampungHargaTotal() {
		return tampungHargaTotal;
	}

	public void setTampungHargaTotal(BigDecimal tampungHargaTotal) {
		this.tampungHargaTotal = tampungHargaTotal;
	}

	public BigDecimal getTotalSebelumDiskon() {
		return totalSebelumDiskon;
	}

	public void setTotalSebelumDiskon(BigDecimal totalSebelumDiskon) {
		this.totalSebelumDiskon = totalSebelumDiskon;
	}

}
