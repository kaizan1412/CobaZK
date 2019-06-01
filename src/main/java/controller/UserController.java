package controller;

import java.util.List;

import javax.ws.rs.client.ResponseProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dto.MstUserDto;
import service.MstUserService;

@RestController
@RequestMapping(value="/api/user")
public class UserController {
	
	@Autowired
	private MstUserService mstUserSvc;
	
	@RequestMapping(
			value="/all",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity findAll(){
		try {
			return new ResponseEntity<List<MstUserDto>>(mstUserSvc.findAll(), null, HttpStatus.OK);
//			return mstUserSvc.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
