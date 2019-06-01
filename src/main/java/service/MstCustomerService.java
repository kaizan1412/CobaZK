package service;

import java.util.List;

import dto.MstCustomerDto;

public interface MstCustomerService {
	
	public List<MstCustomerDto> findAllDeletedFalse();
	public void save(MstCustomerDto mstCustomerDto);
	public void update(MstCustomerDto mstCustomerDto);
	public void delete(MstCustomerDto mstCustomerDto);
	public MstCustomerDto findByCustomerCode(String customerCode);

}
