package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.MenuFood;

public class MenuFoodDaoImpl implements MenuFoodDao {

	private DataSource ds;

	public MenuFoodDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	private MenuFood mapToMenuFood(ResultSet rs) throws SQLException {
		MenuFood menuFood = new MenuFood();
		menuFood.setId((Integer)rs.getObject("id"));
		menuFood.setQuantity((Integer)rs.getObject("quantity"));
		menuFood.setMenuId((Integer)rs.getObject("menu_id"));
		menuFood.setFoodId((Integer)rs.getObject("food_id"));
		return menuFood;
	}

	private MenuFood mapToShowMenuFood(ResultSet rs) throws SQLException {
		MenuFood menuFood = new MenuFood();
		menuFood.setId((Integer)rs.getObject("id"));
		menuFood.setQuantity((Integer)rs.getObject("quantity"));
		menuFood.setMenuId((Integer)rs.getObject("menu_id"));
		menuFood.setFoodId((Integer)rs.getObject("food_id"));
		menuFood.setFoodName(rs.getString("name"));
		return menuFood;
	}


	@Override
	public List<MenuFood> findAll() throws Exception {
		List<MenuFood> menuFoods = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM menu_foods";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				menuFoods.add(mapToMenuFood(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return menuFoods;
	}


	@Override
	public List<MenuFood> findById(Integer id) throws Exception {
		List<MenuFood> menuFoods = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
		    String sql = "SELECT * FROM menu_foods"
		    			+ " JOIN foods"
		    			+ " ON menu_foods.food_id = foods.id"
		    			+ " WHERE menu_id = ?";
		    PreparedStatement stmt = con.prepareStatement(sql);
		    stmt.setObject(1, id, Types.INTEGER);
		    ResultSet rs = stmt.executeQuery();

		    while (rs.next()) {
		        menuFoods.add(mapToShowMenuFood(rs));
		    }
	    } catch (Exception e) {
	        throw e;
	    }
		return menuFoods;
	}


	@Override
	public Integer findRegisteredId(Integer id) throws Exception {
		Integer registeredId = 0;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT id FROM menu_foods WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id, Types.INTEGER);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				registeredId = rs.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		}
		return registeredId;
	}

	@Override
	public void insert(MenuFood menuFood) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO menu_foods (quantity, menu_id, food_id)"
						+ " VALUES ( ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, menuFood.getQuantity(), Types.INTEGER);
			stmt.setObject(2, menuFood.getMenuId(), Types.INTEGER);
			stmt.setObject(3, menuFood.getFoodId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void update(MenuFood menuFood) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE menu_foods SET quantity = ?, menu_id = ?, food_id = ?"
						+ " WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, menuFood.getQuantity(), Types.INTEGER);
			stmt.setObject(2, menuFood.getMenuId(), Types.INTEGER);
			stmt.setObject(3, menuFood.getFoodId(), Types.INTEGER);
			stmt.setObject(4, menuFood.getId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void delete(Integer menuId) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM menu_foods WHERE menu_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, menuId, Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void deleteRegisteredId(Integer id) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM menu_foods WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id, Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

}
