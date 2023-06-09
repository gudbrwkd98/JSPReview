<%@page import="hewon.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="memMgr" class="hewon.MemberDAO"/>
<jsp:useBean id="mem" class="hewon.MemberDTO"/>
<jsp:setProperty name ="mem" property="*"/>
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
 													
	
	//->member=>memberInsert()호출하기위해서
	//MemberDAO memMgr = new MemberDAO();
	boolean check = memMgr.memberInsert(mem);
	
	%>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 체크 </title>
</head>
<body bgcolor="#FFFFCC">
 	<center>
 		<%
 		if(check){ //회원가입에 성공했다면
 			out.println("<b>회원가입을 축하드립니다</b>");
 			out.println("<a href=Login.jsp>로그인</a>");
 		}else{
 			out.println("<b>다시 입력하여 주십시오.</b><p>");
 			out.println("<a href=Login.jsp>다시 가입</a>");
 		}
 			 %>
 	</center>
</body>
</html>