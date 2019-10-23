package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import bean.Message;
import bean.TMessage;
import bean.UserBean;
import dao.UserDao;

/**
 * Servlet implementation class SelectUserServlet
 */
@WebServlet("/SelectUserServlet")
public class SelectUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF8");
		PrintWriter out = response.getWriter();
		TMessage<List<UserBean>> message = new TMessage<List<UserBean>>();

		try {
			List<UserBean> userBeans = new ArrayList<UserBean>();
			userBeans = UserDao.userSelect();
			if (null != userBeans && userBeans.size() > 0) {
				System.out.println("获取用户数据成功 ");
				message.setData(userBeans);
				message.setMessage("获取成功");
				message.setCode(200);
				out.print(JSON.toJSONString(message));
			}else {
				System.out.println("无数据");
				 message.setCode(-11);
				 message.setData(null);
				 message.setMessage("无数据");
				 out.print(JSON.toJSONString(message));
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
