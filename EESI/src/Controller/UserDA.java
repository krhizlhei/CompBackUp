package Controller;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.User;

public class UserDA {
	public User user;
	public Connection connection;
	public List<User> userlist;

	public UserDA(Connection connection) {
		this.connection = connection;
		initialize();
	}

	public void initialize() {
		userlist = new ArrayList<User>();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from user");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.sUsername(rs.getString(1));
				user.sPassword(rs.getString(2));
				userlist.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<User> userlist()
	{
		return userlist;
	}

}
