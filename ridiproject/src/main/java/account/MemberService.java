package account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberService {
	
	public int loginMember(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		id = id.trim();
		pw = pw.trim();
		
		int statusCode ;
		
		if(id == null ||pw == null || id.isEmpty() || pw.isEmpty()) {
			// 로그인에 필요한 파라미터를 전달 받지 못했다면 SC_BAD_REQUEST 응답
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
			return statusCode;
			
		}  else if(id.length() < 8 || id.length() > 20 ) {
			statusCode = HttpServletResponse.SC_UNAUTHORIZED;
			return statusCode;
		}
		
		statusCode = idCheck(id);
		
		if(statusCode == 200) {
			statusCode = pwCheck(pw);
		}
		
		return statusCode;
		
	}
	
	public int idCheck(String id) {
		int statusCode;
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
			statusCode = HttpServletResponse.SC_UNAUTHORIZED;
			return statusCode;
		}
		
		return 200;
	}
	
	public int pwCheck(String pw) {
		
		if(pw.length() < 8 || pw.length() > 20 ) {
			return HttpServletResponse.SC_UNAUTHORIZED;
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
			return HttpServletResponse.SC_UNAUTHORIZED;
		}
		
		return 200;
	}
	
}
