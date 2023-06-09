<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    
    //로그인 했는지 안했는지 체크	session.setAttribute("idkey", mem_id); //키명,저장할값(id)
    String mem_id = (String)session.getAttribute("idkey");
    System.out.println("LoginSuccess.jsp 의 mem_id => " + mem_id);
    
    
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인증성공 페이지</title>
</head>
<body>
<%

	//Login.jsp  -> LoginProc.jsp -> LoginSuccess.jsp
	if(mem_id != null){ //인증된 사람이라면
%>
<b><%=mem_id %></b>님 환영합니다. <p>
당신은 제한된 기능을 사용할 수가 있습니다.<p>
<a href="Logout.jsp">로그아웃</a>
	<%} else {%>
		<script type="text/javascript">
		alert("먼저 로그인 해주세요 ")
		</script>
		
	<%} %>
</body>
</html>