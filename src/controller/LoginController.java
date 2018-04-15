package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import dao.UserDao;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("logout") != null) {														
			request.getSession().invalidate();
			getServletContext().getRequestDispatcher("/WEB-INF/loginPage.jsp").forward(request, response);
		} else if (request.getParameter("reg") != null) {
			request.setAttribute("registrationScreen", true);
			getServletContext().getRequestDispatcher("/WEB-INF/loginPage.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/WEB-INF/loginPage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String providedPassword = request.getParameter("password");
		String systemPassword = UserDao.loadPassword(username);
		if (BCrypt.checkpw(providedPassword, systemPassword)) {
			session.setAttribute("username", username);
			session.setAttribute("password", systemPassword);
			List<String> usernames = UserDao.allUsers();
			session.setAttribute("usernames", usernames);
			getServletContext().getRequestDispatcher("/Homepage").forward(request, response);
		} else {
			request.setAttribute("loginSuccess", false);
			getServletContext().getRequestDispatcher("/WEB-INF/loginPage.jsp").forward(request, response);
		}
	}

}