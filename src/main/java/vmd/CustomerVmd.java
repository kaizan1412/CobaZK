package vmd;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.Button;
import org.zkoss.zul.Messagebox.ClickEvent;

import service.MstCustomerService;
import dto.MstCustomerDto;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CustomerVmd extends NavigationVmd{
	
	@WireVariable
	private MstCustomerService mstCustomerSvc;
	
	private List<MstCustomerDto> listCustomer = new ArrayList<MstCustomerDto>();
	private MstCustomerDto mstCustomerDto = new MstCustomerDto();
	private boolean readonly = false;
	
	
	@Init
	public void load(){
		if(mstCustomerSvc.findAllDeletedFalse() != null && !mstCustomerSvc.findAllDeletedFalse().isEmpty() && mstCustomerSvc.findAllDeletedFalse().size() > 0){
			listCustomer = mstCustomerSvc.findAllDeletedFalse();
		}
		else{
			Messagebox.show("Tidak ada data customer yang bisa ditampilkan.");
		}
	}
	
	@Command(value="add")
	public void add(){
		MstCustomerDto mstCustomerDto = new MstCustomerDto();
		Sessions.getCurrent().setAttribute("objCustomer", mstCustomerDto);
		Executions.sendRedirect("/master/customer/customeredit.zul");
	}
	
	@Command(value="edit")
	public void edit(){
		if(!mstCustomerDto.getCustomerCode().equalsIgnoreCase("")){
			Sessions.getCurrent().setAttribute("objCustomer", mstCustomerDto);
			Executions.sendRedirect("/master/customer/customeredit.zul");
		}
		else{
			Messagebox.show("Pilih data yang akan diedit");
		}
	}
	
	@Command(value="delete")
	public void delete(){
		if(mstCustomerDto.getCustomerCode().equalsIgnoreCase("")){
			Messagebox.show("Pilih data yang akan di delete.");
		}
		else{
			Messagebox.show("Apakah yakin mau di hapus?", "Perhatian", new Button[]{Button.YES, Button.NO},  Messagebox.QUESTION, Button.NO, new EventListener<Messagebox.ClickEvent>() {
				
				@Override
				public void onEvent(ClickEvent event) throws Exception {
					if(Messagebox.ON_YES.equals(event.getName())){
						mstCustomerSvc.delete(mstCustomerDto);
						listCustomer.remove(mstCustomerDto);
						BindUtils.postNotifyChange(null, null, CustomerVmd.this, "listCustomer");
						Clients.showNotification("Data berhasil di delete", Clients.NOTIFICATION_TYPE_INFO, null, null, 500);
					}
				}
			});
		}
	}
	
	/* -----SETTER GETTER----- */
	public MstCustomerService getMstCustomerSvc() {
		return mstCustomerSvc;
	}
	public void setMstCustomerSvc(MstCustomerService mstCustomerSvc) {
		this.mstCustomerSvc = mstCustomerSvc;
	}
	public List<MstCustomerDto> getListCustomer() {
		return listCustomer;
	}
	public void setListCustomer(List<MstCustomerDto> listCustomer) {
		this.listCustomer = listCustomer;
	}
	public MstCustomerDto getMstCustomerDto() {
		return mstCustomerDto;
	}
	public void setMstCustomerDto(MstCustomerDto mstCustomerDto) {
		this.mstCustomerDto = mstCustomerDto;
	}
	public boolean isReadonly() {
		return readonly;
	}
	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}
	
}
