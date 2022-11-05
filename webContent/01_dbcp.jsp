<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>db연동 생성</h3>
   <%
    Context initContext = new InitialContext();  // 네이밍 조작을 위한 클래스 객체생성
    Context envContext  = (Context)initContext.lookup("java:/comp/env");	// 지정한 이름 찾기
    DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");		// server.xml파일에서 지정한 이름 찾기
    Connection conn = ds.getConnection();
    out.println("DBCP 연동 성공");
    %>
    <br/>
    
    <%= conn %>
</body>
</html>