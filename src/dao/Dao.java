package dao;

import java.sql.Connection;

import org.apache.tomcat.jdbc.pool.DataSource;



public class Dao {
	static DataSource ds;

	public Connection getConnection() throws Exception {
		//データベースへのコネクションを返却
		return ds.getConnection();
	}
}