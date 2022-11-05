<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원관리</title>
	<script src="script/member.js"></script>
</head>
<body>

<!-- 497페이지 -->
	<h2>아이디 중복확인</h2>
	<form action="idCheck.do" method="get" name="frm">
	
		아이디 <input type=text name="userid" value="${userid}"> 
		     <input type=submit	value="중복 체크"> <br>
		
		<c:if test="${result == 1}">
			<script>
				//opener란 이창을 열어준 부모 창을 말한다. 즉 회원가입폼
				opener.document.frm.userid.value = "";
			</script>
			${userid}는 이미 사용 중인 아이디입니다.
		</c:if>
		
		<c:if test="${result==-1}">
			${userid}는 사용 가능한 아이디입니다.
			<input type="button" value="사용" class="cancel" onclick="idok('${userid}')">
		</c:if>
	
	</form>
</body>
</html>