package domain;

public class Food {

	private Integer id;
	private String name;
	private Double saltLevel;

	public Food() {

	}
	public Food(Integer id, String name, Double saltLevel) {
		this.id = id;
		this.name = name;
		this.saltLevel = saltLevel;
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
	public Double getSaltLevel() {
		return saltLevel;
	}
	public void setSaltLevel(Double saltLevel) {
		this.saltLevel = saltLevel;
	}

}
