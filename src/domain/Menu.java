package domain;

public class Menu {

	private Integer id;
	private String name;
	private String recipe;
	private String foodstuff;
	private String image;
	private Integer userId;

	public Menu() {

	}
	public Menu(Integer id, String name, String recipe, String foodstuff, String image, Integer userId) {
		this.id = id;
		this.name = name;
		this.recipe = recipe;
		this.foodstuff = foodstuff;
		this.image = image;
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRecipe() {
		return recipe;
	}
	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}
	public String getFoodstuff() {
		return foodstuff;
	}
	public void setFoodstuff(String foodstuff) {
		this.foodstuff = foodstuff;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
