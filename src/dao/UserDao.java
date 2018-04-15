package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

import database.DbUtil;

public class UserDao {

	public static void createUser(User user) {
		try (Connection conn = DbUtil.getConn()) {
			String query = "INSERT INTO user (username, password) VALUES (?, ?)";
			PreparedStatement preStm = conn.prepareStatement(query);
			preStm.setString(1, user.getUsername());
			preStm.setString(2, user.getPassword());
			preStm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String loadPassword(String username) {
		try (Connection conn = DbUtil.getConn()) {
			String query = "SELECT * FROM user WHERE username = ?";
			PreparedStatement preStm = conn.prepareStatement(query);
			preStm.setString(1, username);
			ResultSet rs = preStm.executeQuery();
			if (rs.next()) {
				return rs.getString("password");
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String checkUsername(String username) {
		try (Connection conn = DbUtil.getConn()) {
			String query = "SELECT * FROM user WHERE username = ?";
			PreparedStatement preStm = conn.prepareStatement(query);
			preStm.setString(1, username);
			ResultSet rs = preStm.executeQuery();
			if (rs.next()) {
				return rs.getString("username");
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<String> allUsers() {
		try (Connection conn = DbUtil.getConn()) {
			List<String> usernames = new ArrayList<>();
			String query = "SELECT * FROM user";
			PreparedStatement preStm = conn.prepareStatement(query);
			ResultSet rs = preStm.executeQuery();
			while (rs.next()) {
				usernames.add(rs.getString("username"));
			}
			return usernames;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Integer getId(String username) {
		try (Connection conn = DbUtil.getConn()) {
			String query = "SELECT id FROM user WHERE username = ?";
			PreparedStatement preStm = conn.prepareStatement(query);
			preStm.setString(1, username);
			ResultSet rs = preStm.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getUsername(Integer id) {
		try (Connection conn = DbUtil.getConn()) {
			String query = "SELECT username FROM user WHERE id = ?";
			PreparedStatement preStm = conn.prepareStatement(query);
			preStm.setInt(1, id);
			ResultSet rs = preStm.executeQuery();
			if (rs.next()) {
				return rs.getString("username");
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
}