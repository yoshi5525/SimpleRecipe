package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.MenuDao;
import domain.Menu;

/**
 * Servlet implementation class MenuIndexServlet
 */
@WebServlet("/index")
public class MenuIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			MenuDao dao = DaoFactory.createMenuDao();
			List<Menu> menus = dao.findAll();
			request.setAttribute("menus", menus);
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
