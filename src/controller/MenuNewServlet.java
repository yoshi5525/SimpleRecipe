package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.DaoFactory;
import dao.FoodDao;
import dao.MenuDao;
import dao.MenuFoodDao;
import dao.TagDao;
import dao.UserDao;
import domain.Food;
import domain.Menu;
import domain.MenuFood;
import domain.Tag;
import domain.User;

/**
 * Servlet implementation class MenuAddServlet
 */
@WebServlet("/new")
@MultipartConfig(location="D:/Users/zd2H10/pleiades/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SimpleRecipe/images/uploads")
public class MenuNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FoodDao foodDao = DaoFactory.createFoodDao();
			List<Food> foods = foodDao.findAll();
			request.setAttribute("foods", foods);

			TagDao tagDao = DaoFactory.createTagDao();
			List<Tag> tags = tagDao.findAll();
			request.setAttribute("tags", tags);

			request.getRequestDispatcher("/WEB-INF/view/new.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("index");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("UTF-8");

		Part part = request.getPart("image");
		String fileName = part.getSubmittedFileName();
//		String path = request.getServletContext().getRealPath("/uploads");
//		File filePath = new File(path);
//		part.write(filePath + "/" + fileName);


		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String userLoginId = user.getLoginId();
		UserDao userDao = DaoFactory.createUserDao();
		User loginUser = null;
		try {
			loginUser = userDao.findById(userLoginId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer loginUserId = loginUser.getId();


		String image = fileName;
		String name = request.getParameter("name");
		String strTagId = request.getParameter("tag_id");
		String foodstuff = request.getParameter("foodstuff");
		String recipe = request.getParameter("recipe");
		Integer tagId = 0;
		if (strTagId != null) {
			tagId = Integer.parseInt(strTagId);
		}

		Menu menu = new Menu();
		menu.setImage(image);
		menu.setName(name);
		menu.setTagId(tagId);
		menu.setFoodstuff(foodstuff);
		menu.setRecipe(recipe);
		menu.setUserId(loginUserId);

		Integer autoIncrementKey = 0;
		MenuDao menuDao = DaoFactory.createMenuDao();
		try {
			autoIncrementKey = menuDao.insert(menu);
		} catch (Exception e) {
			e.printStackTrace();
		}


		String strMenuFoodLength = request.getParameter("menu-food-length");
		Integer menuFoodLength = Integer.parseInt(strMenuFoodLength);
		Integer foodId = 0;
		Integer quantity = 0;

		String[] strFoodIds = request.getParameterValues("food_id");
		String[] strQuantities = request.getParameterValues("food_quantity");

		for (int i = 0; i < menuFoodLength; i++) {
			if (strFoodIds[i] != null) {
				foodId = Integer.parseInt(strFoodIds[i]);
			}
			if (strQuantities[i] != null) {
				quantity = Integer.parseInt(strQuantities[i]);
			}

			MenuFood menuFood = new MenuFood();
			menuFood.setQuantity(quantity);
			menuFood.setMenuId(autoIncrementKey);
			menuFood.setFoodId(foodId);
			MenuFoodDao menuFoodDao = DaoFactory.createMenuFoodDao();

			try {
				menuFoodDao.insert(menuFood);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect("show?id=" + autoIncrementKey);
	}

}
