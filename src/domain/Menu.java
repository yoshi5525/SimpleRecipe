package domain;

public class Menu {

	private Integer id;
	private String name;
	private String recipe;
	private String foodstuff;
	private String image;
	private Integer userId;
	private Integer tagId;
	private String tagName;
	private Integer idMenuFood;
	private Integer foodQuantity;
	private Integer foodId;
	private Integer idFood;
	private String foodName;


	public Menu() {

	}
	public Menu(Integer id, String name, String recipe, String foodstuff, String image, Integer userId, Integer tagId,
			String tagName, Integer idMenuFood, Integer foodQuantity, Integer foodId, Integer idFood, String foodName) {
		super();
		this.id = id;
		this.name = name;
		this.recipe = recipe;
		this.foodstuff = foodstuff;
		this.image = image;
		this.userId = userId;
		this.tagId = tagId;
		this.tagName = tagName;
		this.idMenuFood = idMenuFood;
		this.foodQuantity = foodQuantity;
		this.foodId = foodId;
		this.idFood = idFood;
		this.foodName = foodName;
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
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public Integer getIdMenuFood() {
		return idMenuFood;
	}
	public void setIdMenuFood(Integer idMenuFood) {
		this.idMenuFood = idMenuFood;
	}
	public Integer getFoodQuantity() {
		return foodQuantity;
	}
	public void setFoodQuantity(Integer foodQuantity) {
		this.foodQuantity = foodQuantity;
	}
	public Integer getFoodId() {
		return foodId;
	}
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	public Integer getIdFood() {
		return idFood;
	}
	public void setIdFood(Integer idFood) {
		this.idFood = idFood;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

}