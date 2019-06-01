package component;

public class RestResponse {
	
	private int responseCode;
	private String message;
	private Object obj;
	
	public RestResponse() {
		// TODO Auto-generated constructor stub
	}
	
	
	public RestResponse(int responseCode, String message, Object obj) {
		super();
		this.responseCode = responseCode;
		this.message = message;
		this.obj = obj;
	}


	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	

}
