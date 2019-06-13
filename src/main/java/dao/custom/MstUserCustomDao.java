package dao.custom;

import java.util.List;
import java.util.Optional;

import entity.MstDepartment;
import entity.MstUser;

public interface MstUserCustomDao {
	
	public MstUser getLastInserted();
	public Optional<List<MstUser>> findAllBySpFirstWay();
	public Optional<List<MstUser>> findAllBySpSecondWay();
	public Optional<List<MstUser>> findAllBySpParamFirstWay(String username, String password);
	public Optional<List<MstUser>> findAllBySpParamSecondWay(String username, String password);
	public Optional<List<MstUser>> findAllBySpParamThirdWay(String username, String password);
	public Optional<List<MstUser>> findAllBySpParamFourthWay(String username, String password);

}
