package dao;

import java.util.List;

import domain.Menu;

public interface MenuDao {

	List<Menu> findAll() throws Exception;

	Menu findById(Integer id) throws Exception;

	void insert(Menu menu) throws Exception;

	void update(Menu menu) throws Exception;

	void delete(Integer id) throws Exception;

}
