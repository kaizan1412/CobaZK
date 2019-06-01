package test;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.MstDepartmentDao;
import dto.MstDepartmentDto;
import dto.MstEmployeeDto;
import entity.MstDepartment;
import entity.enumcol.GenderEnum;
import service.MstDepartmentService;
import service.MstEmployeeService;

public class TestKaryawan {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
		
		MstEmployeeService mstEmployeeSvc = context.getBean(MstEmployeeService.class);
		MstDepartmentDao mstDepartmentDao = context.getBean(MstDepartmentDao.class);
		
		MstDepartment dept = mstDepartmentDao.findByDeptName("DIVISI FINANCE");
		
		MstEmployeeDto dto = new MstEmployeeDto();
		dto.setCreatedDate(new Date());
		dto.setCreatedUser("ADMIN");
		dto.setDateOfBirth(new Date());
		dto.setDeleted(false);
		dto.setDepartment(dept);
		dto.setGender(GenderEnum.MALE);
		dto.setId(3);
		dto.setEmployeeName("Rizky");
//		mstKaryawanSvc.save(dto);
		
		dto = mstEmployeeSvc.findByUsername("Haqi");
		System.out.println(dto.getEmployeeName()  + " USERNAME: " + dto.getUsername());
	}

}
