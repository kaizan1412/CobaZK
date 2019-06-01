package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MstProductDao;
import dto.MstProductDto;
import entity.MstProduct;
import entity.pk.MstProductPk;
import service.MstProductService;
import service.MstProvinceService;

@Service(value="mstProductSvc")
@Transactional
public class MstProductServiceImpl implements MstProductService{
	
	@Autowired
	private MstProductDao mstProductDao;

	@Override
	public List<MstProductDto> findAllDeletedFalse() {
		try {
			List<MstProduct> prods = mstProductDao.findAllDeleted();
			if(prods != null && !prods.isEmpty() && prods.size() > 0){
				List<MstProductDto> list = new ArrayList<MstProductDto>();
				for(MstProduct prod : prods){
					MstProductDto dto = new MstProductDto();
					dto.setCategory(prod.getCategory());
					dto.setCreatedDate(prod.getCreatedDate());
					dto.setCreatedUser(prod.getCreatedUser());
					dto.setDeleted(prod.getDeleted());
					dto.setProductCode(prod.getProductCode());
					dto.setProductName(prod.getProductName());
					dto.setQuantity(prod.getQuantity());
					dto.setUnitPrice(prod.getUnitPrice());
					dto.setUpdatedDate(prod.getUpdatedDate());
					dto.setUpdatedUser(prod.getUpdatedUser());
					
					list.add(dto);
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public void save(MstProductDto mstProductDto) {
		try {
			MstProduct prod = new MstProduct();
			prod.setCategory(mstProductDto.getCategory());
			prod.setCreatedDate(new Date());
			prod.setCreatedUser("ADMIN");
			prod.setDeleted(mstProductDto.getDeleted());
			prod.setProductCode(mstProductDto.getProductCode());
			prod.setProductName(mstProductDto.getProductName());
			prod.setQuantity(mstProductDto.getQuantity());
			prod.setUnitPrice(mstProductDto.getUnitPrice());
			mstProductDao.save(prod);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(MstProductDto mstProductDto) {
		try {
			MstProduct prod = mstProductDao.findOne(new MstProductPk(mstProductDto.getProductCode()));
			if(prod != null && !prod.getProductCode().equalsIgnoreCase("")){
				prod.setCategory(mstProductDto.getCategory());
				prod.setDeleted(mstProductDto.getDeleted());
				prod.setProductCode(mstProductDto.getProductCode());
				prod.setProductName(mstProductDto.getProductName());
				prod.setQuantity(mstProductDto.getQuantity());
				prod.setUnitPrice(mstProductDto.getUnitPrice());
				prod.setUpdatedDate(new Date());
				prod.setUpdatedUser("ADMIN");
				mstProductDao.save(prod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(MstProductDto mstProductDto) {
		try {
			MstProduct prod = mstProductDao.findOne(new MstProductPk(mstProductDto.getProductCode()));
			if(prod != null && !prod.getProductCode().equalsIgnoreCase("")){
				prod.setDeleted(Boolean.TRUE);
				prod.setUpdatedDate(new Date());
				prod.setUpdatedUser("ADMIN");
				mstProductDao.save(prod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public MstProductDto findByProductCode(String productCode) {
		
		try {
			MstProduct prod = mstProductDao.findOne(new MstProductPk(productCode));
			if(prod != null && !prod.getProductCode().equalsIgnoreCase("")){
				MstProductDto dto = new MstProductDto();
				dto.setCategory(prod.getCategory());
				dto.setCreatedDate(prod.getCreatedDate());
				dto.setCreatedUser(prod.getCreatedUser());
				dto.setDeleted(prod.getDeleted());
				dto.setProductCode(prod.getProductCode());
				dto.setProductName(prod.getProductName());
				dto.setQuantity(prod.getQuantity());
				dto.setUnitPrice(prod.getUnitPrice());
				dto.setUpdatedDate(prod.getUpdatedDate());
				dto.setUpdatedUser(prod.getUpdatedUser());
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
