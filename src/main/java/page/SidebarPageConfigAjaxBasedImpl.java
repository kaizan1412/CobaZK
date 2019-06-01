package page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SidebarPageConfigAjaxBasedImpl implements SidebarPageConfig{

	HashMap<String, SidebarPage> pageMap = new LinkedHashMap<String, SidebarPage>();
	
	public SidebarPageConfigAjaxBasedImpl() {
		pageMap.put("fn1",
				new SidebarPage("Karyawan","/assets/imgs/fn.png","/master/karyawan/karyawan.zul"));
		
		pageMap.put("fn2", 
				new SidebarPage("Customer", "/assets/imgs/fn.png", "/master/customer/customer.zul"));

		pageMap.put("fn3", 
				new SidebarPage("Transaksi","/assets/imgs/fn.png", "/transaksi/order.zul" ));
		
		pageMap.put("fn4", 
				new SidebarPage("Logout","/assets/imgs/fn.png", "/masuk.zul" ));
	}
	
	@Override
	public List<SidebarPage> getPages() {
		return new ArrayList<SidebarPage>(pageMap.values());
	}
	
}
