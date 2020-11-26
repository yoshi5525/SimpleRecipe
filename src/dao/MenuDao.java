package dao;

import java.util.List;

import domain.Menu;

public interface MenuDao {

	List<Menu> findAll() throws Exception;

	List<Menu> findSearchAll(String name) throws Exception;

	Menu findById(Integer id, String url) throws Exception;

	Integer insert(Menu menu) throws Exception;

	void update(Menu menu) throws Exception;

	void delete(Integer id) throws Exception;

	List<Menu> findSearchName(String name) throws Exception;

}
