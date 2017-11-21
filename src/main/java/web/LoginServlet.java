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
			throw new RuntimeException("û�����ҳ��");
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
		
		//��ȡsession
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
		//�����ݿ��в���
		UserDao ud = new UserDao();
		User u = ud.findById(code);
		
		//��ȡsession��imgcode
		
		String imgcode = (String) session.getAttribute("imgcode");
		String imgCheck = req.getParameter("imgCheck");
		if(imgcode == null || !imgcode.equalsIgnoreCase(imgCheck)) {
			req.setAttribute("error","��֤�����");
			req.getRequestDispatcher("jingTao.jsp").forward(req,res);
			return;
		}
		
		
		
		if(u==null) {
			System.out.println("�˺Ų�����");
			req.setAttribute("codeinfo","�˺Ų�����");
			req.getRequestDispatcher("jingTao.jsp").forward(
	                req, res);
		} else {
			if(!u.getPwd().equals(pwd)) {
				System.out.println("�������");
				req.setAttribute("pwdinfo","�������");
				req.getRequestDispatcher("jingTao.jsp").forward(
		                req, res);
			} else {
				System.out.println("�˺�������ȷ");
				req.setAttribute("success","�˺�������ȷ");
				res.sendRedirect("toshop.shop");
				//��һ��Ҫ��ȡ�˺���Ϣ�ͽ��
			}
		}
		
	}
}
