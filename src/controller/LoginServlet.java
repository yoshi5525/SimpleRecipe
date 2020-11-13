package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.UserDao;
import domain.User;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("UTF-8");

		HttpSession session = request.getSession();
		String loginStatus = (String) session.getAttribute("loginStatus");
		if (loginStatus == null) {
			request.setAttribute("loginStatus", loginStatus);
		}

		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginId = request.getParameter("login_id");
		String loginPass = request.getParameter("login_pass");
		String loginStatus = request.getParameter("login");

		UserDao dao = DaoFactory.createUserDao();
		User user = null;
		try {
			user = dao.findByLoginIdAndLoginPass(loginId, loginPass);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("loginStatus", loginStatus);
			response.sendRedirect("new");
		} else {
			request.setAttribute("error", "ログインIDまたはパスワードが間違っています");
			request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
		}
	}

}
