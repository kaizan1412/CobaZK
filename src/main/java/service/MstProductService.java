package service;

import java.util.List;

import dto.MstProductDto;

public interface MstProductService {
	
	public List<MstProductDto> findAllDeletedFalse();
	public void save(MstProductDto mstProductDto);
	public void update(MstProductDto mstProductDto);
	public void delete(MstProductDto mstProductDto);
	public MstProductDto findByProductCode(String productCode);

}
