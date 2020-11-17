package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ValidationMenu.errorKeys;
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
		request.getRequestDispatcher("UTF-8");
		String url = request.getRequestURI();

		HttpSession session = request.getSession();
		String loginStatus = (String) session.getAttribute("loginStatus");
		if (loginStatus == null) {
			response.sendRedirect("index");
			return;
		}

		Integer menuFoodLength = 1;
		request.setAttribute("menuFoodLength", menuFoodLength);

		try {
			FoodDao foodDao = DaoFactory.createFoodDao();
			List<Food> foods = foodDao.findAll();
			request.setAttribute("foods", foods);

			TagDao tagDao = DaoFactory.createTagDao();
			List<Tag> tags = tagDao.findAll();
			request.setAttribute("tags", tags);

			request.setAttribute("url", url);
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
		String url = request.getRequestURI();

		try {
			FoodDao foodDao = DaoFactory.createFoodDao();
			List<Food> foods = foodDao.findAll();
			request.setAttribute("foods", foods);

			TagDao tagDao = DaoFactory.createTagDao();
			List<Tag> tags = tagDao.findAll();
			request.setAttribute("tags", tags);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("index");
		}


//		Part part = request.getPart("image");
//		String fileName = part.getSubmittedFileName();
//		String path = request.getServletContext().getRealPath("/images/uploads");
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


//		String image = fileName;
		String image = request.getParameter("image");
		String name = request.getParameter("name");
		String kana = request.getParameter("kana");
		String foodstuff = request.getParameter("foodstuff");
		String recipe = request.getParameter("recipe");
		String strTagId = request.getParameter("tag_id");
		Integer tagId = 0;
		if (strTagId != null) {
			tagId = Integer.parseInt(strTagId);
		}

		String strMenuFoodLength = request.getParameter("menu-food-length");
		Integer menuFoodLength = Integer.parseInt(strMenuFoodLength);
		String[] strFoodIds = request.getParameterValues("food_id");
		String[] strQuantities = request.getParameterValues("food_quantity");


		// バリデーション
		ValidationMenu validationMenu = new ValidationMenu();
		Map<String, String> errors = validationMenu.errorCheck(name, kana, tagId, strQuantities);

		if (errors.get((Object)errorKeys.ERROR_MSG) != "" || errors.get((Object)errorKeys.ERROR_MSG) != null) {
			request.setAttribute("menuFoodLength", menuFoodLength);
			request.setAttribute("image", image);
			request.setAttribute("name", name);
			request.setAttribute("kana", kana);
			request.setAttribute("foodstuff", foodstuff);
			request.setAttribute("recipe", recipe);

			if (tagId == 0) {
				request.setAttribute("tag_id", 1);
			} else {
				request.setAttribute("tag_id", tagId);
			}

			Integer[] foodIds = new Integer[menuFoodLength];
			Integer[] quantities = new Integer[menuFoodLength];
			for (int i = 0; i < menuFoodLength; i++) {
				if (!strFoodIds[i].equals(null)) {
					foodIds[i] = Integer.parseInt(strFoodIds[i]);
				}
				if (!strQuantities[i].equals(null) && !strQuantities[i].equals("")) {
					quantities[i] = Integer.parseInt(strQuantities[i]);
				}
			}
			request.setAttribute("foodIds", foodIds);
			request.setAttribute("quantities", quantities);
			request.setAttribute("errors", errors);

			request.setAttribute("url", url);
			request.getRequestDispatcher("/WEB-INF/view/new.jsp").forward(request, response);
			return;
		}


		Menu menu = new Menu();
		menu.setImage(image);
		menu.setName(name);
		menu.setKana(kana);
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

		Integer foodId = 0;
		Integer quantity = 0;
		for (int i = 0; i < menuFoodLength; i++) {
			if (strFoodIds[i] != null && strFoodIds[i] != "") {
				foodId = Integer.parseInt(strFoodIds[i]);
			}
			if (strQuantities[i] != null && strFoodIds[i] != "") {
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
