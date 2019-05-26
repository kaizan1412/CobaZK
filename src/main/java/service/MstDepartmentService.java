package service;

import java.util.List;

import dto.MstDepartmentDto;

public interface MstDepartmentService {
	
	public void save(MstDepartmentDto mstDepartmentDto);
	public List<MstDepartmentDto> findAll();
	public MstDepartmentDto getLastInserted();

}