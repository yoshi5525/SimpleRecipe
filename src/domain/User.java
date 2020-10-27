package domain;

public class User {

	private Integer id;
	private String name;
	private String loginId;
	private String loginPass;

	public User() {

	}
	public User(Integer id, String name, String loginId, String loginPass) {
		this.id = id;
		this.name = name;
		this.loginId = loginId;
		this.loginPass = loginPass;
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
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPass() {
		return loginPass;
	}
	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

}
