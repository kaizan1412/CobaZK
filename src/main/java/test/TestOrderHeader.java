package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dto.MstProductDto;
import dto.TrOrderHeaderDto;
import service.MstProductService;
import service.TrOrderDetailService;
import service.TrOrderHeaderService;

public class TestOrderHeader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
		
		TrOrderHeaderService trOrderHeaderSvc = context.getBean(TrOrderHeaderService.class);
		
		MstProductService mstProductSvc = context.getBean(MstProductService.class);
		
		for(MstProductDto dto : mstProductSvc.findAllDeletedFalse()){
			System.out.println("ID: " + dto.getProductName());
		}
		
		
	}

}
