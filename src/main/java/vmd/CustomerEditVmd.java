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

import service.MstCityService;
import service.MstCustomerService;
import service.MstProvinceService;
import dao.MstCityDao;
import dto.MstCityDto;
import dto.MstCustomerDto;
import dto.MstProvinceDto;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CustomerEditVmd {
	
	@WireVariable
	private MstProvinceService mstProvinceSvc;

	@WireVariable
	private MstCityService mstCitySvc;
	
	@WireVariable
	private MstCustomerService mstCustomerSvc;
	
	@WireVariable
	private MstCityDao mstCityDao;
	
	private List<MstCityDto> listCities = new ArrayList<MstCityDto>();
	private List<MstProvinceDto> listProvince = new ArrayList<>();
	private MstCustomerDto mstCustmerDto = new MstCustomerDto();
	private MstProvinceDto mstProvinceDto;
	private MstCityDto mstCityDto;
	private String cityCode;
	private String genderChoice;

	@Init
	public void load(){
		mstCustmerDto = (MstCustomerDto)Sessions.getCurrent().getAttribute("objCustomer");
		if(mstCustmerDto != null && !mstCustmerDto.getCustomerCode().equalsIgnoreCase("")){
			mstCityDto = mstCitySvc.findByCityCode(mstCustmerDto.getCity().getCityCode());
			mstProvinceDto = mstProvinceSvc.findOneByProvCode(mstCustmerDto.getProvince().getProvinceCode());
		}
		else{
			mstCityDto = new MstCityDto();
			mstProvinceDto = new MstProvinceDto();
		}
		genderChoice = mstCustmerDto.getGender() != null ? mstCustmerDto.getGender().getCode() : "";
		listProvince = mstProvinceSvc.findAllDeletedFalse();
		listCities = mstCitySvc.findAllDeletedFalse();
	}
	
	@Command(value="save")
	@NotifyChange(value={"mstCustomerDto"})
	public void save(){
		MstCustomerDto mstCustomerFindOne = new MstCustomerDto();
		mstCustomerFindOne = mstCustomerSvc.findByCustomerCode(mstCustmerDto.getCustomerCode());
		
		if(mstCustomerFindOne != null && !mstCustomerFindOne.getCustomerCode().equalsIgnoreCase("")){
			try {
				mstCustmerDto.setCity(mstCityDto);
				mstCustomerSvc.update(mstCustmerDto);
				Clients.showNotification("Data berhasil diupdate", Clients.NOTIFICATION_TYPE_INFO, null, null, 1500);
				Executions.sendRedirect("/master/customer/customer.zul");
			} catch (Exception e) {
				Clients.showNotification("Data gagal diupdate" + e.getMessage(), Clients.NOTIFICATION_TYPE_ERROR, null, null, 1500);
				Sessions.getCurrent().setAttribute("objCustomer", mstCustmerDto);
				Executions.sendRedirect("/master/customer/customeredit.zul");
			}
		}
		else{
			try {
				mstCustmerDto.setCity(mstCityDto);
				mstCustomerSvc.save(mstCustmerDto);
				Clients.showNotification("Data berhasil disimpan", Clients.NOTIFICATION_TYPE_INFO, null, null, 1500);
				Executions.sendRedirect("/master/customer/customer.zul");
			} catch (Exception e) {
				Clients.showNotification("Data gagal disimpan" + e.getMessage(), Clients.NOTIFICATION_TYPE_ERROR, null, null, 1500);
				Sessions.getCurrent().setAttribute("objCustomer", mstCustmerDto);
				Executions.sendRedirect("/master/customer/customeredit.zul");
			}
		}
	}
	
	@Command(value="back")
	public void back(){
		Executions.sendRedirect("/master/customer/customer.zul");
	}
	
	@Command(value="findCity")
	@NotifyChange(value={"listCities", "mstCityDto"})
	public void findCity(){
		listCities = mstCitySvc.findByProvCode(mstProvinceDto.getProvinceCode());
	}
	
	/*-----SETTER GETTER-----*/
	public List<MstCityDto> getListCities() {
		return listCities;
	}
	public void setListCities(List<MstCityDto> listCities) {
		this.listCities = listCities;
	}
	public List<MstProvinceDto> getListProvince() {
		return listProvince;
	}
	public void setListProvince(List<MstProvinceDto> listProvince) {
		this.listProvince = listProvince;
	}
	public MstCustomerDto getMstCustmerDto() {
		return mstCustmerDto;
	}
	public void setMstCustmerDto(MstCustomerDto mstCustmerDto) {
		this.mstCustmerDto = mstCustmerDto;
	}
	public MstProvinceDto getMstProvinceDto() {
		return mstProvinceDto;
	}
	public void setMstProvinceDto(MstProvinceDto mstProvinceDto) {
		this.mstProvinceDto = mstProvinceDto;
	}
	public MstCityDto getMstCityDto() {
		return mstCityDto;
	}
	public void setMstCityDto(MstCityDto mstCityDto) {
		this.mstCityDto = mstCityDto;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getGenderChoice() {
		return genderChoice;
	}

	public void setGenderChoice(String genderChoice) {
		this.genderChoice = genderChoice;
	}
	
}
