package service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MstUserDao;
import dto.MstUserDto;
import entity.MstUser;
import entity.pk.MstUserPk;
import service.MstUserService;

@Service("mstUserSvc")
@Transactional
public class MstUserServiceImpl implements MstUserService {

	@Autowired
	private MstUserDao mstUserDao;
	
	@Override
	public List<MstUserDto> findAll() {
		List<MstUserDto> list = new ArrayList<>();
		
		try {
			List<MstUser> users = mstUserDao.findAll();
			
			if(users != null && !users.isEmpty() && users.size() > 0){
				for(MstUser user : users){
					MstUserDto mstUserDto = new MstUserDto();
					mstUserDto.setCreatedDate(user.getCreatedDate());
					mstUserDto.setCreatedUser(user.getCreatedUser());
					mstUserDto.setDeleted(user.getDeleted());
					mstUserDto.setId(user.getId());
					mstUserDto.setPassword(user.getPassword());
					mstUserDto.setUpdatedDate(user.getUpdatedDate());
					mstUserDto.setUpdatedUser(user.getUpdatedUser());
					mstUserDto.setUsername(user.getUsername());

					list.add(mstUserDto);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public MstUserDto findByUsernamePassword(String username, String password) {
		MstUserDto userDto = new MstUserDto();
		
		try {
			MstUser user = mstUserDao.findByUsernamePassword(username, password);
			
			if(user != null ){//&& !user.getId().equalsIgnoreCase("")){
					userDto.setCreatedDate(user.getCreatedDate());
					userDto.setCreatedUser(user.getCreatedUser());
					userDto.setDeleted(user.getDeleted());
					userDto.setId(user.getId());
					userDto.setPassword(user.getPassword());
					userDto.setUpdatedDate(user.getUpdatedDate());
					userDto.setUpdatedUser(user.getUpdatedUser());
					userDto.setUsername(user.getUsername());

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userDto;
	}

	@Override
	public void save(MstUserDto mstUserDto) {
		try {
			MstUser user = new MstUser();
			if(mstUserDto.getCreatedDate() == null){
				user.setCreatedDate(new Date());
			}
			else{
				user.setCreatedDate(mstUserDto.getCreatedDate());
			}
			user.setCreatedUser("ADMIN");
			user.setDeleted(mstUserDto.getDeleted());
			user.setPassword(mstUserDto.getPassword());
			user.setUsername(mstUserDto.getUsername());
			user.setId("USR001");
			mstUserDao.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Optional<List<MstUserDto>> findAllBySpFirstWay() {
//		try {
//			List<MstUserDto> dtos = null;
//			List<MstUser> users = mstUserDao.();
//			
//			if(users != null && !users.isEmpty() && users.size() > 0){
//				for(MstUser user : users){
//					MstUserDto mstUserDto = new MstUserDto();
//					mstUserDto.setCreatedDate(user.getCreatedDate());
//					mstUserDto.setCreatedUser(user.getCreatedUser());
//					mstUserDto.setDeleted(user.getDeleted());
//					mstUserDto.setId(user.getId());
//					mstUserDto.setPassword(user.getPassword());
//					mstUserDto.setUpdatedDate(user.getUpdatedDate());
//					mstUserDto.setUpdatedUser(user.getUpdatedUser());
//					mstUserDto.setUsername(user.getUsername());
//
//					list.add(mstUserDto);
//				}
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return Optional.empty();
//		}
		return Optional.empty();
	}
	
	

}
