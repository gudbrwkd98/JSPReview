function loginCheck(){
	if(document.login.mem_id.value==""){
		alert("아이디를를 입력해 주세요.");
		document.login.mem_id.focus();
		return; //return false;
	}
	if(document.login.mem_passwd.value==""){
		alert("비밀번호를 입력해 주세요.");
		document.login.mem_passwd.focus();
		return;
	}
	document.login.submit(); //document.폼객체명.submit();
}

function memberReg(){
	document.location = "agreement.jsp";
}
 


//중복ID체크 해주는 자바스크립트함수 선언
function idCheck(id){
    if(id==""){
		alert("아이디를 먼저 입력하세요!")
		document.regForm.mem_id.focus();
		//document.폼객체명.입력양식객체명.함수명()
	}else{ //IdCheck.jsp 에게 매개변수를 전달 
		url = "IdCheck.jsp?mem_id="+id;
		//1.불러올 문서명, 2.창의 제목, 3.창의 옵션(위치,크기지정)
		open(url,"post",'width=400, height=300');																			
	}
}
//우편번호를 검색해주는 함수선언
function zipCheck(){
    
    // check =  y = > 검색 전의 창의 모습을 구분하는 인자 (매개변수로 구분)
    
    url = "ZipCheck.jsp?check=k" ;
    
    //1.불러올 문서명 , 2 .창의 제목 , 3.창의 옵션 (위치,크기지정)
    open(url,"post","left =400,top=220,width=500,height=300,menubar = no,status=yes, toolbar = no ,scrollbars=yes");
    
    
     
    
    
    
    
    
    
    
    
}
