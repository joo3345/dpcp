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
<!-- 491p -->
		<h2> ▶ 회원가입</h2>
	
		<p>'*' 표시 항목은 필수 입력 항목입니다.</p>
		<form method="post" action="join.do" name="frm" >
			<table>
				<tr>
					<th><label for="userName">이름</label>  </th>
					<td> <input type="text" name="name" size="20" id="userName">*</td>
				</tr>
				
				<tr>
					<th> <label for="userid">아이디</label> </th>
					<td> <input type="text" name="userid" size="20" id="userid">*
						 <input type="hidden" name="reid" size="20">
						 <input type="button" value="중복 체크" onclick="return idCheck()">
					</td>
				</tr>
				
				<tr>
					<th> <label for="userpwd1">암호</label> </th>
					<td> <input type="password" name="pwd" size="20" id="userpwd1">*</td>
				</tr>
				
				<tr>
					<th> <label for="userpwd2">암호확인</label> </th>
					<td> <input type="password" name="pwd_check" size="20" id="userpwd2">*</td>
				</tr>
				
				<tr>
					<th> <label for="userMail">이메일</label> </th>
					<td> <input type="text" name="email" size="20" id="userMail"></td>
				</tr>
				
				<tr>
					<th> <label for="userPhone">전화번호</label> </th>
					<td> <input type="text" name="phone" size="20" id="userPhone"></td>
				</tr>
				
				<tr>
					<th> <label>등급</label> </th>
					<td> <input type="radio" name="admin" value="0" checked > 일반회원 &nbsp;
						 <input type="radio" name="admin" value="1"  > 관리자
					</td>
				</tr>
				
				</table>
				
				<div class="btnWrap">
					<input type="submit" value="전송" onclick="return joinCheck()">
					<input type="reset" value="취소"> 
					<p>${message}</p>
				</div>
		</form>
		
	
</body>
</html>