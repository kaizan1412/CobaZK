package service;

import java.util.List;
import java.util.Optional;

import dto.MstUserDto;
import entity.MstUser;

public interface MstUserService {
	
	public List<MstUserDto> findAll();
	public MstUserDto findByUsernamePassword(String username, String password);
	public void save(MstUserDto mstUserDto);
	public Optional<List<MstUserDto>> findAllBySpFirstWay();

}
