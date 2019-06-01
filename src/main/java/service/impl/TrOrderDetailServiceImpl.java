package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MstProductDao;
import dao.MstProvinceDao;
import dao.TrOrderDetailDao;
import dto.MstProductDto;
import dto.TrOrderDetailDto;
import entity.TrOrderDetail;
import entity.pk.TrOrderDetailPk;
import service.MstProductService;
import service.TrOrderDetailService;
import service.TrOrderHeaderService;

@Service(value="trOrderDetailSvc")
@Transactional
public class TrOrderDetailServiceImpl implements TrOrderDetailService{
	
	@Autowired
	private TrOrderDetailDao trOrderDetailDao;
	
	@Autowired
	private MstProductDao mstProductDao;
	
	@Autowired
	private MstProductService mstProductSvc;

	@Override
	public List<TrOrderDetailDto> findAllDeletedFalse() {
		List<TrOrderDetailDto> list = new ArrayList<TrOrderDetailDto>();

		try {
			List<TrOrderDetail> orders = trOrderDetailDao.findAllDeletedFalse();
			if(orders != null && !orders.isEmpty() && orders.size() > 0){
				for(TrOrderDetail order : orders){
					TrOrderDetailDto dto = new TrOrderDetailDto();
					dto.setCreatedDate(order.getCreatedDate());
					dto.setCreatedUser(order.getCreatedUser());
					dto.setDeleted(order.getDeleted());
					dto.setDetailId(order.getDetailId());
					dto.setDiscount(order.getDiscount());
					dto.setNoNota(order.getNoNota());
					
					MstProductDto prodDto = mstProductSvc.findByProductCode(order.getProductCode());
					dto.setProduct(prodDto);
					dto.setProductCode(order.getProductCode());
					dto.setQty(order.getQty());
					dto.setSubtotal(order.getSubtotal());
					dto.setUnitPrice(order.getUnitPrice());
					dto.setUpdatedDate(order.getUpdatedDate());
					dto.setUpdatedUser(order.getUpdatedUser());
					list.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void save(TrOrderDetailDto trOrderDetailDto) {
		try {
			TrOrderDetail order = new TrOrderDetail();
			order.setCreatedDate(new Date());
			order.setCreatedUser("ADMIN");
			order.setDeleted(trOrderDetailDto.getDeleted());
			order.setDetailId(trOrderDetailDto.getDetailId());
			order.setDiscount(trOrderDetailDto.getDiscount());
			order.setNoNota(trOrderDetailDto.getNoNota());
			order.setProductCode(trOrderDetailDto.getProductCode());
			order.setQty(trOrderDetailDto.getQty());
			order.setSubtotal(trOrderDetailDto.getSubtotal());
			order.setUnitPrice(trOrderDetailDto.getUnitPrice());
			trOrderDetailDao.save(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(TrOrderDetailDto trOrderDetailDto) {
		try {
			TrOrderDetail order = trOrderDetailDao.findOne(new TrOrderDetailPk(trOrderDetailDto.getDetailId()));
			if(order != null && !order.getDetailId().equalsIgnoreCase("")){
				order.setDeleted(trOrderDetailDto.getDeleted());
				order.setDetailId(trOrderDetailDto.getDetailId());
				order.setDiscount(trOrderDetailDto.getDiscount());
				order.setNoNota(trOrderDetailDto.getNoNota());
				order.setProductCode(trOrderDetailDto.getProductCode());
				order.setQty(trOrderDetailDto.getQty());
				order.setSubtotal(trOrderDetailDto.getSubtotal());
				order.setUnitPrice(trOrderDetailDto.getUnitPrice());
				order.setUpdatedDate(new Date());
				order.setUpdatedUser("ADMIN");

				trOrderDetailDao.save(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(TrOrderDetailDto trOrderDetailDto) {
		try {
			TrOrderDetail order = trOrderDetailDao.findOne(new TrOrderDetailPk(trOrderDetailDto.getDetailId()));
			if(order != null && !order.getDetailId().equalsIgnoreCase("")){
				order.setDeleted(Boolean.TRUE);
				order.setUpdatedDate(new Date());
				order.setUpdatedUser("ADMIN");
	
				trOrderDetailDao.save(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public TrOrderDetailDto findOneById(String detailId) {
		try{
			TrOrderDetail order = trOrderDetailDao.findOne(new TrOrderDetailPk(detailId));
			if(order != null && !order.getDetailId().equalsIgnoreCase("")){
				TrOrderDetailDto dto = new TrOrderDetailDto();
				dto.setCreatedDate(order.getCreatedDate());
				dto.setCreatedUser(order.getCreatedUser());
				dto.setDeleted(order.getDeleted());
				dto.setDetailId(order.getDetailId());
				dto.setDiscount(order.getDiscount());
				dto.setNoNota(order.getNoNota());

				MstProductDto prodDto = mstProductSvc.findByProductCode(order.getProductCode());
				dto.setProduct(prodDto);
				dto.setProductCode(order.getProductCode());
				dto.setQty(order.getQty());
				dto.setSubtotal(order.getSubtotal());
				dto.setUnitPrice(order.getUnitPrice());
				dto.setUpdatedDate(order.getUpdatedDate());
				dto.setUpdatedUser(order.getUpdatedUser());
				
				return dto;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<TrOrderDetailDto> findByNoNota(String noNota) {
		try {
			List<TrOrderDetail> orders = trOrderDetailDao.findByNoNota(noNota);
			if(orders != null && !orders.isEmpty() && orders.size() > 0){
				List<TrOrderDetailDto> list = new ArrayList<>();
				
				for(TrOrderDetail order : orders){
					TrOrderDetailDto dto = new TrOrderDetailDto();
					dto.setCreatedDate(order.getCreatedDate());
					dto.setCreatedUser(order.getCreatedUser());
					dto.setDeleted(order.getDeleted());
					dto.setDetailId(order.getDetailId());
					dto.setDiscount(order.getDiscount());
					dto.setNoNota(order.getNoNota());
					
					MstProductDto prodDto = mstProductSvc.findByProductCode(order.getProductCode());
					dto.setProduct(prodDto);
					dto.setProductCode(order.getProductCode());
					dto.setQty(order.getQty());
					dto.setSubtotal(order.getSubtotal());
					dto.setUnitPrice(order.getUnitPrice());
					dto.setUpdatedDate(order.getUpdatedDate());
					dto.setUpdatedUser(order.getUpdatedUser());
					list.add(dto);
					return list;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
