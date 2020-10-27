package dao;

import javax.sql.DataSource;

import domain.User;

public class UserDaoImpl implements UserDao {

	private DataSource ds;

	public UserDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public User findByLoginIdAndLoginPass(String loginId, String loginPass) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
