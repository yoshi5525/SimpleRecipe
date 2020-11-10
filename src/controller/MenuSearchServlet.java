package controller;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DaoFactory;
import dao.MenuDao;
import domain.Menu;

/**
 * Servlet implementation class MenuSearchServlet
 */
@WebServlet("/search")
public class MenuSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json; charset=UTF-8");

		String searchName = request.getParameter("searchName");
		MenuDao dao = DaoFactory.createMenuDao();
		Writer writer = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();

		List<Menu> menus = null;
		try {
			if (searchName != null) {
				menus = dao.findSearchName(searchName);
				writer.write(mapper.writeValueAsString(menus));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}