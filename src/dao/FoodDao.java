package dao;

import java.util.List;

import domain.Food;

public interface FoodDao {

	List<Food> findAll() throws Exception;

	Food findById(Integer id) throws Exception;

}
