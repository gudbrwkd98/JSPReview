<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%
//클라이언트가 서버에 항상 동일한 요청을 해도 항상 새로운내용으로 전송
 response.setHeader("Cache-Control","no-cache");//메모리X
 response.setHeader("Pragma","no-cache");//메모리에 저장X
 response.setDateHeader("Expires",0);//보관하지 마라
%>
<jsp:useBean id="memMgr" class="hewon.MemberDAO" />

<%   
    //deletePro.jsp?mem_id='kkk' passwd
    //추가
    String mem_id=request.getParameter("mem_id");//입력X
    String passwd=request.getParameter("passwd");//입력O

    System.out.println
    ("deletePro.jsp(mem_id)=>"+mem_id
                        +",passwd=>"+passwd);
    //-------------------------------------------------------
    int check=memMgr.memberDelete(mem_id, passwd);
%>
<%
  if(check==1){//회원탈퇴에 성공했다면
	  session.invalidate();//세션종료(메모리 해제)
%>
  <script>
     alert("<%=mem_id%>님 성공적으로 탈퇴처리 되었습니다.");
     location.href="Login.jsp";//로그인창으로 이동
  </script>
 <% }else{ %>
  <script>
     alert("비밀번호가 틀립니다.\n다시한번 확인하시기바랍니다.");
     history.back();//전의 페이지에서 다시 암호입력
  </script>
 <%  } %>
