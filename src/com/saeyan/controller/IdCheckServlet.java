package com.saeyan.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.MemberDAO;


@WebServlet("/idCheck.do")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//496p
		//자바스크립트로 아이디 중복 체크를 위한 함수를 추가하였기에 
		//<중복체크>버튼 클릭시 idDheck.do가 요청 
		String userid=request.getParameter("userid");
		
		MemberDAO mDao = MemberDAO.getInstance();
		
		int result=mDao.confirmID(userid);
		
		request.setAttribute("userid", userid);
		request.setAttribute("result", result); //중복값이 있으면 1, 없으면 -1
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("member/idcheck.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
