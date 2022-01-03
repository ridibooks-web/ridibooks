package account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/account/login/pwcheck")
public class LoginPwcheck extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pw = request.getParameter("pw");
		
		pw = pw.trim();
		
		if(pw.length() < 8 || pw.length() > 20 ) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		} 
		
		String[] specialChar = {"!", "@", "?", "#", "$", "%", "^", "&", "*", "(", ")", "-", "+", "=", "|", "\\", "~", "<", ">", ",", "."};
		boolean isSpecial = false;
		for( int i=0; i < specialChar.length;i++) {
			isSpecial = pw.indexOf(specialChar[i]) > -1;
			if(isSpecial) break;
		}

		String[] alpha ={"a", "b", "c", "d", "f", "e","f","g","h","i","j","k", "l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A", "B","C", "D", "E", "F", "G", "H", "I", "J","K","L","M","N","O","P", "Q", "R","S", "T", "U","V", "W", "X", "Y", "Z"};
		boolean isAlpha = false;
		for( int i = 0; i<alpha.length; i++) {
			isAlpha = pw.indexOf(alpha[i]) >-1;
			if(isAlpha) break;
		}
		String[] num = {"0","1","2","3", "4","5", "6","7","8","9"};
		boolean isNum = false;
		for(int i = 0; i<num.length; i++) {
			isNum = pw.indexOf(num[i]) > -1;
			if(isNum) break;
		}

		// 영문/숫자/특수문자 중 2가지 이상 입력. 
		if((isSpecial || (!isAlpha || !isNum)) && (isAlpha || (!isSpecial || !isNum)) && (isNum || (!isAlpha || !isSpecial))) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		// 연속된 3자 이상의 같은 문자는 제한
		
		RequestDispatcher rd = request.getRequestDispatcher("/account/logindbcon");
		rd.forward(request, response);
			
	}

}
