package dao;

import java.util.List;

import domain.MenuFood;

public interface MenuFoodDao {

	List<MenuFood> findAll() throws Exception;

	List<MenuFood> findById(Integer id) throws Exception;

	Integer findRegisteredId(Integer id) throws Exception;

	void insert(MenuFood menuFood) throws Exception;

	void update(MenuFood menuFood) throws Exception;

	void delete(Integer menuId) throws Exception;

	void deleteRegisteredId(Integer id) throws Exception;

}
