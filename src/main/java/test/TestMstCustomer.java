package test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dto.MstCityDto;
import dto.MstCustomerDto;
import dto.MstProvinceDto;
import entity.enumcol.GenderEnum;
import service.MstCityService;
import service.MstCustomerService;
import service.MstProvinceService;

public class TestMstCustomer {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
		
		MstCustomerService mstCustomerSvc = context.getBean(MstCustomerService.class);
		MstCityService mstCitySvc = context.getBean(MstCityService.class);
		MstProvinceService mstProvinceSvc = context.getBean(MstProvinceService.class);
		
		MstCityDto cityDto = mstCitySvc.findByCityCode("CTY_007");
		MstProvinceDto provDto = mstProvinceSvc.findOneByProvCode("PROV_02");
		
		MstCustomerDto custDto = new MstCustomerDto();
		custDto.setAddress("Bandung");
		custDto.setCity(cityDto);
		custDto.setCustomerCode("CM_0002");
		custDto.setCustomerName("Andi");
		custDto.setDateOfBirth(new Date());
		custDto.setDeleted(false);
		custDto.setGender(GenderEnum.MALE);
		custDto.setPostalCode("13460");
		custDto.setProvince(provDto);
		
		mstCustomerSvc.save(custDto);
		
	}

}
