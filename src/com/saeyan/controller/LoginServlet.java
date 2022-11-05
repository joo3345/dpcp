package com.saeyan.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saeyan.dao.MemberDAO;
import com.saeyan.dto.MemberVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	/*	478p	
		//forward()메소드는 다른 페이지로 이동명령으로 RequestDispatcher객체로 접근해야만 사용가능하다.
		//지정 로컬 url에 대한 requestDispacher객체를 구합니다. (즉 지정한 페이지로 이동명령)
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/login.jsp");
		dispatcher.forward(request, response);
		*/
		
		//	513p. 회원정보 수정 한 후 login.do 요청할 경우. 
		//	로그인이 완료된 회원이기에 인증처리를 거치지 않고 메인 페이지로 이동
		
		String url="member/login.jsp";
		
		HttpSession session=request.getSession();
		
		if(session.getAttribute("loginUser") !=null){//이미 로그인 된 사용자이면
			url="main.jsp";//메인페이지로 이동
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//486p. 회원 인증을 위한 서블릿 클래스 만들기
		String url="member/login.jsp";
		
		String userid=request.getParameter("userid");
		String pwd =request.getParameter("pwd");
		
		MemberDAO mDao=MemberDAO.getInstance();
		int result=mDao.userCheck(userid, pwd);
		
		if(result==1) {
			MemberVO mVo=mDao.getMember(userid);
			HttpSession session=request.getSession();
			
			session.setAttribute("loginUser",mVo);
			request.setAttribute("message","로그인에 성공했습니다.");
			url="main.jsp";
			
		}else if(result==0) {
			request.setAttribute("message", "비밀번호가 맞지 않습니다.");
			
		}else if(result==-1) {
			request.setAttribute("message", "존재하지 않은 회원입니다.");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
