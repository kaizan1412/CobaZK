package test;

import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.MstUserDao;
import entity.MstUser;

public class TestCallingSp {

	public static void main(String[] args) {
			
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
		
		MstUserDao dao = context.getBean(MstUserDao.class);
		
		Optional<List<MstUser>> opt = dao.findAllBySpParamThirdWay("Haqi", "Haqi");
		
		if(opt.isPresent()){
			for(MstUser user : opt.get()){
				System.out.println(user.toString());
			}
		}
		else{
			System.out.println("No data");
		}
		
	}

}
