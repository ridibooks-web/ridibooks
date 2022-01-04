package account;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDao {
	// DB 연결
	public Connection getConnection( ) {
		Connection conn = null;
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:comp/env/maria/mvc");
			conn = ds.getConnection();
			return conn;
		} catch(NamingException e) {
			e.printStackTrace();
			System.out.println("Connection Pool과 관련 예외 발생");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connecting 관련 예외 발생");
		}
		
		return conn;
	}
}