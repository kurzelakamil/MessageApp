package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DbUtil;
import model.Message;

public class MessageDao {
	public static void createMessage(Message message) {
		try (Connection conn = DbUtil.getConn()) {
			String query = "INSERT INTO message (subject, text_message, created_date, author_id, receiver_id) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preStm = conn.prepareStatement(query);
			preStm.setString(1, message.getSubject());
			preStm.setString(2, message.getTextMessage());
			preStm.setTimestamp(3, message.getCreatedDate());
			preStm.setInt(4, message.getAuthorId());
			preStm.setInt(5, message.getReceiverId());
			preStm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Message> allMessagesByReceiverId(Integer receiverId){
		try(Connection conn = DbUtil.getConn()){
			List<Message> messages = new ArrayList<>();
			String query = "SELECT id, subject, text_message, created_date, author_id FROM message WHERE receiver_id = ?";
			PreparedStatement preStm = conn.prepareStatement(query);
			preStm.setInt(1, receiverId);
			ResultSet rs = preStm.executeQuery();
			while(rs.next()) {
				messages.add(new Message(rs.getInt("id"), rs.getString("subject"), rs.getString("text_message"), rs.getTimestamp("created_date"), rs.getInt("author_id"), receiverId));
			}
			return messages;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Message messageById(Integer id) {
		try(Connection conn = DbUtil.getConn()){
			String query = "SELECT * FROM message WHERE id = ?";
			PreparedStatement preStm = conn.prepareStatement(query);
			preStm.setInt(1, id);
			ResultSet rs = preStm.executeQuery();
			if(rs.next()) {
				return new Message(id, rs.getString("subject"), rs.getString("text_message"), rs.getTimestamp("created_date"), rs.getInt("author_id"), rs.getInt("receiver_id"));
			}else
				return null;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}