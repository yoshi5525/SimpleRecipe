package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
			Menu menu = menuDao.findById(id);
			request.setAttribute("menu", menu);

			MenuFoodDao menuFoodDao = DaoFactory.createMenuFoodDao();
			List<MenuFood> menuFoods = menuFoodDao.findById(id);
			request.setAttribute("menuFoods", menuFoods);

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

		Part part = request.getPart("image");
		String fileName = part.getSubmittedFileName();
//		String path = request.getServletContext().getRealPath("/images/uploads");
//		File filePath = new File(path);
//		part.write(filePath + "/" + fileName);

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
		menu.setId(menuId);

		MenuDao menuDao = DaoFactory.createMenuDao();
		try {
			menuDao.update(menu);
		} catch (Exception e) {
			e.printStackTrace();
		}


		MenuFoodDao menuFoodDao = DaoFactory.createMenuFoodDao();
		String[] strFoodIds = request.getParameterValues("food_id");
		String[] strQuantities = request.getParameterValues("food_quantity");
		String[] strRegisteredIds = request.getParameterValues("registered_id");

		String strMenuFoodLength = request.getParameter("menu-food-length");
		Integer menuFoodLength = Integer.parseInt(strMenuFoodLength);
		Integer deleteCount = strRegisteredIds.length - menuFoodLength;

		int[] registeredIds = null;
		if (strRegisteredIds != null) {
			registeredIds = new int[strRegisteredIds.length];
			for (int i = 0; i < strRegisteredIds.length; i++) {
				registeredIds[i] = Integer.parseInt(strRegisteredIds[i]);
			};
		}

		if (strRegisteredIds.length > menuFoodLength) {
			try {
				for (int i = 0; i < deleteCount; i++) {
					menuFoodDao.deleteRegisteredId(registeredIds[deleteCount - i]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Integer foodId = 0;
		Integer quantity = 0;

		for (int i = 0; i < menuFoodLength; i++) {
			if (strFoodIds[i] != null) {
				foodId = Integer.parseInt(strFoodIds[i]);
			}
			if (strQuantities[i] != null) {
				quantity = Integer.parseInt(strQuantities[i]);
			}

			MenuFood menuFood = new MenuFood();
			menuFood.setQuantity(quantity);
			menuFood.setMenuId(menuId);
			menuFood.setFoodId(foodId);

			try {
				if (i <= strRegisteredIds.length && menuFoodDao.findRegisteredId(registeredIds[i]) != 0) {
					menuFood.setId(registeredIds[i]);
					menuFoodDao.update(menuFood);
				} else {
					menuFoodDao.insert(menuFood);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect("show?id=" + menuId);
	}

}
