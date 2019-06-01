package vmd;

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
import org.zkoss.zul.Window;

import service.MstCityService;
import service.MstDepartmentService;
import service.MstEmployeeService;
import service.MstProvinceService;
import dao.MstDepartmentDao;
import dto.MstCityDto;
import dto.MstDepartmentDto;
import dto.MstEmployeeDto;
import dto.MstProductDto;
import dto.MstProvinceDto;
import entity.MstDepartment;
import entity.enumcol.GenderEnum;
import entity.pk.MstDepartmentPk;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class KaryawanEditVmd {
	
	@WireVariable
	private MstEmployeeService mstEmployeeSvc;
	
	@WireVariable
	private MstDepartmentService mstDepartmentSvc;
	
	@WireVariable
	private MstDepartmentDao mstDepartmentDao;
	
	@WireVariable
	private MstCityService mstCitySvc;
	
	@WireVariable
	private MstProvinceService mstProvinceSvc;
	
	private MstDepartment mstDepartment;
	
	private List<MstCityDto> listCity = new ArrayList<MstCityDto>();
	private List<MstProvinceDto> listProvince = new ArrayList<MstProvinceDto>();
	private MstCityDto mstCityDto;
	private MstProvinceDto mstProvinceDto;
	
	private MstEmployeeDto mstEmployeeDto = new MstEmployeeDto();
	private GenderEnum gender;
	private List<MstDepartmentDto> listDepartment = new ArrayList<MstDepartmentDto>();
	private MstDepartmentDto mstDepartmentDto;
	
	private String genderChoice;
	
	private Messagebox msgBox = new Messagebox();
	private String strMsg = "";

	@Init
	@NotifyChange(value={"genderChoice", "listProvince", "mstProvinceDto","mstCityDto"})
	public void load(){
		mstEmployeeDto = (MstEmployeeDto) Sessions.getCurrent().getAttribute("objKaryawan");
		
		if(mstEmployeeDto != null && mstEmployeeDto.getId() !=  null){
			mstCityDto = mstCitySvc.findByCityCode(mstEmployeeDto.getCityCode());
			mstProvinceDto = mstProvinceSvc.findOneByProvCode(mstEmployeeDto.getProvinceCode());
		}
		else{
			mstEmployeeDto = new MstEmployeeDto();
			mstCityDto = new MstCityDto();
			mstProvinceDto = new MstProvinceDto();
		}
		genderChoice = mstEmployeeDto.getGender() != null ? mstEmployeeDto.getGender().getCode() : "";
		getAllDepartment();
		getAllProvince();
	}
	
	@Command(value="save")
	public void save(){
		MstEmployeeDto mstKaryawanFindOne = new MstEmployeeDto();
		mstKaryawanFindOne = mstEmployeeSvc.findByEmpId(mstEmployeeDto.getId());
		
		if(mstKaryawanFindOne != null && mstKaryawanFindOne.getId() != null){
			MstDepartmentPk deptPk = new MstDepartmentPk();
			deptPk.setId(mstDepartmentDto.getId());
			mstDepartment = mstDepartmentDao.findOne(deptPk);
			
			mstEmployeeDto.setDepartment(mstDepartment);
			gender = genderChoice.equalsIgnoreCase("M")? GenderEnum.MALE : GenderEnum.FEMALE;
			mstEmployeeDto.setGender(gender);
			mstEmployeeSvc.update(mstEmployeeDto);
			Clients.showNotification("Data berhasil diupdate", Clients.NOTIFICATION_TYPE_INFO, null, null, 1500);
			Include inc = (Include) Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
			inc.setSrc("/master/karyawan/karyawan.zul");
		}
		else{
			mstEmployeeSvc.save(mstEmployeeDto);
			Clients.showNotification("Data berhasil disimpan", Clients.NOTIFICATION_TYPE_INFO, null, null, 1500);
			Include inc = (Include) Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
			inc.setSrc("/master/karyawan/karyawan.zul");
		}
	}
	
	@Command(value="back")
	public void back(){
		Include inc = (Include) Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
		inc.setSrc("/master/karyawan/karyawan.zul");
	}
	
	@Command(value="findCity")
	@NotifyChange(value={"listCity","mstCityDto"})
	public void findCity(){
		listCity = mstCitySvc.findByProvCode(mstProvinceDto.getProvinceCode());
	}
	
	private void getAllDepartment(){
		if(mstDepartmentSvc.findAllNotDeleted() != null 
				&& !mstDepartmentSvc.findAllNotDeleted().isEmpty()
				&& mstDepartmentSvc.findAllNotDeleted().size() > 0){
			listDepartment = mstDepartmentSvc.findAllNotDeleted();
		}
		else{
			Messagebox.show("Tidak ada daftar Department yang bisa ditampilkan");
		}
	}
	
	private void getAllProvince(){
		if(mstProvinceSvc.findAllDeletedFalse().size() > 0  
				&& mstProvinceSvc.findAllDeletedFalse() != null 
				&& mstProvinceSvc.findAllDeletedFalse().size() > 0){
			listProvince = mstProvinceSvc.findAllDeletedFalse();
		}
		else{
			Messagebox.show("Tidak ada data Provinsi yang bisa ditampilkan.");
		}
	}
	
	/* ----- Setter Getter ----- */
	
	public MstEmployeeDto getMstKaryawanDto() {
		return mstEmployeeDto;
	}

	public void setMstKaryawanDto(MstEmployeeDto mstKaryawanDto) {
		this.mstEmployeeDto = mstKaryawanDto;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public List<MstDepartmentDto> getListDepartment() {
		return listDepartment;
	}

	public void setListDepartment(List<MstDepartmentDto> listDepartment) {
		this.listDepartment = listDepartment;
	}

	public MstDepartmentDto getMstDepartmentDto() {
		return mstDepartmentDto;
	}

	public void setMstDepartmentDto(MstDepartmentDto mstDepartmentDto) {
		this.mstDepartmentDto = mstDepartmentDto;
	}

	public MstDepartment getMstDepartment() {
		return mstDepartment;
	}

	public void setMstDepartment(MstDepartment mstDepartment) {
		this.mstDepartment = mstDepartment;
	}

	public String getGenderChoice() {
		return genderChoice;
	}

	public void setGenderChoice(String genderChoice) {
		this.genderChoice = genderChoice;
	}

	public List<MstCityDto> getListCity() {
		return listCity;
	}

	public void setListCity(List<MstCityDto> listCity) {
		this.listCity = listCity;
	}

	public List<MstProvinceDto> getListProvince() {
		return listProvince;
	}

	public void setListProvince(List<MstProvinceDto> listProvince) {
		this.listProvince = listProvince;
	}

	public MstCityDto getMstCityDto() {
		return mstCityDto;
	}

	public void setMstCityDto(MstCityDto mstCityDto) {
		this.mstCityDto = mstCityDto;
	}

	public MstProvinceDto getMstProvinceDto() {
		return mstProvinceDto;
	}

	public void setMstProvinceDto(MstProvinceDto mstProvinceDto) {
		this.mstProvinceDto = mstProvinceDto;
	}
	
}
