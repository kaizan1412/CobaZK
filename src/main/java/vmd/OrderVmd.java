package vmd;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Include;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.Button;
import org.zkoss.zul.Messagebox.ClickEvent;

import dto.TrOrderDetailDto;
import dto.TrOrderHeaderDto;
import service.TrOrderDetailService;
import service.TrOrderHeaderService;

@VariableResolver(value=org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class OrderVmd {

	@WireVariable
	private TrOrderHeaderService trOrderHeaderSvc;

	@WireVariable
	private TrOrderDetailService trOrderDetailSvc;
	
	private TrOrderHeaderDto trOrderHeaderDto = new TrOrderHeaderDto();
	private List<TrOrderHeaderDto> listOrders = new ArrayList<TrOrderHeaderDto>();
	
	private TrOrderDetailDto trOrderDetailDto;
	
	private String search;
	
	@Init
	public void load(){
		listOrders = trOrderHeaderSvc.findAllDeletedFalse();
	}
	
	@Command(value="add")
	@NotifyChange(value={"trOrderHeaderDto"})
	public void add(){
		TrOrderHeaderDto trOrderHeaderDto = new TrOrderHeaderDto();
		Sessions.getCurrent().setAttribute("objOrderHeader", trOrderHeaderDto);
		Include inc = (Include) Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
		inc.setSrc("/transaksi/editorder.zul");
//		Executions.sendRedirect("/transaksi/editorder.zul");
	}
	
	@Command(value="edit")
	@NotifyChange(value={"includeSrc", "p"})
	public void edit(){
		if(trOrderHeaderDto != null && trOrderHeaderDto.getNoNota() != null && !trOrderHeaderDto.getNoNota().equalsIgnoreCase("")){
			Sessions.getCurrent().setAttribute("objOrderHeader", trOrderHeaderDto);
			Include inc = (Include) Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
			inc.setSrc("/transaksi/editorder.zul");
//			Executions.sendRedirect("/transaksi/editorder.zul");
		}
		else{
			Messagebox.show("Pilih data yang akan diedit.");
		}
	}
	
	@Command(value="delete")
	public void delete(){
		if(trOrderHeaderDto != null && !trOrderHeaderDto.getNoNota().equalsIgnoreCase("")){
			Messagebox.show("Apakah yakin mau dihapus?"
					, "Perhatian", new Button[]{Button.YES, Button.NO}
					,Messagebox.QUESTION, Button.NO, new EventListener<Messagebox.ClickEvent>() {
						
						@Override
						public void onEvent(ClickEvent event) throws Exception {
							try {
								trOrderHeaderSvc.delete(trOrderHeaderDto);
								trOrderHeaderSvc.deleteOrderDetail(trOrderHeaderDto.getNoNota());
								BindUtils.postNotifyChange(null, null, OrderVmd.this, "listOrders");
								Clients.showNotification("Data berhasil di hapus.", Clients.NOTIFICATION_TYPE_ERROR, null, null, 500);
							} catch (Exception e) {
								BindUtils.postNotifyChange(null, null, OrderVmd.this, "listOrders");
								Clients.showNotification("Data gagal di hapus.", Clients.NOTIFICATION_TYPE_ERROR, null, null, 500);
							}
						}
					});
		}
		else{
			Messagebox.show("Pilih data yang akan dihapus.");
		}
	}
	
	@Command(value="search")
	@NotifyChange("listOrders")
	public void search(){
		listOrders.clear();
//		listOrders = trOrderHeaderSvc.
	}
	
	/*-----SETTER GETTER-----*/
	public TrOrderHeaderDto getTrOrderHeaderDto() {
		return trOrderHeaderDto;
	}

	public void setTrOrderHeaderDto(TrOrderHeaderDto trOrderHeaderDto) {
		this.trOrderHeaderDto = trOrderHeaderDto;
	}

	public List<TrOrderHeaderDto> getListOrders() {
		return listOrders;
	}

	public void setListOrders(List<TrOrderHeaderDto> listOrders) {
		this.listOrders = listOrders;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public TrOrderDetailDto getTrOrderDetailDto() {
		return trOrderDetailDto;
	}

	public void setTrOrderDetailDto(TrOrderDetailDto trOrderDetailDto) {
		this.trOrderDetailDto = trOrderDetailDto;
	}
	
}
