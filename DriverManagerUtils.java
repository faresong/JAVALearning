package com.bookshop.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class DriverManagerUtils {
	private static DataSource pool ;
	private static Properties info;
	static {
          try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			InputStream inStream = DriverManagerUtils.class.getResourceAsStream("/dbcp.properties");
			info = new Properties();
			info.load(inStream);
			try {
				 pool =  BasicDataSourceFactory.createDataSource(info);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 从连接池中获取一个数据库连接
	 * @return
	 */
	public static Connection getConnection() {

		try {
			return pool.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	/**
	 * 资源释放方法
	 * @param conn
	 * @param pre
	 * @param result
	 */
	public static void closed(Connection conn ,PreparedStatement pre , ResultSet result) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pre != null) {
			try {
				pre.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(result != null) {
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
