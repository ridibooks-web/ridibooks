package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	public boolean MemberInsert(MemberDto member){ //회원가입
		Connection conn = null;
		int num =0;
		String sql="";
		PreparedStatement pstmt;
		ResultSet rs;
		
		int result=0;
		
		try{
			pstmt = conn.prepareStatement("select * from member");
			rs = pstmt.executeQuery();
			
			sql="insert into member(id,pw,name,phone,master)values(?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhone());
			pstmt.setInt(5,member.getMaster());
			
			result=pstmt.executeUpdate();
			if(result==0)return false;
			
			return true;
		}catch(Exception ex){
			System.out.println("MemberInsert 에러 : "+ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		}
		return false;
	}
	
	public String MemberLogin(MemberDto member) // 로그인
	   {
	      String id = member.getId();
	      String pw = member.getPw();
	      
	      try {
	         String sql = "SELECT id,pw,name,master FROM member where id=? ";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, id);
	         rs = pstmt.executeQuery();
	         if (rs.next()) { //로그인
	            if (pw.equals(rs.getString("pw"))) {
	               returns = true+"\t"+rs.getString("master")+"\t"+rs.getString("name");
	            } else {
	               System.out.println("비번틀림");
	            }
	            System.out.println(returns);
	         }
	      } catch (SQLException e) {
	         System.out.println("login실패");
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      return returns;
	   }
}