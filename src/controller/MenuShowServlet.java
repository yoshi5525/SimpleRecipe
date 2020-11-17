package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.MenuDao;
import dao.MenuFoodDao;
import dao.UserDao;
import domain.Menu;
import domain.MenuFood;
import domain.User;

/**
 * Servlet implementation class MenuShowServlet
 */
@WebServlet("/show")
public class MenuShowServlet extends HttpServlet {
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
			response.sendRedirect("index");
			return;
		}

		HttpSession session = request.getSession();
		Integer loginUserId = 0;
		User user = (User) session.getAttribute("user");
		if (user != null) {
			String userLoginId = user.getLoginId();
			UserDao userDao = DaoFactory.createUserDao();
			User loginUser = null;
			try {
				loginUser = userDao.findById(userLoginId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			loginUserId = loginUser.getId();
		}

		try {
			MenuDao menuDao = DaoFactory.createMenuDao();
			Menu menu = menuDao.findById(id, url);
			request.setAttribute("menu", menu);

			MenuFoodDao menuFoodDao = DaoFactory.createMenuFoodDao();
			List<MenuFood> menuFoods = menuFoodDao.findById(id);
			request.setAttribute("menu_foods", menuFoods);

			request.setAttribute("loginUserId", loginUserId);

			request.getRequestDispatcher("/WEB-INF/view/show.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("index");
			return;
		}
	}

}
