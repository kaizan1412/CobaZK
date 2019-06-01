package service;

import java.util.List;

import dto.TrOrderDetailDto;


public interface TrOrderDetailService {

	public List<TrOrderDetailDto> findAllDeletedFalse();
	public void save(TrOrderDetailDto trOrderDetailDto);
	public void update(TrOrderDetailDto trOrderDetailDto);
	public void delete(TrOrderDetailDto trOrderDetailDto);
	public TrOrderDetailDto findOneById(String detailId);
	public List<TrOrderDetailDto> findByNoNota(String noNota);
	
}
