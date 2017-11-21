package web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import entity.User;
import util.CreateImgUtil;

public class LoginServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = req.getServletPath();
		if("/tologin.login".equals(path)) {
			req.getRequestDispatcher("jingTao.jsp").forward(req,res);
		} else if("/login.login".equals(path)) {
			login(req,res);
		} else if("/createimage.login".equals(path)) {
			createImage(req,res);
		} else if("/relogin.login".equals(path)){
			relogin(req,res);
		} else {
			throw new RuntimeException("没有这个页面");
		}
//		String incode = u.getCode();
//		String inpwd = u.getPwd();
//		System.out.println(incode);
//		System.out.println(inpwd);
		
	}
	protected void relogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session!=null) {
			session.invalidate();
		}
		res.sendRedirect("tologin.login");
	}
	
	
	protected void createImage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		CreateImgUtil ciu = new CreateImgUtil();
		Object[] objs = ciu.createImage();
		BufferedImage img = (BufferedImage) objs[1];
		String imgcode= (String) objs[0];
		
		//获取session
		HttpSession session = req.getSession();
		session.setAttribute("imgcode",imgcode);
		res.setContentType("html/png");
		OutputStream os = res.getOutputStream();
		ImageIO.write(img,"png",os);
		os.close();
		
	}
	protected void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String code = req.getParameter("code");
		String pwd = req.getParameter("pwd");
		System.out.println(code);
		System.out.println(pwd);
		HttpSession session = req.getSession();
		session.setAttribute("code",code);
		//从数据库中查找
		UserDao ud = new UserDao();
		User u = ud.findById(code);
		
		//获取session的imgcode
		
		String imgcode = (String) session.getAttribute("imgcode");
		String imgCheck = req.getParameter("imgCheck");
		if(imgcode == null || !imgcode.equalsIgnoreCase(imgCheck)) {
			req.setAttribute("error","验证码错误");
			req.getRequestDispatcher("jingTao.jsp").forward(req,res);
			return;
		}
		
		
		
		if(u==null) {
			System.out.println("账号不存在");
			req.setAttribute("codeinfo","账号不存在");
			req.getRequestDispatcher("jingTao.jsp").forward(
	                req, res);
		} else {
			if(!u.getPwd().equals(pwd)) {
				System.out.println("密码错误");
				req.setAttribute("pwdinfo","密码错误");
				req.getRequestDispatcher("jingTao.jsp").forward(
		                req, res);
			} else {
				System.out.println("账号密码正确");
				req.setAttribute("success","账号密码正确");
				res.sendRedirect("toshop.shop");
				//下一步要获取账号信息和金额
			}
		}
		
	}
}
