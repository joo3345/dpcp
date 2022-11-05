<%@page import="java.sql.Connection"%>
<%@page import="com.saeyan.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원관리를 위한 db연동 확인</title>
</head>
<body>
	<!--MemberDAO클래스의 getConnection()메소드로 커넥션 객체를 얻어내는지 확인 테스트파일  -->

	<%
		MemberDAO memDao=MemberDAO.getInstance();
		Connection conn=memDao.getConnection();
		out.println("dbcp 연동 성공");
	%>
	<br/>
	<%= conn %>
</body>
</html>