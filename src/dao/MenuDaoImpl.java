package dao;

import java.util.List;

import javax.sql.DataSource;

import domain.Menu;

public class MenuDaoImpl implements MenuDao {

	private DataSource ds;

	public MenuDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Menu> findAll() throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Menu findById(Integer id) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void insert(Menu menu) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void update(Menu menu) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void delete(Integer id) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
