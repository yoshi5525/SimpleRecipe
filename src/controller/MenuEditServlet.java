package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.ValidationMenu.errorKeys;
import dao.DaoFactory;
import dao.FoodDao;
import dao.MenuDao;
import dao.MenuFoodDao;
import dao.TagDao;
import domain.Food;
import domain.Menu;
import domain.MenuFood;
import domain.Tag;

/**
 * Servlet implementation class MenuEditServlet
 */
@WebServlet("/edit")
@MultipartConfig(location="D:/Users/zd2H10/pleiades/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SimpleRecipe/images/uploads")
public class MenuEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();

		String strId = request.getParameter("id");
		Integer id = 0;
		if (strId != null) {
			id = Integer.parseInt(strId);
		} else {
			response.sendRedirect("show");
			return;
		}

		try {
			FoodDao foodDao = DaoFactory.createFoodDao();
			List<Food> foods = foodDao.findAll();
			request.setAttribute("foods", foods);

			TagDao tagDao = DaoFactory.createTagDao();
			List<Tag> tags = tagDao.findAll();
			request.setAttribute("tags", tags);

			MenuDao menuDao = DaoFactory.createMenuDao();
			Menu menu = menuDao.findById(id ,url);
			request.setAttribute("image", menu.getImage());
			request.setAttribute("name", menu.getName());
			request.setAttribute("kana", menu.getKana());
			request.setAttribute("foodstuff", menu.getFoodstuff());
			request.setAttribute("recipe", menu.getRecipe());
			request.setAttribute("tag_id", menu.getTagId());
			request.setAttribute("menu_id", menu.getId());

			MenuFoodDao menuFoodDao = DaoFactory.createMenuFoodDao();
			List<MenuFood> menuFoods = menuFoodDao.findById(id);
			Integer count = menuFoodDao.findByIdCount(id);

			Integer[] foodIds = new Integer[count];
			Double[] quantities = new Double[count];
			Integer[] menuFoodIds = new Integer[count];
			int i = 0;
			for (MenuFood menuFood: menuFoods) {
				foodIds[i] = menuFood.getFoodId();
				quantities[i] = menuFood.getQuantity();
				menuFoodIds[i] = menuFood.getId();
				i++;
			}
			request.setAttribute("menuFoodLength", count);
			request.setAttribute("foodIds", foodIds);
			request.setAttribute("quantities", quantities);
			request.setAttribute("menuFoodIds", menuFoodIds);

			request.setAttribute("url", url);
			request.getRequestDispatcher("/WEB-INF/view/edit.jsp").forward(request, response);
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
			response.sendRedirect("show");
		}


		Part part = request.getPart("image");
		String fileName = part.getSubmittedFileName();
		String path = request.getServletContext().getRealPath("/images/uploads");
		File filePath = new File(path);
		part.write(filePath + "/" + fileName);

		String strMenuId = request.getParameter("menu_id");
		Integer menuId = 0;
		if (strMenuId != null) {
			menuId = Integer.parseInt(strMenuId);
		}


		if (request.getParameter("delete") != null) {
			try {
				MenuFoodDao menuFoodDao = DaoFactory.createMenuFoodDao();
				menuFoodDao.delete(menuId);
				MenuDao menuDao = DaoFactory.createMenuDao();
				menuDao.delete(menuId);
				response.sendRedirect("index");
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("index");
			}
			return;
		}


		String image = fileName;
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
		String[] strMenuFoodIds = request.getParameterValues("menu_food_id");

		Integer[] foodIds = new Integer[menuFoodLength];
		Double[] quantities = new Double[menuFoodLength];
		for (int i = 0; i < menuFoodLength; i++) {
			if (!strFoodIds[i].equals(null) && !strFoodIds[i].equals("")) {
				foodIds[i] = Integer.parseInt(strFoodIds[i]);
			}
			if (!strQuantities[i].equals(null) && !strQuantities[i].equals("")) {
				quantities[i] = Double.parseDouble(strQuantities[i]);
			}
		}

		Integer[] menuFoodIds = new Integer[menuFoodLength];
		for (int i = 0; i < strMenuFoodIds.length; i++) {
			if (!strMenuFoodIds[i].equals(null) && !strMenuFoodIds[i].equals("")) {
				menuFoodIds[i] = Integer.parseInt(strMenuFoodIds[i]);
			}
		}


		// バリデーション
		ValidationMenu validationMenu = new ValidationMenu();
		Map<String, String> errors = validationMenu.errorCheck(name, kana, tagId, strQuantities);

		if (errors.get((Object)errorKeys.ERROR_MSG) != null) {
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

			request.setAttribute("foodIds", foodIds);
			request.setAttribute("quantities", quantities);
			request.setAttribute("menuFoodIds", menuFoodIds);
			request.setAttribute("errors", errors);

			request.setAttribute("url", url);
			request.getRequestDispatcher("/WEB-INF/view/edit.jsp").forward(request, response);
			return;
		}


		Menu menu = new Menu();
		menu.setImage(image);
		menu.setName(name);
		menu.setKana(kana);
		menu.setTagId(tagId);
		menu.setFoodstuff(foodstuff);
		menu.setRecipe(recipe);
		menu.setId(menuId);

		MenuDao menuDao = DaoFactory.createMenuDao();
		try {
			menuDao.update(menu);
		} catch (Exception e) {
			e.printStackTrace();
		}


		MenuFoodDao menuFoodDao = DaoFactory.createMenuFoodDao();
		for (int i = 0; i < menuFoodIds.length; i++) {
			try {
				menuFoodDao.deleteRegisteredId(menuFoodIds[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < menuFoodLength; i++) {
			MenuFood menuFood = new MenuFood();
			menuFood.setQuantity(quantities[i]);
			menuFood.setMenuId(menuId);
			menuFood.setFoodId(foodIds[i]);
			try {
				menuFoodDao.insert(menuFood);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect("show?id=" + menuId);
	}

}
