package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;

import bean.ClothBean;
import bean.Message;
import bean.TMessage;
import bean.UserBean;
import dao.ClothDao;

/**
 * Servlet implementation class UploadImageServlet
 */
@WebServlet("/UploadImageServlet")
public class UploadImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadImageServlet() {
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Message message = new Message();
		ClothBean news = new ClothBean();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = null;
		try {
			items = (List<FileItem>) upload.parseRequest(request);
		} catch (FileUploadException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		Iterator<FileItem> it = items.iterator();
		if (items != null) {
			while (it.hasNext()) {
				FileItem tempitemFileItem = it.next();
				String itemNameString = tempitemFileItem.getFieldName();
				System.out.println(itemNameString);
				if (tempitemFileItem.isFormField()) {
					String content = tempitemFileItem.getString("utf-8");
					if ("title".equals(itemNameString)) {
						news.setNewsTitle(content);
						System.out.println(news.getNewsTitle());
					} else if ("content".equals(itemNameString)) {
						news.setNewsContent(content);
						System.out.println(news.getNewsContent());
					}
				} else {
					System.out.println(request.getSession().getServletContext().getRealPath("/"));
					File tempFile = new File(request.getSession().getServletContext().getRealPath("/") + "image\\"
							+ new File(tempitemFileItem.getName()).getName());
					System.out.println(tempFile);
					try {
						tempitemFileItem.write(tempFile);

					} catch (Exception e) {
						e.printStackTrace();
					}
					news.setNewsPicPath(
							tempFile.getCanonicalPath().substring(tempFile.getCanonicalPath().lastIndexOf("\\")));
					System.out.println(news.getNewsPicPath());
				}
			}
		}
		try {
			news.setNewsTime(new Date().toLocaleString());
			ClothDao clothDao = new ClothDao();
			boolean addFlag = clothDao.add(news.getNewsTitle(), news.getNewsContent(), news.getNewsPicPath(),
					news.getNewsTime());
			if (addFlag) {
				System.out.println("true");
				message.setCode(200);
				message.setData("token");
				message.setMessage("添加成功");
				out.print(JSON.toJSONString(message));
			} else {
				System.out.println("false");
				message.setCode(-11);
				message.setData("null");
				message.setMessage("添加失败");
				out.print(JSON.toJSONString(message));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}

}
