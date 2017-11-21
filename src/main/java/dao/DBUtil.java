package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;


public class DBUtil {
	private static BasicDataSource ds;
	static {
		Properties p = new Properties();
		try {
			//������
			p.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			String driver = p.getProperty("driver");
			String url = p.getProperty("url");
			String user = p.getProperty("user");
			String pwd = p.getProperty("pwd");
			String initsize = p.getProperty("initsize");
			String maxsize = p.getProperty("maxsize");
			ds = new BasicDataSource();
			//���ò���
			ds.setDriverClassName(driver);
			ds.setUrl(url);
			ds.setUsername(user);
			ds.setPassword(pwd);
			ds.setInitialSize(new Integer(initsize));
			ds.setMaxActive(new Integer(maxsize));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("�������ļ�ʧ��",e);
		}
	}
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	public static void close(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("�ر�����ʧ��",e);
			}
		}
	}
	public static void rollback(Connection conn) {
		if(conn!=null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("�ع�����ʧ��",e);
			}
		}
	}
}
