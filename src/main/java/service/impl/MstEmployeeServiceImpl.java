package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.bytecode.buildtime.spi.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MstDepartmentDao;
import dao.MstEmployeeDao;
import dto.MstEmployeeDto;
import entity.MstEmployee;
import entity.pk.MstEmployeePk;
import service.MstEmployeeService;

@Service(value="mstEmployeeSvc")
@Transactional
public class MstEmployeeServiceImpl implements MstEmployeeService{

	@Autowired
	private MstEmployeeDao mstEmployeeDao;
	
	@Autowired
	private MstDepartmentDao mstDepartmentDao;
	
	@Override
	public void save(MstEmployeeDto mstEmployeeDto) {
		try {
			MstEmployee employee = new MstEmployee();
			employee.setCreatedDate(mstEmployeeDto.getCreatedDate());
			employee.setCreatedUser(mstEmployeeDto.getCreatedUser());
			employee.setDateOfBirth(mstEmployeeDto.getDateOfBirth());
			employee.setDeleted(mstEmployeeDto.getDeleted());
			employee.setDepartment(mstEmployeeDto.getDepartment());
			employee.setEmployeeName(mstEmployeeDto.getEmployeeName());
			employee.setUpdatedDate(mstEmployeeDto.getUpdatedDate());
			employee.setUpdatedUser(mstEmployeeDto.getUpdatedUser());
			employee.setGender(mstEmployeeDto.getGender());
			employee.setId(mstEmployeeDto.getId());
			employee.setBirthPlace(mstEmployeeDto.getBirthPlace());
			employee.setAddress(mstEmployeeDto.getAddress());
			employee.setCityCode(mstEmployeeDto.getCityCode());
			employee.setPostalCode(mstEmployeeDto.getPostalCode());
			employee.setProvinceCode(mstEmployeeDto.getProvinceCode());
			employee.setUsername(mstEmployeeDto.getUsername());
			mstEmployeeDao.save(employee);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MstEmployeeDto> findAll() {
		List<MstEmployeeDto> list = null;
		try {
			List<MstEmployee> res = mstEmployeeDao.findAll();
			if(res != null && !res.isEmpty() && res.size() > 0){
				list = new ArrayList<MstEmployeeDto>();
				
				for(MstEmployee employee : res){
					MstEmployeeDto dto = new MstEmployeeDto();
					dto.setCreatedDate(employee.getCreatedDate());
					dto.setCreatedUser(employee.getCreatedUser());
					dto.setDateOfBirth(employee.getDateOfBirth());
					dto.setDeleted(employee.getDeleted());
					dto.setDepartment(employee.getDepartment());
					dto.setGender(employee.getGender());
					dto.setId(employee.getId());
					dto.setEmployeeName(employee.getEmployeeName());
					dto.setUpdatedDate(employee.getUpdatedDate());
					dto.setUpdatedUser(employee.getUpdatedUser());
					dto.setBirthPlace(employee.getBirthPlace());
					dto.setAddress(employee.getAddress());
					dto.setCityCode(employee.getCityCode());
					dto.setPostalCode(employee.getPostalCode());
					dto.setProvinceCode(employee.getProvinceCode());
					dto.setUsername(employee.getUsername());
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
	public void delete(MstEmployeeDto mstEmployeeDto) {
		try {
			MstEmployeePk karyPk = new MstEmployeePk();
			karyPk.setId(mstEmployeeDto.getId());
			
			MstEmployee employee = mstEmployeeDao.findOne(karyPk);
			if(employee != null && employee.getId() != null){
				employee.setDeleted(true);
				employee.setUpdatedDate(new Date());
				employee.setUpdatedUser("ADMIN");
				mstEmployeeDao.save(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public MstEmployeeDto findByEmpId(Integer employeeId) {
		try {
			MstEmployeePk karyPk = new MstEmployeePk();
			karyPk.setId(employeeId);
			MstEmployeeDto dto = null;
			MstEmployee employee = mstEmployeeDao.findOne(karyPk);
			if(employee != null && employee.getId() != null){
				dto = new MstEmployeeDto();
				dto.setCreatedDate(employee.getCreatedDate());
				dto.setCreatedUser(employee.getCreatedUser());
				dto.setDateOfBirth(employee.getDateOfBirth());
				dto.setDeleted(employee.getDeleted());
				dto.setDepartment(employee.getDepartment());
				dto.setGender(employee.getGender());
				dto.setId(employee.getId());
				dto.setEmployeeName(employee.getEmployeeName());
				dto.setUpdatedDate(employee.getUpdatedDate());
				dto.setUpdatedUser(employee.getUpdatedUser());
				dto.setBirthPlace(employee.getBirthPlace());
				dto.setUsername(employee.getUsername());
			}
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update(MstEmployeeDto mstEmployeeDto) {
		try {
			MstEmployeePk karyPk = new MstEmployeePk();
			karyPk.setId(mstEmployeeDto.getId());
			
			MstEmployee employee = mstEmployeeDao.findOne(karyPk);
			if(employee != null && employee.getId() != null){
				employee.setDeleted(mstEmployeeDto.getDeleted());
				employee.setUpdatedDate(new Date());
				employee.setUpdatedUser("ADMIN");
				employee.setDateOfBirth(mstEmployeeDto.getDateOfBirth());
				employee.setDepartment(mstEmployeeDto.getDepartment());
				employee.setGender(mstEmployeeDto.getGender());
				employee.setEmployeeName(mstEmployeeDto.getEmployeeName());
				employee.setBirthPlace(mstEmployeeDto.getBirthPlace());
				employee.setAddress(mstEmployeeDto.getAddress());
				employee.setCityCode(mstEmployeeDto.getCityCode());
				employee.setPostalCode(mstEmployeeDto.getPostalCode());
				employee.setProvinceCode(mstEmployeeDto.getProvinceCode());
				employee.setUsername(mstEmployeeDto.getUsername());

				mstEmployeeDao.save(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public MstEmployeeDto findByUsername(String username) {
		try {
			MstEmployee employee = mstEmployeeDao.findByUsername(username);
			if(employee != null && !employee.getId().equals(0) && !employee.getUsername().equalsIgnoreCase("")){
				MstEmployeeDto dto = new MstEmployeeDto();
				dto.setCreatedDate(employee.getCreatedDate());
				dto.setCreatedUser(employee.getCreatedUser());
				dto.setDateOfBirth(employee.getDateOfBirth());
				dto.setDeleted(employee.getDeleted());
				dto.setDepartment(employee.getDepartment());
				dto.setGender(employee.getGender());
				dto.setId(employee.getId());
				dto.setEmployeeName(employee.getEmployeeName());
				dto.setUpdatedDate(employee.getUpdatedDate());
				dto.setUpdatedUser(employee.getUpdatedUser());
				dto.setBirthPlace(employee.getBirthPlace());
				dto.setUsername(employee.getUsername());

				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
