package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.DBBean;
import bean.UserBean;

public class UserDao {

	static Connection connection = DBBean.getConn();

	static ResultSet resultSet = null;

	static PreparedStatement preparedStatement = null;

	public static boolean userLogin(String phone, String password) throws SQLException {
		boolean flag = false;
		String sql = "select * from user where phone=? and password=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, phone);
		preparedStatement.setString(2, password);
		resultSet = preparedStatement.executeQuery();
		if (resultSet != null) {
			if (resultSet.next()) {
				flag = true;
			}
		}
		return flag;
	}

	public static List<UserBean> userSelect() throws SQLException {
		List<UserBean> list = new ArrayList<UserBean>();
		String sql = "select * from user";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		if (resultSet != null) {
			while (resultSet.next()) {
				UserBean userBean = new UserBean();
				userBean.setPhone(resultSet.getString("phone"));
				userBean.setPassword(resultSet.getString("password"));
				userBean.setUser_id(resultSet.getInt("user_id"));
				list.add(userBean);
			}
		}
		return list;

	}

	public static boolean registUser(String phone, String password) throws SQLException {
		boolean flag = false;
		String sql = "insert into user(phone,password) values(?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, phone);
		preparedStatement.setString(2, password);
		int result = preparedStatement.executeUpdate();
		if (result == 1) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;

	}
}
