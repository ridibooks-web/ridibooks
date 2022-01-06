package account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/controller")
public class MemberController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		
		if(path.equals("/sign.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./Member/sign.jsp");
		 }else if(path.equals("/Login.do")){
			   forward=new ActionForward();
			   forward.setRedirect(false);
			   forward.setPath("./Member/Login.jsp");
			   
		   }else if(path.equals("/MemberInsert.do")){
			   action = new MemberInsert();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		}else if(path.equals("/MemberLoginAction.do")){
			   action = new MemberLoginAction();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
	 	   }else if(path.equals("/Start.do")){
	 		   forward=new ActionForward();
			   forward.setRedirect(false);
			   forward.setPath("./Member/Start.jsp");
		   }
		   //전송방식처리  => forward방식 또는 sendRedirect방식 선택
		   if(forward.isRedirect()){
			   response.sendRedirect(forward.getPath());
		   }else{
			   RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			   dispatcher.forward(request, response);
		   }
		  
		}

}
