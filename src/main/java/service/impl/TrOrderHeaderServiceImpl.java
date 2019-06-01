package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.MstCustomerService;
import service.MstEmployeeService;
import service.TrOrderDetailService;
import service.TrOrderHeaderService;
import dao.MstCustomerDao;
import dao.TrOrderDetailDao;
import dao.TrOrderHeaderDao;
import dto.MstCustomerDto;
import dto.MstEmployeeDto;
import dto.TrOrderDetailDto;
import dto.TrOrderHeaderDto;
import entity.TrOrderHeader;
import entity.pk.TrOrderHeaderPk;

@Transactional
@Service(value="trOrderHeaderSvc")
public class TrOrderHeaderServiceImpl implements TrOrderHeaderService{
	
	@Autowired
	private TrOrderHeaderDao trOrderHeaderDao;
	@Autowired
	private MstCustomerService mstCustomerSvc;
	@Autowired
	private MstEmployeeService mstEmployeeSvc;
	@Autowired
	private TrOrderDetailService trOrderDetailSvc;
	

	@Override
	public List<TrOrderHeaderDto> findAllDeletedFalse() {
		List<TrOrderHeaderDto> list = new ArrayList<>();
		
		try {
			List<TrOrderHeader> headers = trOrderHeaderDao.findAllDeletedFalse();
			
			if(headers != null && !headers.isEmpty() && headers.size() > 0){
				for(TrOrderHeader header : headers){
					TrOrderHeaderDto dto = new TrOrderHeaderDto();
//					dto.setCustomerCode(header.getCustomerCode());
					MstCustomerDto custDto = mstCustomerSvc.findByCustomerCode(header.getCustomerCode());
					dto.setCustomer(custDto);
					dto.setCreatedDate(header.getCreatedDate());
					dto.setCreatedUser(header.getCreatedUser());
					dto.setDeleted(header.getDeleted());
//					dto.setEmployeeCode(header.getEmployeeCode());
					MstEmployeeDto empDto = mstEmployeeSvc.findByEmpId(header.getEmployeeCode());
					dto.setEmployee(empDto);
					dto.setGlobalDiscount(header.getGlobalDiscount());
					dto.setNoNota(header.getNoNota());
					dto.setTotalPrice(header.getTotalPrice());
					dto.setTransactionDate(header.getTransactionDate());
					dto.setUpdatedDate(header.getUpdatedDate());
					dto.setUpdatedUser(header.getUpdatedUser());
					list.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void save(TrOrderHeaderDto trOrderHeaderDto) {
		try {
			TrOrderHeader order = new TrOrderHeader();
			order.setCustomerCode(trOrderHeaderDto.getCustomer().getCustomerCode());
			order.setCreatedDate(new Date());
			order.setCreatedUser("ADMIN");
			order.setDeleted(Boolean.FALSE);
			order.setEmployeeCode( trOrderHeaderDto.getEmployee().getId());
			order.setGlobalDiscount(trOrderHeaderDto.getGlobalDiscount());
			order.setNoNota(trOrderHeaderDto.getNoNota());
			order.setTotalPrice(trOrderHeaderDto.getTotalPrice());
			order.setTransactionDate(trOrderHeaderDto.getTransactionDate());
			trOrderHeaderDao.save(order);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(TrOrderHeaderDto trOrderHeaderDto) {
		try {
			TrOrderHeader order = trOrderHeaderDao.findOne(new TrOrderHeaderPk(trOrderHeaderDto.getNoNota()));
			if(order != null && !order.getNoNota().equalsIgnoreCase("")){
				order.setCustomerCode(trOrderHeaderDto.getCustomer().getCustomerCode());
				order.setDeleted(Boolean.FALSE);
				order.setEmployeeCode( trOrderHeaderDto.getEmployee().getId());
				order.setGlobalDiscount(trOrderHeaderDto.getGlobalDiscount());
				order.setNoNota(trOrderHeaderDto.getNoNota());
				order.setTotalPrice(trOrderHeaderDto.getTotalPrice());
				order.setTransactionDate(trOrderHeaderDto.getTransactionDate());
				order.setUpdatedDate(new Date());
				order.setUpdatedUser("ADMIN");
				trOrderHeaderDao.save(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(TrOrderHeaderDto trOrderHeaderDto) {
		try {
			TrOrderHeader order = trOrderHeaderDao.findOne(new TrOrderHeaderPk(trOrderHeaderDto.getNoNota()));
			if(order != null && !order.getNoNota().equalsIgnoreCase("")){
				order.setDeleted(Boolean.TRUE);
				order.setUpdatedDate(new Date());
				order.setUpdatedUser("ADMIN");
				trOrderHeaderDao.save(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public TrOrderHeaderDto findByNoNota(String noNota) {
		try {
			TrOrderHeader order = trOrderHeaderDao.findOne(new TrOrderHeaderPk(noNota));
			
			if(order != null && !order.getNoNota().equalsIgnoreCase("")){
				TrOrderHeaderDto dto = new TrOrderHeaderDto();
//				dto.setCustomerCode(header.getCustomerCode());
				MstCustomerDto custDto = mstCustomerSvc.findByCustomerCode(order.getCustomerCode());
				dto.setCustomer(custDto);
				dto.setCreatedDate(order.getCreatedDate());
				dto.setCreatedUser(order.getCreatedUser());
				dto.setDeleted(order.getDeleted());
//				dto.setEmployeeCode(header.getEmployeeCode());
				MstEmployeeDto empDto = mstEmployeeSvc.findByEmpId(order.getEmployeeCode());
				dto.setEmployee(empDto);
				dto.setGlobalDiscount(order.getGlobalDiscount());
				dto.setNoNota(order.getNoNota());
				dto.setTotalPrice(order.getTotalPrice());
				dto.setTransactionDate(order.getTransactionDate());
				dto.setUpdatedDate(order.getUpdatedDate());
				dto.setUpdatedUser(order.getUpdatedUser());
				
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteOrderDetail(String noNota) {
		try {
			TrOrderHeader order = trOrderHeaderDao.findOne(new TrOrderHeaderPk(noNota));
			if(order != null && !order.getNoNota().equalsIgnoreCase("")){
				
				List<TrOrderDetailDto> listDetails = trOrderDetailSvc.findByNoNota(noNota);
				
				if(listDetails != null && !listDetails.isEmpty() && listDetails.size() > 0){
					for(TrOrderDetailDto detail : listDetails){
						trOrderDetailSvc.delete(detail);
					}
					order.setDeleted(Boolean.TRUE);
					order.setUpdatedDate(new Date());
					order.setUpdatedUser("ADMIN");
					trOrderHeaderDao.save(order);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
