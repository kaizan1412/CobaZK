package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MstCityDao;
import dto.MstCityDto;
import entity.MstCity;
import entity.MstProvince;
import entity.pk.MstCityPk;
import service.MstCityService;

@Service(value="mstCitySvc")
@Transactional
public class MstCityServiceImpl implements MstCityService{

	@Autowired
	private MstCityDao mstCityDao;
	
	@Override
	public List<MstCityDto> findAllDeletedFalse() {
		List<MstCityDto> list = null;
		
		try {
			List<MstCity> cities = mstCityDao.findAllDeletedFalse();
			if(cities != null && !cities.isEmpty() && cities.size() > 0){
				list = new ArrayList<MstCityDto>();
				
				for(MstCity city : cities){
					MstCityDto dto = new MstCityDto();
					dto.setCityCode(city.getCityCode());
					dto.setCityName(city.getCityName());
					dto.setCreatedDate(city.getCreatedDate());
					dto.setCreatedUser(city.getCreatedUser());
					dto.setDeleted(city.getDeleted());
					dto.setProvinceCode(city.getProvinceCode());
					dto.setUpdatedDate(city.getUpdatedDate());
					dto.setUpdatedUser(city.getUpdatedUser());
					
					list.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	@Override
	public void save(MstCityDto mstCityDto) {
		try{
			MstCity city = new MstCity();
			city.setCityCode(mstCityDto.getCityCode());
			city.setCityName(mstCityDto.getCityName());
			city.setCreatedDate(new Date());
			city.setCreatedUser("ADMIN");
			city.setDeleted(mstCityDto.getDeleted());
			city.setProvinceCode(mstCityDto.getProvinceCode());
			mstCityDao.save(city);
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void update(MstCityDto mstCityDto) {
		try {
			MstCity city = mstCityDao.findOne(new MstCityPk(mstCityDto.getCityCode()));
			city.setCityCode(mstCityDto.getCityCode());
			city.setCityName(mstCityDto.getCityName());
			city.setDeleted(mstCityDto.getDeleted());
			city.setProvinceCode(mstCityDto.getProvinceCode());
			city.setUpdatedDate(new Date());
			city.setUpdatedUser("ADMIN");
			
			mstCityDao.save(city);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(MstCityDto mstCityDto) {
		try {
			MstCity city = mstCityDao.findOne(new MstCityPk(mstCityDto.getCityCode()));
			city.setDeleted(Boolean.TRUE);
			city.setUpdatedDate(new Date());
			city.setUpdatedUser("ADMIN");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MstCityDto> findByProvCode(String provinceCode) {
		
		try {
			List<MstCity> cities = mstCityDao.findByProvCode(provinceCode);
			
			if(cities != null && !cities.isEmpty() && cities.size() > 0){
				List<MstCityDto> list = new ArrayList<>();
				for(MstCity city : cities){
					MstCityDto dto = new MstCityDto();
					dto.setCityCode(city.getCityCode());
					dto.setCityName(city.getCityName());
					dto.setCreatedDate(city.getCreatedDate());
					dto.setCreatedUser(city.getCreatedUser());
					dto.setDeleted(city.getDeleted());
					dto.setProvinceCode(city.getProvinceCode());
					dto.setUpdatedDate(city.getUpdatedDate());
					dto.setUpdatedUser(city.getUpdatedUser());
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
	public MstCityDto findByCityCode(String cityCode) {
		try {
			MstCity city = mstCityDao.findOne(new MstCityPk(cityCode));
			
			MstCityDto dto = new MstCityDto();
			dto.setCityCode(city.getCityCode());
			dto.setCityName(city.getCityName());
			dto.setCreatedDate(city.getCreatedDate());
			dto.setCreatedUser(city.getCreatedUser());
			dto.setDeleted(city.getDeleted());
			dto.setProvinceCode(city.getProvinceCode());
			dto.setUpdatedDate(city.getUpdatedDate());
			dto.setUpdatedUser(city.getUpdatedUser());
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
