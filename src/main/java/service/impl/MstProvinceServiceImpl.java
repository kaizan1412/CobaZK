package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MstProvinceDao;
import dto.MstProductDto;
import dto.MstProvinceDto;
import entity.MstProvince;
import entity.pk.MstProvincePk;
import service.MstProvinceService;

@Service(value = "mstProvinceSvc")
@Transactional
public class MstProvinceServiceImpl implements MstProvinceService {

	@Autowired
	private MstProvinceDao mstProvinceDao;

	@Override
	public List<MstProvinceDto> findAllDeletedFalse() {
		List<MstProvinceDto> list = null;
		try {
			List<MstProvince> res = mstProvinceDao.findAllDeleteFalse();
			if (res != null && !res.isEmpty() && res.size() > 0) {
				list = new ArrayList<MstProvinceDto>();

				for (MstProvince prov : res) {
					MstProvinceDto dto = new MstProvinceDto();
					dto.setProvinceCode(prov.getProvinceCode());
					dto.setProvinceName(prov.getProvinceName());
					dto.setCreatedDate(prov.getCreatedDate());
					dto.setCreatedUser(prov.getCreatedUser());
					dto.setDeleted(prov.getDeleted());
					dto.setUpdatedDate(prov.getUpdatedDate());
					dto.setUpdatedUser(prov.getUpdatedUser());

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
	public void save(MstProvinceDto mstProvinceDto) {
		try {
			if (mstProvinceDto != null
					&& mstProvinceDto.getProvinceCode().equalsIgnoreCase("")) {
				MstProvince prov = new MstProvince();
				prov.setCreatedDate(mstProvinceDto.getCreatedDate());
				prov.setCreatedUser(mstProvinceDto.getCreatedUser());
				prov.setDeleted(mstProvinceDto.getDeleted());
				prov.setProvinceCode(mstProvinceDto.getProvinceCode());
				prov.setProvinceName(mstProvinceDto.getProvinceName());
				prov.setUpdatedDate(mstProvinceDto.getUpdatedDate());
				prov.setUpdatedUser(mstProvinceDto.getUpdatedUser());
				mstProvinceDao.save(prov);
			}
		} catch (Exception e) {
			e.printStackTrace();
//			throw new SQLException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public void update(MstProvinceDto mstProvinceDto) {
		try {
			if (mstProvinceDto != null
					&& mstProvinceDto.getProvinceCode().equalsIgnoreCase("")) {
				MstProvincePk pk = new MstProvincePk();
				pk.setProvinceCode(mstProvinceDto.getProvinceCode());

				MstProvince prov = mstProvinceDao.findOne(pk);
				prov.setCreatedDate(mstProvinceDto.getCreatedDate());
				prov.setCreatedUser(mstProvinceDto.getCreatedUser());
				prov.setDeleted(mstProvinceDto.getDeleted());
				prov.setProvinceCode(mstProvinceDto.getProvinceCode());
				prov.setProvinceName(mstProvinceDto.getProvinceName());
				prov.setUpdatedDate(new Date());
				prov.setUpdatedUser("ADMIN");
				mstProvinceDao.save(prov);
			}
		} catch (Exception e) {
			e.printStackTrace();
//			throw new SQLException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public void deleted(MstProvinceDto mstProvinceDto) {
		try {
			if (mstProvinceDto != null
					&& mstProvinceDto.getProvinceCode().equalsIgnoreCase("")) {
				MstProvincePk pk = new MstProvincePk();
				pk.setProvinceCode(mstProvinceDto.getProvinceCode());

				MstProvince prov = mstProvinceDao.findOne(pk);
				prov.setDeleted(Boolean.TRUE);
				prov.setUpdatedDate(mstProvinceDto.getUpdatedDate());
				prov.setUpdatedUser(mstProvinceDto.getUpdatedUser());
				mstProvinceDao.save(prov);
			}
		} catch (Exception e) {
			e.printStackTrace();
//			throw new SQLException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public MstProvinceDto findOneByProvCode(String provinceCode) {
		MstProvinceDto dto = null;
		try {
			if (!provinceCode.equalsIgnoreCase("")) {
				MstProvince prov = mstProvinceDao.findOne(new MstProvincePk(
						provinceCode));
				
				if (prov != null && !prov.getProvinceCode().equalsIgnoreCase("")) {
					
					dto = new MstProvinceDto();
					dto.setProvinceCode(prov.getProvinceCode());
					dto.setProvinceName(prov.getProvinceName());
					dto.setCreatedDate(prov.getCreatedDate());
					dto.setCreatedUser(prov.getCreatedUser());
					dto.setDeleted(prov.getDeleted());
					dto.setUpdatedDate(prov.getUpdatedDate());
					dto.setUpdatedUser(prov.getUpdatedUser());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

}
