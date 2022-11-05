<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원관리</title>
	<script src="script/member.js"></script>
</head>
<body>
<!-- 474p -->	
	<h2>로그인 </h2>
	
	<form method="post" action="login.do" name=frm >
		<table>
			<tr>
				<th><label for="userid"> 아이디 : </label></th>
				<td><input type="text" name="userid" id="userid" value="${userid}" ></td>
			</tr>
			
			<tr>
				<th><label for="userpwd"> 암 &nbsp; 호 : </label></th>
				<td><input type ="password" name="pwd" id="userpwd"  "></td>
			</tr>
			
		</table>
		<p class="btnWrap">
			<input type="submit" value="로그인"  onclick="return loginCheck()">&nbsp;&nbsp;
			<input type="reset" value="취소">&nbsp;&nbsp;
			<input type="button" value="회원가입" onclick="location.href='join.do'">
		</p>
		<p>${message}</p>
	</form>
		
</body>
</html>