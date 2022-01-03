package account;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBMS;

@WebServlet("/account/logindbcon")
public class LoginDbCon extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 정보 조회
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		try {
			// DB 접속
			Connection conn = DBMS.getConnection();

			String sql = "SELECT * FROM memberInfo WHERE id = ? AND pw = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			ResultSet rs = pstmt.executeQuery();

			// 조회 결과가 있으면 로그인 성공
			boolean isLogin = rs.next();
			
			if(isLogin) {
				HttpSession session = request.getSession();
	
				if(session.isNew() || session.getAttribute("isLogin")== null) {
					session.setAttribute("isLogin", true);
					session.setAttribute("id", id);
					session.setMaxInactiveInterval(600);
					response.setStatus(200);
					
				} else {
					response.setStatus(208);
					return ;
				}
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return ;
			}

			//DBMS와 관련된 자원 해제
			rs.close();
			pstmt.close();
			conn.close();

			} catch(SQLException e) {
				System.out.println("DBMS와 관련된 문제가 생겼음");
			}
	}
}
