package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;

public class UserDao {
	//������½ʱ�������ݿ�
	public User findById(String code) {
		Connection conn = null;
		User user = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from user_linhui where code = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,code);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setCode(rs.getString("code"));
				user.setPwd(rs.getString("pwd"));
				user.setMoney(rs.getDouble("money"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("�û�������");
		} finally {
			DBUtil.close(conn);
		}
		return user;
	}
	
	//����ע����Ϣ�������ݿ�
	public boolean add(User user) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into user_linhui(code,pwd,sex,city)"
						+ "values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,user.getCode());
			ps.setString(2,user.getPwd());
			ps.setString(3,user.getSex());
			ps.setString(4,user.getCity());
			
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return false;
	}

}
