package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ShopDao;
import dao.UserDao;
import entity.Shop;
import entity.User;

public class ShopServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String path = req.getServletPath();//获得网名
		if(path.equals("/toshop.shop")) {
			toShop(req, res);
		} else if(path.equals("/shop.shop")){
			Shop(req,res);
		} else if(path.equals("/savebuy.shop")){
			saveBuy(req,res);
		} else {
			throw new RuntimeException("无此购物车页面");
		}
	}
	
	protected void saveBuy(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//保存到session
		String[] name = req.getParameterValues("name1");
		String[] price1 =(req.getParameterValues("price1"));
		String[] buySum = (req.getParameterValues("buySum"));
		List<Shop> list = new ArrayList<Shop>();
		
		for(int i=0;i<name.length;i++) {
			Shop shop = new Shop();
			shop.setName(name[i]);
			Double money = new Double(price1[i]);
			shop.setMoney(money);
			Integer buysum = new Integer(buySum[i]);
			shop.setBuySum(buysum);
			shop.setSumprice(money*buysum);
			list.add(shop);
		}
		System.out.println(list);
		HttpSession session = req.getSession();
		Integer page = new Integer(req.getParameter("page"));
		
		session.setAttribute("buyshops",list);
		req.getRequestDispatcher("shop.shop?"+page).forward(req,res);
	}
	
	
	//转到shop.jsp
	protected void toShop(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("buy.jsp").forward(req,res);
	}
	
	protected void Shop(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//读取session购物车信息
		HttpSession session = req.getSession();
		System.out.println("-----------------------------------------------------");
		
		List<Shop> shops = (List<Shop>) session.getAttribute("buyshops");
		System.out.println(shops+"-------------------------------------------");
		if(shops != null) {
			
			req.setAttribute("buyshops1",shops);
			double total = 0;
			for(Shop shop : shops) {
				double onesum = shop.getSumprice();
				total+=onesum;
			}
			req.setAttribute("totalPrice",total);
		}
		//查询商品
		ShopDao shopdao = new ShopDao();
		int size = 5;
		int page = new Integer(req.getParameter("page"));
		int maxnum = shopdao.findAll().size();
		int maxpage = (maxnum%size) == 0? (maxnum/size) : (maxnum/size+1);
		req.setAttribute("maxpage",maxpage);
		List<Shop> list = shopdao.findShopByPage(page,size);
		
		req.setAttribute("page",page);
		req.setAttribute("shops",list);
		
		
		//增加在购物车界面显示余额的功能
		//先获得code,根据code查询 money
		String code = (String)session.getAttribute("code");
		UserDao userdao = new UserDao();
		User user = userdao.findById(code);
		Double money = user.getMoney();
		req.setAttribute("money",money);
		
		req.getRequestDispatcher("buy.jsp").forward(req,res);
	}
}















