package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MessageDao;
import dao.UserDao;
import model.Message;

@WebServlet("/MessageController")
public class MessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MessageController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = String.valueOf(session.getAttribute("username"));
		Integer receiverId = UserDao.getId(username);
		if(request.getParameter("id") != null) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			Message message = MessageDao.messageById(id);
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/WEB-INF/messageDetails.jsp").forward(request, response);
		}
		List<Message> messages = MessageDao.allMessagesByReceiverId(receiverId);
		HashMap<Integer, String> authors = new HashMap<>();
		for(Message m : messages){
			String author = UserDao.getUsername(m.getAuthorId());
			authors.put(m.getAuthorId(), author);
		};
		request.setAttribute("authors", authors);
		request.setAttribute("messages", messages);
		getServletContext().getRequestDispatcher("/WEB-INF/messages.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = String.valueOf(session.getAttribute("username"));
		Integer receiverId = UserDao.getId(username);
		List<Message> messages = MessageDao.allMessagesByReceiverId(receiverId);
		HashMap<Integer, String> authors = new HashMap<>();
		for(Message m : messages){
			String author = UserDao.getUsername(m.getAuthorId());
			authors.put(m.getAuthorId(), author);
		};
		request.setAttribute("authors", authors);	
		request.setAttribute("messages", messages);
		getServletContext().getRequestDispatcher("/WEB-INF/messages.jsp").forward(request, response);
	}

}