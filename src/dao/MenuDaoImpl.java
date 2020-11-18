package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
		menu.setKana(rs.getString("kana"));
		menu.setId((Integer) rs.getObject("id"));
		menu.setTagId((Integer) rs.getObject("tag_id"));
		return menu;
	}


	private Menu mapToShowMenu(ResultSet rs) throws SQLException {
		Menu menu = new Menu();
		menu.setId((Integer) rs.getObject("id_menu"));
		menu.setName(rs.getString("name"));
		menu.setKana(rs.getString("kana"));
		menu.setRecipe(rs.getString("recipe"));
		menu.setFoodstuff(rs.getString("foodstuff"));
		menu.setImage(rs.getString("image"));
		menu.setUserId((Integer)rs.getObject("user_id"));
		menu.setTagId((Integer)rs.getObject("tag_id"));
		menu.setTagName(rs.getString("tag_name"));
		menu.setIdMenuFood((Integer)rs.getObject("id_menu_food"));
		menu.setFoodQuantity(rs.getDouble("food_quantity"));
		menu.setFoodId((Integer)rs.getObject("food_id"));
		menu.setIdFood((Integer)rs.getObject("id_food"));
		menu.setFoodName(rs.getString("food_name"));
		return menu;
	}


	@Override
	public List<Menu> findAll() throws Exception {
		List<Menu> menus = new ArrayList<>();
		List<Menu> sortedMenus;

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT id, name, kana, tag_id FROM menus";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				menus.add(mapToIndexMenu(rs));
			}
			sortedMenus = menus.stream().sorted(new Comparator<Menu>(){
			    @Override
			    public int compare(Menu p1, Menu p2) {
			        return p1.getKana().compareTo(p2.getKana());
			    }
			}).collect(Collectors.toList());
		} catch (Exception e) {
			throw e;
		}
		return sortedMenus;
	}


	@Override
	public Menu findById(Integer id, String url) throws Exception {
		Menu menu = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT"
						+ " menus.id AS id_menu, menus.name, menus.kana, recipe, foodstuff, image, user_id, tag_id, tags.name AS tag_name,"
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
			stmt.setObject(1, id, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				menu = mapToShowMenu(rs);
			}

			if (menu != null && url.equals("/SimpleRecipe/show")) {
				String strFoodstuff = menu.getFoodstuff();
				String replacedFoodstuff = strFoodstuff.replaceAll("、", "\n･");
				replacedFoodstuff = replacedFoodstuff.replaceFirst("", "･");
				menu.setFoodstuff(replacedFoodstuff);

				String strRecipe = menu.getRecipe();
				String replacedRecipe = null;

				int line = strRecipe.split("。", - 1).length -1;
				String[] arrayRecipe = strRecipe.split("。");
				String changeText = "";
				for (int i = 1; i <= line + 1; i++) {
					if (i < line + 1) {
						changeText = arrayRecipe[i - 1].replaceFirst("", i + ":");
						changeText += arrayRecipe[i - 1].replaceFirst("$", "\n");
						replacedRecipe += changeText;
					} else {
						changeText = arrayRecipe[i - 1].replaceFirst("", i + ":");
						replacedRecipe += changeText;
					}
				}
				replacedRecipe = replacedRecipe.replaceFirst("null", "");
				menu.setRecipe(replacedRecipe);
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
			String sql = "INSERT INTO menus (name, kana, recipe, foodstuff, image, user_id, tag_id)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, menu.getName());
			stmt.setString(2, menu.getKana());
			stmt.setString(3, menu.getRecipe());
			stmt.setString(4, menu.getFoodstuff());
			stmt.setString(5, menu.getImage());
			stmt.setObject(6, menu.getUserId());
			stmt.setObject(7, menu.getTagId(), Types.INTEGER);
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
							+ " name = ?, kana = ?, recipe = ?, foodstuff = ?, image = ?, tag_id = ?"
							+ " WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, menu.getName());
			stmt.setString(2, menu.getKana());
			stmt.setString(3, menu.getRecipe());
			stmt.setString(4, menu.getFoodstuff());
			stmt.setString(5, menu.getImage());
			stmt.setObject(6, menu.getTagId());
			stmt.setObject(7, menu.getId(), Types.INTEGER);
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
		List<Menu> sortedMenus;

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT id, name, tag_id, kana FROM menus"
						+ " WHERE name LIKE ? OR kana LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);

			String searchName = "";
			if (!name.equals("")) {
				searchName = name + "%";
			}
			stmt.setString(1, searchName);
			stmt.setString(2, searchName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				menus.add(mapToIndexMenu(rs));
			}

			sortedMenus = menus.stream().sorted(new Comparator<Menu>(){
			    @Override
			    public int compare(Menu p1, Menu p2) {
			        return p1.getKana().compareTo(p2.getKana());
			    }
			}).collect(Collectors.toList());
		} catch (Exception e) {
			throw e;
		}
		return sortedMenus;
	}

}
