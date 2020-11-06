package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;

import domain.User;

public class UserDaoImpl implements UserDao {

	private DataSource ds;

	public UserDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	private User mapToUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId((Integer)rs.getObject("id"));
		return user;
	}


	@Override
	public User findByLoginIdAndLoginPass(String loginId, String loginPass) throws Exception {
		User user = null;

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM users WHERE login_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, loginId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String hashedPass = rs.getString("login_pass");
				if (BCrypt.checkpw(loginPass, hashedPass)) {
					user = new User();
					user.setLoginId(loginId);
					user.setName(rs.getString("name"));
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return user;
	}


	@Override
	public User findById(String loginUserId) throws Exception {
		User user = null;

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT id FROM users WHERE login_id = ?";
		    PreparedStatement stmt = con.prepareStatement(sql);
		    stmt.setString(1, loginUserId);
		    ResultSet rs = stmt.executeQuery();

		    if (rs.next()) {
		        user = mapToUser(rs);
		    }
	    } catch (Exception e) {
	        throw e;
	    }
		return user;
	}

}
