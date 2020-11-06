package dao;

import domain.User;

public interface UserDao {

	User findByLoginIdAndLoginPass(String loginId, String loginPass) throws Exception;

	User findById(String loginUserId) throws Exception;

}
