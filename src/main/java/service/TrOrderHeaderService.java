package service;

import java.util.List;

import dto.TrOrderHeaderDto;

public interface TrOrderHeaderService {
	
	public List<TrOrderHeaderDto> findAllDeletedFalse();
	public void save(TrOrderHeaderDto trOrderHeaderDto);
	public void update(TrOrderHeaderDto trOrderHeaderDto);
	public void delete(TrOrderHeaderDto trOrderHeaderDto);
	public void deleteOrderDetail(String noNota);
	public TrOrderHeaderDto findByNoNota(String noNota);

}
