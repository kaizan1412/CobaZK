package test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import component.RestResponse;
import dto.MstUserDto;

public class TestConsumesRestful {

	public static void main(String[] args) {
		
		RestResponse restResponse = null;
		List<MstUserDto> listUser  = null;
		
		// urlendpoint
		String urlendpoint = "http://localhost:8080/CobaZK/api/user/all";
		
		RestTemplate restTemplate = new RestTemplate();
		
		// is an endpoint request a body?
		Object sentObject = null;
		
		// declare a Http Header
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		// initialize the header for  Content-Type = "application/json"
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		// what Http RequestMethod that required
		final HttpMethod httpMethod =  HttpMethod.GET;
		
		// Create a Http object that contains Http Headers and Http Body
		final HttpEntity<Object> requestEntity = new HttpEntity<>(sentObject, httpHeaders);
		
		// what kind of return type of that endpoint, 
		// in this example the endpoint will return the RestResponse class (change to any type of Java class as same as the return type of the enpoint)
		ResponseEntity<RestResponse> responseEntity = null;
		int responseCode = 0;
		
		try {
			responseEntity = restTemplate.exchange(urlendpoint, httpMethod, requestEntity, RestResponse.class);
			responseCode = responseEntity.getStatusCode().value();
			restResponse = responseEntity.getBody();
			
		} catch (RestClientException e) {
			restResponse = new RestResponse(responseCode, e.getLocalizedMessage(), null);
		}
		
		System.out.println(restResponse.getResponseCode() + " " + restResponse.getMessage() + " " + restResponse.getObj());
		
		if(restResponse.getResponseCode() != 0){
			try {
				// we will try to convert the value of the obj variable in the RestResponse class
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				
				String json = mapper.writeValueAsString(restResponse.getObj());
				TypeFactory type = TypeFactory.defaultInstance();
				
				// the endpoint contains list of MstUserDto, so we will try to convert to List<MstUserDto>
				listUser = mapper.readValue(json, type.constructCollectionType(ArrayList.class, MstUserDto.class));
				
			} catch (Exception e) {
				e.printStackTrace();
				listUser = null;
			}
			
		}
		
		if(listUser != null && !listUser.isEmpty() && listUser.size() > 0){
			for(MstUserDto user : listUser){
				// print out the data of list of MstUserDto
				System.out.println(user.toString());
			}
		}
		else{
			System.out.println("No data Found");
		}
		
	}

}
