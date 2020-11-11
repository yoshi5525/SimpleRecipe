package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * Servlet implementation class MenuDeleteServlet
 */
@WebServlet("/delete")
public class MenuDeleteServlet extends HttpServlet {
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
		request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);

	}

}
