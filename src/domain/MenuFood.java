package domain;

public class MenuFood {

	private Integer id;
	private Integer quantity;
	private Integer menuId;
	private Integer foodId;
	private String foodName;
	private Double saltLevel;

	public MenuFood() {

	}
	public MenuFood(Integer id, Integer quantity, Integer menuId, Integer foodId, String foodName, Double saltLevel) {
		this.id = id;
		this.quantity = quantity;
		this.menuId = menuId;
		this.foodId = foodId;
		this.foodName = foodName;
		this.saltLevel = saltLevel;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getFoodId() {
		return foodId;
	}
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public Double getSaltLevel() {
		return saltLevel;
	}
	public void setSaltLevel(Double saltLevel) {
		this.saltLevel = saltLevel;
	}

}
