package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;

/*
 * ���ฺ��ע��ҵ��
 * */
public class ZhuceServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//����req�ı���,����Ϊ?????...
		req.setCharacterEncoding("utf-8");
		String account = req.getParameter("account");
		System.out.println(account);
		String pwd = req.getParameter("pwd");
		String sex = req.getParameter("sex");
		System.out.println(sex);
		String city = req.getParameter("city");
		UserDao userdao = new UserDao();
		//ע��ʱ�����ݿ�����˺� ���Ϊ�� �����ע��
		User user = userdao.findById(account);
		System.out.println(user);
		if(user != null) {
			req.setAttribute("zcinfo","�˺��Ѵ���");
			req.getRequestDispatcher("zhuce.jsp").forward(req,res);
		}
		User u = new User();
		u.setCode(account);
		u.setPwd(pwd);
		u.setSex(sex);
		u.setCity(city);
		//��ӵ����ݿ�
		
		boolean flag = userdao.add(u);
		if(flag) {
			req.setAttribute("zcinfo","ע��ɹ�");
			req.setAttribute("account",account);
		} else {
			req.setAttribute("zcinfo","ע��ʧ��");
		}
		req.getRequestDispatcher("zhuce.jsp").forward(req,res);
		
		
	}
}
