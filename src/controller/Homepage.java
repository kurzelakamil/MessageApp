package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MessageDao;
import dao.UserDao;
import model.Message;

@WebServlet("/Homepage")
public class Homepage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Homepage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/homepage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("newMessage") != null) {
			String subject = request.getParameter("subject");
			String description = request.getParameter("newMessage");
			Timestamp createdDate = new Timestamp(System.currentTimeMillis());
			String username = String.valueOf(session.getAttribute("username"));
			Integer authorId = UserDao.getId(username);
			Integer receiverId = UserDao.getId(request.getParameter("receiver"));
			Message message = new Message(subject, description, createdDate, authorId, receiverId);
			MessageDao.createMessage(message);
			getServletContext().getRequestDispatcher("/WEB-INF/homepage.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/WEB-INF/homepage.jsp").forward(request, response);
		}
	}

}