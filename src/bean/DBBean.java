package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBBean {
	public static Connection conn = null;

	public static Connection getConn() {

		try {

			// /ΪӦ�ó����������

			Class.forName("com.mysql.jdbc.Driver");

			// ʹ��JDBC_URL����ʶ�ض������ݿ�

			// String driver_url =
			// "jdbc:mysql://172.20.10.3:3306/paotui?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";

			String driver_url = "jdbc:mysql://localhost:3306/db_search?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";

			// �������ݿ��URL,���ݿ��û������û�������������ݿ�

			conn = DriverManager.getConnection(driver_url, "root", "root");

			if (conn != null) {

				System.out.println("���ӳɹ�");

			} else {

				System.out.println("����ʧ��");

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return conn;

	}

	public static void closeAll(Connection conn, PreparedStatement ps) {

		System.out.println("���ݿ�Ͽ�����");

		if (conn != null) {

			try {

				conn.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

		if (ps != null) {

			try {

				ps.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

	}

	/**
	 * 
	 * �ر����ݿ�
	 */

	public static void closeAll(Connection conn, PreparedStatement ps,
			ResultSet rs) {

		if (conn != null) {

			try {

				conn.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

		if (ps != null) {

			try {

				ps.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

		if (rs != null) {

			try {

				rs.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

	}
}
