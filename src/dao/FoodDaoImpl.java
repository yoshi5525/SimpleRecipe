package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Food;

public class FoodDaoImpl implements FoodDao {

	private DataSource ds;

	public FoodDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	private Food mapToFood(ResultSet rs) throws SQLException {
		Food foods = new Food();
		foods.setId((Integer) rs.getObject("id"));
		foods.setName(rs.getString("name"));
		foods.setSaltLevel((Double)rs.getDouble("salt_level"));
		return foods;
	}

	@Override
	public List<Food> findAll() throws Exception {
		List<Food> foods = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM foods";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				foods.add(mapToFood(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return foods;
	}

	@Override
	public Food findById(Integer id) throws Exception {
		Food food = null;

		try (Connection con = ds.getConnection()) {
		    String sql = "SELECT * FROM foods WHERE id = ?";
		    PreparedStatement stmt = con.prepareStatement(sql);
		    stmt.setObject(1, id, Types.INTEGER);
		    ResultSet rs = stmt.executeQuery();

		    if (rs.next()) {
		        food = mapToFood(rs);
		    }
	    } catch (Exception e) {
	        throw e;
	    }
		return food;
	}

}
