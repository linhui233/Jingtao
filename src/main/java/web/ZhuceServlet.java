package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;

/*
 * 本类负责注册业务
 * */
public class ZhuceServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//设置req的编码,否则为?????...
		req.setCharacterEncoding("utf-8");
		String account = req.getParameter("account");
		System.out.println(account);
		String pwd = req.getParameter("pwd");
		String sex = req.getParameter("sex");
		System.out.println(sex);
		String city = req.getParameter("city");
		UserDao userdao = new UserDao();
		//注册时从数据库查找账号 如果为空 则可以注册
		User user = userdao.findById(account);
		System.out.println(user);
		if(user != null) {
			req.setAttribute("zcinfo","账号已存在");
			req.getRequestDispatcher("zhuce.jsp").forward(req,res);
		}
		User u = new User();
		u.setCode(account);
		u.setPwd(pwd);
		u.setSex(sex);
		u.setCity(city);
		//添加到数据库
		
		boolean flag = userdao.add(u);
		if(flag) {
			req.setAttribute("zcinfo","注册成功");
			req.setAttribute("account",account);
		} else {
			req.setAttribute("zcinfo","注册失败");
		}
		req.getRequestDispatcher("zhuce.jsp").forward(req,res);
		
		
	}
}
