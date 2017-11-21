package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Shop;

public class ShopDao implements Serializable{
	//根据页面查找
	public List<Shop> findShopByPage(int page,int size) {
		Connection conn = null;
		try {
			List<Shop> shops = new ArrayList<Shop>();
			conn = DBUtil.getConnection();
			int start = (page-1)*size+1;
			int end = page*size;
			String sql = 
					  "select * from  ("
					+ "	select e.*,rownum r from ("
					+ "		select * from shops_linhui order by img_id"
					+ "	) e "
					+ ") where r between "+start+" and "+end;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Shop shop = new Shop();
				shop.setName(rs.getString("name"));
				shop.setMoney(rs.getDouble("money"));
				shop.setColor(rs.getString("color"));
				shop.setSum(rs.getInt("sum"));
				shop.setNice(rs.getDouble("nice"));
				shop.setImgId(rs.getInt("img_id"));
				shops.add(shop);
			}
			return shops;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("分页查询失败",e);
		} finally {
			DBUtil.close(conn);
		}
		
		
	}
	
	//查找商品
	public List<Shop> findAll() {
		Connection conn ;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from shops_linhui";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Shop> list = new ArrayList<Shop>();
			while(rs.next()) {
				String name = rs.getString("name");
				Double money = rs.getDouble("money");
				String color = rs.getString("color");
				Integer sum = rs.getInt("sum");
				Double nice = rs.getDouble("nice");
				Integer imgId = rs.getInt("img_id");
				Shop shop = new Shop();
				shop.setName(name);
				shop.setMoney(money);
				shop.setColor(color);
				shop.setSum(sum);
				shop.setNice(nice);
				shop.setImgId(imgId);
				list.add(shop);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询商品失败",e);
		}
	}
	public static void main(String[] args) {
		ShopDao shopdao = new ShopDao();
		List<Shop> list = shopdao.findAll();
		for(Shop e : list) {
			System.out.println(e);
		}
	}
}
