package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.DBBean;

public class ClothDao {
	static Connection connection = DBBean.getConn();

	static ResultSet resultSet = null;

	static PreparedStatement preparedStatement = null;

	public boolean add(String title, String content, String picture, String time) throws SQLException {
		boolean addFlag = false;
		try {

			String sql = "insert into news(newsTitle,newsContent,newsPicPath,newsTime) value(?,?,?,?)";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, content);
			preparedStatement.setString(3, picture);
			preparedStatement.setString(4, time);
			int rows = preparedStatement.executeUpdate();
			if (rows > 0) {
				addFlag = true;
				System.out.println(rows);
			} else {
				System.out.println(rows);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addFlag;

	}

}
