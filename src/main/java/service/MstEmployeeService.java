package service;

import java.util.List;

import dto.MstEmployeeDto;

public interface MstEmployeeService {
	
	public void save(MstEmployeeDto mstEmployeeDto);
	public List<MstEmployeeDto> findAll();
	public void delete(MstEmployeeDto mstEmployeeDto);
	public MstEmployeeDto findByEmpId(Integer employeeId);
	public void update(MstEmployeeDto mstEmployeeDto);
	public MstEmployeeDto findByUsername(String username);

}
