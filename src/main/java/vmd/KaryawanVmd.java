package vmd;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Include;
import org.zkoss.zul.Messagebox;

import service.MstEmployeeService;
import dto.MstEmployeeDto;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class KaryawanVmd {
	
	private List<MstEmployeeDto> listKaryawan = new ArrayList<MstEmployeeDto>();
	private MstEmployeeDto mstEmployeeDto = new MstEmployeeDto();
	private boolean readonly = false;
	
	@WireVariable
	private MstEmployeeService mstEmployeeSvc;
	
	@Init
	public void load(){
		listKaryawan = mstEmployeeSvc.findAll();
	}
	
	@Command(value="add")
	public void add(){
		MstEmployeeDto mstEmployeeDto = new MstEmployeeDto();
		
		Sessions.getCurrent().setAttribute("objKaryawan", mstEmployeeDto);
		
		Include inc = (Include) Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
		
		inc.setSrc("/master/karyawan/karyawanedit.zul");
	}
	
	@Command(value="edit")
	public void edit(){
		
		if(mstEmployeeDto == null && mstEmployeeDto.getId() == null){
			Messagebox.show("Pilih data yang akan diedit");
		}
		else{
			Sessions.getCurrent().setAttribute("objKaryawan", mstEmployeeDto);
			Include inc = (Include) Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
			inc.setSrc("/master/karyawan/karyawanedit.zul");
		}
		
	}
	
	@Command("delete")
	public void delete(){
		if(mstEmployeeDto == null && mstEmployeeDto.getId() == null){
			Messagebox.show("Pilih data yang akan di delete");
		}
		else{
			mstEmployeeSvc.delete(mstEmployeeDto);
			listKaryawan.remove(mstEmployeeDto);
			BindUtils.postNotifyChange(null, null, KaryawanVmd.this, "listKaryawan");
			Clients.showNotification("Data Berhasil di Delete", Clients.NOTIFICATION_TYPE_INFO, null, null, 500);
		}
	}

	/* ----- Setter Getter ----- */
	public List<MstEmployeeDto> getListKaryawan() {
		return listKaryawan;
	}

	public void setListKaryawan(List<MstEmployeeDto> listKaryawan) {
		this.listKaryawan = listKaryawan;
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public MstEmployeeService getMstKaryawanSvc() {
		return mstEmployeeSvc;
	}

	public void setMstKaryawanSvc(MstEmployeeService mstKaryawanSvc) {
		this.mstEmployeeSvc = mstKaryawanSvc;
	}

	public MstEmployeeDto getMstEmployeeDto() {
		return mstEmployeeDto;
	}

	public void setMstEmployeeDto(MstEmployeeDto mstEmployeeDto) {
		this.mstEmployeeDto = mstEmployeeDto;
	}
	
}
