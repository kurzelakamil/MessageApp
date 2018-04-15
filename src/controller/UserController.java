package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

import dao.UserDao;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmpassword");
		if (UserDao.checkUsername(username) == null) {
			if (password.equals(confirmPassword)) {
				User user = new User(username, password);
				UserDao.createUser(user);
				request.setAttribute("signedUp", true);
				getServletContext().getRequestDispatcher("/WEB-INF/loginPage.jsp").forward(request, response);
			} else {
				request.setAttribute("registrationScreen", true);
				request.setAttribute("unconfirmedPassword", true);
				getServletContext().getRequestDispatcher("/WEB-INF/loginPage.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("usernameDuplicate", true);
			request.setAttribute("registrationScreen", true);
			getServletContext().getRequestDispatcher("/WEB-INF/loginPage.jsp").forward(request, response);
		}
	}

}