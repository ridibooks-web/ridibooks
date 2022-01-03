package account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/account/loginidcheck")
public class LoginIdCheck extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		// 영문, 숫자 확인
		String[] alpha ={"a", "b", "c", "d", "f", "e","f","g","h","i","j","k", "l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A", "B","C", "D", "E", "F", "G", "H", "I", "J","K","L","M","N","O","P", "Q", "R","S", "T", "U","V", "W", "X", "Y", "Z"};
		boolean isAlpha = false;
		for( int i = 0; i<alpha.length; i++) {
			isAlpha = id.indexOf(alpha[i]) >-1;
			if(isAlpha) break;
		}
		
		String[] num = {"0","1","2","3", "4","5", "6","7","8","9"};
		boolean isNum = false;
		for(int i = 0; i<num.length; i++) {
			isNum = id.indexOf(num[i]) > -1;
			if(isNum) break;
		}
		if( !isAlpha || !isNum) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/account/loginpwcheck");
		rd.forward(request, response);
	}

}
