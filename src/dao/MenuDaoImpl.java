package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Menu;

public class MenuDaoImpl implements MenuDao {

	private DataSource ds;


	public MenuDaoImpl(DataSource ds) {
		this.ds = ds;
	}


	private Menu mapToIndexMenu(ResultSet rs) throws SQLException {
		Menu menu = new Menu();
		menu.setName(rs.getString("name"));
		menu.setId((Integer) rs.getObject("id"));
		menu.setTagId((Integer) rs.getObject("tag_id"));
		return menu;
	}


	private Menu mapToShowMenu(ResultSet rs) throws SQLException {
		Menu menu = new Menu();
		menu.setId((Integer) rs.getObject("id_menu"));
		menu.setName(rs.getString("name"));
		menu.setRecipe(rs.getString("recipe"));
		menu.setFoodstuff(rs.getString("foodstuff"));
		menu.setImage(rs.getString("image"));
		menu.setUserId((Integer)rs.getObject("user_id"));
		menu.setTagId((Integer)rs.getObject("tag_id"));
		menu.setTagName(rs.getString("tag_name"));
		menu.setIdMenuFood((Integer)rs.getObject("id_menu_food"));
		menu.setFoodQuantity((Integer)rs.getObject("food_quantity"));
		menu.setFoodId((Integer)rs.getObject("food_id"));
		menu.setIdFood((Integer)rs.getObject("id_food"));
		menu.setFoodName(rs.getString("food_name"));
		return menu;
	}

	private Menu mapToSearchMenu(ResultSet rs) throws SQLException {
		Menu menu = new Menu();
		menu.setName(rs.getString("name"));
		menu.setId((Integer) rs.getObject("id"));
		menu.setTagId((Integer) rs.getObject("tag_id"));
		return menu;
	}


	@Override
	public List<Menu> findAll() throws Exception {
		List<Menu> menus = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT id, name, tag_id FROM menus";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				menus.add(mapToIndexMenu(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return menus;
	}


	@Override
	public Menu findById(Integer id) throws Exception {
		Menu menu = null;

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT"
						+ " menus.id AS id_menu, menus.name, recipe, foodstuff, image, user_id, tag_id, tags.name AS tag_name,"
						+ " menu_foods.id AS id_menu_food, menu_foods.quantity AS food_quantity, menu_foods.food_id,"
						+ " foods.id AS id_food, foods.name AS food_name"
						+ " FROM menus"
						+ " JOIN tags "
						+ " ON menus.tag_id = tags.id"
						+ " JOIN menu_foods"
						+ " ON menus.id = menu_foods.menu_id"
						+ " JOIN foods"
						+ " ON menu_foods.food_id = foods.id"
						+ " WHERE menus.id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				menu = mapToShowMenu(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return menu;
	}


	@Override
	public Integer insert(Menu menu) throws Exception {
		Integer autoIncrementKey = 0;

		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO menus (name, recipe, foodstuff, image, user_id, tag_id)"
							+ " VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, menu.getName());
			stmt.setString(2, menu.getRecipe());
			stmt.setString(3, menu.getFoodstuff());
			stmt.setString(4, menu.getImage());
			stmt.setObject(5, menu.getUserId());
			stmt.setObject(6, menu.getTagId(), Types.INTEGER);
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				autoIncrementKey = rs.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		}
		return autoIncrementKey;
	}


	@Override
	public void update(Menu menu) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE menus SET"
							+ " name = ?, recipe = ?, foodstuff = ?, image = ?, tag_id = ?"
							+ " WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, menu.getName());
			stmt.setString(2, menu.getRecipe());
			stmt.setString(3, menu.getFoodstuff());
			stmt.setString(4, menu.getImage());
			stmt.setObject(5, menu.getTagId());
			stmt.setObject(6, menu.getId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}


	@Override
	public void delete(Integer id) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM menus WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id, Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}


	@Override
	public List<Menu> findSearchName(String name) throws Exception {
		List<Menu> menus = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT id, name, tag_id FROM menus WHERE name LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);

			String searchName = "";
			if (!name.equals("")) {
				searchName = name + "%";
			}
			stmt.setString(1, searchName);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				menus.add(mapToSearchMenu(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return menus;
	}

}
