package account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/account/login1")
public class Login1 extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		id = id.trim();
		pw = pw.trim();
		
		if(id == null ||pw == null || id.isEmpty() || pw.isEmpty()) {
			// 로그인에 필요한 파라미터를 전달 받지 못했다면 SC_BAD_REQUEST 응답
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					
			response.setContentType("text/html;charset=UTF-8");
					
			PrintWriter out = response.getWriter();
			
			out.print("브라우저의 뒤로 가기 버튼을 눌러 ID 또는 PW를 입력하세요.");
			
			out.close();
			
			return ;
			
		} else if(id.length() < 8 || id.length() > 20 ) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/account/loginidcheck");
		rd.forward(request, response);
	}		
}
