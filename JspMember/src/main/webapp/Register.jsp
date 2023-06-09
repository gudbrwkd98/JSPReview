<%@ page contentType="text/html;charset=UTF-8"%>
<%
//클라이언트가 서버에 항상 동일한 요청을 해도 항상 새로운내용으로 전송
 response.setHeader("Cache-Control","no-cache");//메모리X
 response.setHeader("Pragma","no-cache");//메모리에 저장X
 response.setDateHeader("Expires",0);//보관하지 마라
%>
<html>
<head>
<title>회원가입</title>
<link href="style.css?ver=1" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js">
</script>
<script>
function idCheck(id){
	   if(id==""){
		   alert("아이디를 먼저 입력하세요!");
		   document.regForm.mem_id.focus();
		   //document.폼객체명.입력양식객체명.함수명()
	   }else{//IdCheck.jsp에게 매개변수를 전달
		  url="IdCheck.jsp?mem_id="+id;  
		  //1.불러올문서명,2.창의제목,3.창의옵션(위치,크기지정)
		  open(url,"post","left=250,top=150,width=300,height=150 ");
	   }
	}
function zipCheck(){
    //check=y=>검색전의 창의 모습을 구분하는 인자(매개변수로 구분)
    url="ZipCheck.jsp?check=y";  
	  //1.불러올문서명,2.창의제목,3.창의옵션(위치,크기지정)
	  //menubar(메뉴바)status(상태바),scrollbars(스크롤기능) yes|no(출현유무)
	  open(url,"post","left=400,top=220,width=500,height=300, "+
	                "menubar=no,status=yes,toolbar=no,scrollbars=yes");
}

function inputCheck(){
	if(document.regForm.mem_id.value==""){
		alert("아이디를 입력해 주세요.");
		document.regForm.mem_id.focus();
		return;
	}
	if(document.regForm.mem_passwd.value==""){
		alert("비밀번호를 입력해 주세요.");
		document.regForm.mem_passwd.focus();
		return;
	}
	if(document.regForm.mem_repasswd.value==""){
		alert("비밀번호를 확인해 주세요");
		document.regForm.mem_repasswd.focus();
		return;
	}
	if(document.regForm.mem_name.value==""){
		alert("이름을 입력해 주세요.");
		document.regForm.mem_name.focus();
		return;
	}

	if(document.regForm.mem_email.value==""){
		alert("이메일을 입력해 주세요.");
		document.regForm.mem_email.focus();
		return;
	}
	if(document.regForm.mem_phone.value==""){
		alert("연락처를 입력해 주세요.");
		document.regForm.mem_phone.focus();
		return;
	}
	if(document.regForm.mem_job.value=="0"){
		alert("직업을 선택해 주세요.");
		document.regForm.mem_job.focus();
		return;
	}
	
	if(document.regForm.mem_passwd.value != document.regForm.mem_repasswd.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.regForm.mem_repasswd.focus();
		return;
	}
	document.regForm.submit(); 
}
</script>
</head>
<body bgcolor="#996600" onLoad="regForm.mem_id.focus()">
<br><br>
<table align="center" border="0" cellspacing="0" cellpadding="5" >
  <tr> 
    <td align="center" valign="middle" bgcolor="#FFFFCC"> 
      <table border="1" cellspacing="0" cellpadding="2"  align="center">
        <form name="regForm" method="post" action="MemberInsert.jsp">
          <tr align="center" bgcolor="#996600"> 
            <td colspan="3"><font color="#FFFFFF"><b>회원 가입</b></font></td>
          </tr>
          <tr> 
            <td width="16%">아이디</td>
            <td width="57%"> <input type="text" name="mem_id" size="15">
		      <input type="button" value="ID중복확인" 
               onClick="idCheck(this.form.mem_id.value)"></td>
            <td width="27%">아이디를 적어 주세요.</td>
          </tr>
          <tr> 
            <td>패스워드</td>
            <td> <input type="password" name="mem_passwd" size="15"> </td>
            <td>패스워드를 적어주세요.</td>
          </tr>
          <tr> 
            <td>패스워드 확인</td>
            <td> <input type="password" name="mem_repasswd" size="15"> </td>
            <td>패스워드를 확인합니다.</td>
          </tr>
          <tr> 
            <td>이름</td>
            <td> <input type="text" name="mem_name" size="15"> </td>
            <td>고객실명을 적어주세요.</td>
          </tr>
          <tr> 
            <td>이메일</td>
            <td> <input type="text" name="mem_email" size="27"> </td>
            <td>이메일을 적어주세요.</td>
          </tr>
          <tr>  
            <td>전화번호</td>
            <td> <input type="text" name="mem_phone" size="20"> </td>
            <td>연락처를 적어 주세요.</td>
          </tr>
		  <tr>  
            <td>우편번호</td>
            <td> <input type="text" name="mem_zipcode" size="7">
                 <input type="button" value="우편번호찾기" onClick="zipCheck()"></td>
            <td>우편번호를 검색 하세요.</td>
          </tr>
		  <tr>  
            <td>주소</td>
            <td><input type="text" name="mem_address" size="70"></td>
            <td>주소를 적어 주세요.</td>
          </tr>
		  <tr>  
            <td>직업</td>
            <td><select name=mem_job>
 					<option value="0">선택하세요.
 					<option value="회사원">회사원
 					<option value="연구전문직">연구전문직
 					<option value="교수학생">교수학생
 					<option value="일반자영업">일반자영업
 					<option value="공무원">공무원
 					<option value="의료인">의료인
 					<option value="법조인">법조인
 					<option value="종교,언론,에술인">종교.언론/예술인
 					<option value="농,축,수산,광업인">농/축/수산/광업인
 					<option value="주부">주부
 					<option value="무직">무직
 					<option value="기타">기타
				  </select></td>
            <td>직업을 선택 하세요.</td>
          </tr>
          <tr> 
            <td colspan="3" align="center"> 
             <input type="button" value="회원가입"   onclick="inputCheck()"> 
              &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
             <input type="reset" value="다시쓰기"> 
            </td>
          </tr>
        </form>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
