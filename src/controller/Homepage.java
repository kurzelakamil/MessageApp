package controller;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

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
			String preSubject = request.getParameter("subject");
			String subject = checkString(preSubject, map).toString();
			String preDescription = request.getParameter("newMessage");
			String description = checkString(preDescription, map).toString();
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

	private static final Map<Integer, String> map;
	static {
		map = new HashMap<>();
		map.put(160, "&nbsp;");
		map.put(60, "&lt;");
		map.put(62, "&gt;");
		map.put(38, "&amp;");
		map.put(162, "&cent;");
		map.put(163, "&pound;");
		map.put(165, "&yen;");
		map.put(8364, "&euro;");
		map.put(167, "&sect;");
		map.put(169, "&copy;");
		map.put(174, "&reg;");
		map.put(8482, "&trade;");
	}

	private static StringWriter checkString(String string, Map<Integer, String> map) {

		StringWriter str = new StringWriter();
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			int indexChar = (int) c;
			String mapPlace = map.get(indexChar);
			if (mapPlace == null) {
				if (indexChar > 0x7F) {
					str.write("&#");
					str.write(Integer.toString(c, 10));
					str.write(";");
				} else {
					str.write(c);
				}
			} else {
				str.write(mapPlace);
			}
		}
		return str;
	}

}