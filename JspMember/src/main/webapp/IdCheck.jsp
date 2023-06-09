<%@page import="hewon.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="memMgr" class="hewon.MemberDAO"/>
	<%
	
	//클라이언트가 서버에 항상 동일한 요청을 해도 항상 새로운 내용으로 전송
	response.setHeader("Cache-Control", "no-cache"); //메모리 x 
	response.setHeader("Pragma", "no-cache");//메모리에 저장X 
	response.setDateHeader("Expires", 0);
	
	//script.js(idCheck())->IdCheck.jsp?mem_id='nup';
	//id,passwd를 받아서 
	String mem_id = request.getParameter("mem_id");
	
	System.out.println("mem_id=>" + mem_id);
	//-> memver=> loginCheck() 호출
	boolean check = memMgr.checkId(mem_id);
	System.out.println("IdCheck.jsp 의 check = >" +  check);
	
	
	%>
<html>
<head>
<meta charset="UTF-8">
<title>중복 ID 체크 </title>
</head>
<body bgcolor="#FFFFCC">
<center>
<b><%=mem_id %></b>
<%
	if(check){ //이미 존재하는 아이디라면
		out.println("는 이미 존재하는 아이디입니다.<p>");
	}else{ //check = false (존재하는 아이디가 아니라면)
		out.println("는 사용가능한 아이디입니다.<p>");
	}
%>
	<!-- 자바스크립트에서 자기 자신의 창을 가리키는 예약어(self) -->
	<a href="#" onclick="self.close()">닫기 </a>
	</center>
</body>
</html>