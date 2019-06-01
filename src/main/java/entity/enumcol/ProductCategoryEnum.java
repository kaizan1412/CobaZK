package entity.enumcol;

public enum ProductCategoryEnum {
	
	SEAFOOD("SEAFOOD", "Seafood"),
	BEVERAGES("BEVERAGES", "Beverages");
	
	private String categoryName;
	private String categoryCode;
	
	private ProductCategoryEnum(String categoryCode, String categoryName) {
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	

}
