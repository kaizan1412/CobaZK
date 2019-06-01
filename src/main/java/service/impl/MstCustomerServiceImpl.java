package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MstCityDao;
import dao.MstCustomerDao;
import dto.MstCityDto;
import dto.MstCustomerDto;
import dto.MstProvinceDto;
import entity.MstCity;
import entity.MstCustomer;
import entity.pk.MstCityPk;
import entity.pk.MstCustomerPk;
import service.MstCityService;
import service.MstCustomerService;
import service.MstProvinceService;

@Service(value="mstCustomerSvc")
@Transactional
public class MstCustomerServiceImpl implements MstCustomerService{

	@Autowired
	private MstCustomerDao mstCustomerDao;
	@Autowired
	private MstCityService mstCitySvc;
	@Autowired
	private MstCityDao mstCityDao;
	@Autowired
	private MstProvinceService mstProvinceSvc;
	
	@Override
	public List<MstCustomerDto> findAllDeletedFalse() {
		try {
			List<MstCustomer> customers = mstCustomerDao.findAllDeletedFalse();
			
			if(customers != null && !customers.isEmpty() && customers.size()>0){
				List<MstCustomerDto> list = new ArrayList<MstCustomerDto>();
				for(MstCustomer customer : customers){
					MstCustomerDto dto = new MstCustomerDto();
					dto.setAddress(customer.getAddress());
//					dto.setCityCode(customer.getCityCode());
					MstCityDto cityDto = mstCitySvc.findByCityCode(customer.getCityCode());
					dto.setCity(cityDto);
					dto.setCreatedDate(customer.getCreatedDate());
					dto.setCreatedUser(customer.getCreatedUser());
					dto.setCustomerCode(customer.getCustomerCode());
					dto.setCustomerName(customer.getCustomerName());
					dto.setDateOfBirth(customer.getDateOfBirth());
					dto.setDeleted(customer.getDeleted());
					dto.setGender(customer.getGender());
					dto.setPostalCode(customer.getPostalCode());
//					dto.setProvinceCode(customer.getProvinceCode());
					MstProvinceDto provDto = mstProvinceSvc.findOneByProvCode(customer.getProvinceCode());
					dto.setProvince(provDto);
					dto.setUpdatedDate(customer.getUpdatedDate());
					dto.setUpdatedUser(customer.getUpdatedUser());

					list.add(dto);
				}
				return list;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void save(MstCustomerDto mstCustomerDto) {
		try {
			MstCustomer customer = new MstCustomer();
			customer.setAddress(mstCustomerDto.getAddress());
//			customer.setCityCode(mstCustomerDto.getCityCode());
//			MstCity city = mstCityDao.findOne(new MstCityPk(mstCustomerDto.getCity().getCityCode()));
			customer.setCityCode(mstCustomerDto.getCity().getCityCode());
			customer.setCreatedDate(new Date());
			customer.setCreatedUser("ADMIN");
			customer.setCustomerCode(mstCustomerDto.getCustomerCode());
			customer.setCustomerName(mstCustomerDto.getCustomerName());
			customer.setDateOfBirth(mstCustomerDto.getDateOfBirth());
			customer.setDeleted(mstCustomerDto.getDeleted());
			customer.setGender(mstCustomerDto.getGender());
			customer.setPostalCode(mstCustomerDto.getPostalCode());
			customer.setProvinceCode(mstCustomerDto.getProvince().getProvinceCode());
			
			mstCustomerDao.save(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(MstCustomerDto mstCustomerDto) {
		try {
			MstCustomer customer = mstCustomerDao.findOne(new MstCustomerPk(mstCustomerDto.getCustomerCode()));
			
			if(customer != null && !customer.getCustomerCode().equalsIgnoreCase("")){
				customer.setAddress(mstCustomerDto.getAddress());
//				customer.setCityCode(mstCustomerDto.getCityCode());
//				MstCity city = mstCityDao.findOne(new MstCityPk(mstCustomerDto.getCity().getCityCode()));
				customer.setCityCode(mstCustomerDto.getCity().getCityCode());
				customer.setUpdatedDate(new Date());
				customer.setUpdatedUser("ADMIN");
				customer.setCustomerCode(mstCustomerDto.getCustomerCode());
				customer.setCustomerName(mstCustomerDto.getCustomerName());
				customer.setDateOfBirth(mstCustomerDto.getDateOfBirth());
				customer.setDeleted(mstCustomerDto.getDeleted());
				customer.setGender(mstCustomerDto.getGender());
				customer.setPostalCode(mstCustomerDto.getPostalCode());
				customer.setProvinceCode(mstCustomerDto.getProvince().getProvinceCode());
				
				mstCustomerDao.save(customer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(MstCustomerDto mstCustomerDto) {
		try {
			MstCustomer customer = mstCustomerDao.findOne(new MstCustomerPk(mstCustomerDto.getCustomerCode()));
			
			if(customer != null && !customer.getCustomerCode().equalsIgnoreCase("")){
				customer.setUpdatedDate(new Date());
				customer.setUpdatedUser("ADMIN");
				customer.setDeleted(Boolean.TRUE);
				mstCustomerDao.save(customer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public MstCustomerDto findByCustomerCode(String customerCode) {
		try {
					MstCustomer customer = mstCustomerDao.findOne(new MstCustomerPk(customerCode));
			if(customer != null && !customer.getCustomerCode().equalsIgnoreCase("")){
					MstCustomerDto dto = new MstCustomerDto();
					dto.setAddress(customer.getAddress());
//					dto.setCityCode(customer.getCityCode());
					MstCityDto cityDto = mstCitySvc.findByCityCode(customer.getCityCode());
					dto.setCity(cityDto);
					dto.setCreatedDate(customer.getCreatedDate());
					dto.setCreatedUser(customer.getCreatedUser());
					dto.setCustomerCode(customer.getCustomerCode());
					dto.setCustomerName(customer.getCustomerName());
					dto.setDateOfBirth(customer.getDateOfBirth());
					dto.setDeleted(customer.getDeleted());
					dto.setGender(customer.getGender());
					dto.setPostalCode(customer.getPostalCode());
					MstProvinceDto provDto = mstProvinceSvc.findOneByProvCode(customer.getProvinceCode());
					dto.setProvince(provDto);
					dto.setUpdatedDate(customer.getUpdatedDate());
					dto.setUpdatedUser(customer.getUpdatedUser());

					return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
