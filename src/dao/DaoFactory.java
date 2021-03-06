package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoFactory {

	public static UserDao createUserDao() {
		return new UserDaoImpl(getDataSource());
	}

	public static MenuDao createMenuDao() {
		return new MenuDaoImpl(getDataSource());
	}

	public static FoodDao createFoodDao() {
		return new FoodDaoImpl(getDataSource());
	}

	public static MenuFoodDao createMenuFoodDao() {
		return new MenuFoodDaoImpl(getDataSource());
	}

	public static TagDao createTagDao() {
		return new TagDaoImpl(getDataSource());
	}

	private static DataSource getDataSource() {
	    InitialContext ctx = null;
	    DataSource ds = null;
	    try {
	    	ctx = new InitialContext();
	    	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/simple_recipe_db");
	    } catch (NamingException e) {
	        if (ctx != null) {
	        	try {
	        		ctx.close();
	        	} catch (NamingException e1) {
	        		throw new RuntimeException(e1);
	        	}
	        }
	        throw new RuntimeException(e);
	    }
	    return ds;
	}
}
