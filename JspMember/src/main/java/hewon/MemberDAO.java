package hewon;

//웹상에서 호출할 메서드를 작성(업무분석)=>DB연결된 후 호출(has a)
import java.sql.*;//DB연결
import java.util.*;//Vector,ArrayList,,,

public class MemberDAO {

	//1.멤버변수에 연결할 클래스의 객체를 선언
	private DBConnectionMgr pool=null;
	//getConnection()->Connection필요,freeConnection() 해제
	
	//1-1) 공통으로 접속할 경우(필요로하는 멤버변수)
	private Connection con=null;
	private PreparedStatement pstmt=null;//sql실행목적
	private ResultSet rs=null;//select
	private String sql="";//실행시킬 SQL구문 저장 목적
	
	//2.생성자를 통해서 자동으로 객체를 얻어올 수 있도록 연결
	public MemberDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
			System.out.println("pool=>"+pool);
		}catch(Exception e) {
			System.out.println("DB연결실패=>"+e);//e.toString()
		}
	}
   //3.요구분석에 따른 웹상에서 호출할 메서드를 작성=>flow-chart
	//1)회원로그인->id,passwd
	public boolean loginCheck(String id,String passwd) {
		//1.DB연결
		boolean check = false;
		//2.sql구문
		try {
			con=pool.getConnection();
			System.out.println("con->"+con);
			sql="select id,passwd from member where id=? and passwd=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,passwd);
			rs=pstmt.executeQuery();
			check=rs.next();
		} catch(Exception e) {
			System.out.println("login check-> 실행에러유발"+e);
		}finally {//3.메모리 해제
			pool.freeConnection(con,pstmt,rs);
		}
		return check;
	}
	
	//2)중복id를 체크
	//select id from member where id='nup';/
	public boolean checkId(String mem_id) {
		//1.DB연결
		boolean check=false;//중복 id 체크유무
				//2.sql구문
		try {
					con=pool.getConnection();
					sql="select id from member where id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1,mem_id);
					rs=pstmt.executeQuery();
					check=rs.next();
			} catch(Exception e) {
					System.out.println("checkId()-> 실행에러유발"+e);
			}finally {//3.메모리 해제
					pool.freeConnection(con,pstmt,rs);
			}
				return check;
	}
	
	//3)우편번호 검색->직접 테이블을 생성->입력->찾기, OpenAPI
	//select * from zipcode where area3 like '미아2동%';
	//public ArrayList<SangDTO>
	public ArrayList<ZipcodeDTO> zipcodeRead(String area3){
		//레코드 한개이상 담을 객체선언
		ArrayList<ZipcodeDTO> zipList=new ArrayList();
		try {
			con=pool.getConnection();
			   //select * from zipcode where area3 like '미아2동%'
		 sql="select * from zipcode where area3 like '"+area3+"%'";
		 pstmt=con.prepareStatement(sql);
		 rs=pstmt.executeQuery();
		 System.out.println("검색된 sql구문확인(sql)=>"+sql);
		 //검색된 레코드의 값을 필드별로 담는 소스코드를 작성
		 while(rs.next()) {
			 //저장된 데이터를 getter method로 불러오게 쉽게하기위해서
			 //SangDTO  sang=new SangDTO();
			 ZipcodeDTO tempZipcode=new ZipcodeDTO();
			 tempZipcode.setZipcode(rs.getString("zipcode"));
			 tempZipcode.setArea1(rs.getString("area1"));//"서울"
			 tempZipcode.setArea2(rs.getString("area2"));//null
			 tempZipcode.setArea3(rs.getString("area3"));
			 tempZipcode.setArea4(rs.getString("area4"));
			 //ArrayList에 담는 구문을 작성꼭할것.
			 zipList.add(tempZipcode);//ZipCheck.jsp->null O
		 }
		}catch(Exception e) {
			System.out.println("zipcodeRead() 실행오류=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return zipList;//ZipCheck.jsp에서 메서드호출->반환(for문)
	}
	//Register.jsp->MemberInsert.jsp(회원가입메서드호출)-성공
	//4)회원가입->insert into member values(?,?,,,)
	//          웹상에서 확인(반환값)->1 or 0 ->boolean
	//(String id,String pw,String name,,,)=>8개작성->DTO
	//DTO->1.테이블의 필드별로 저장,출력 2.매개변수자료형,반환형사용
	public boolean memberInsert(MemberDTO mem) {
		boolean check=false;//회원가입 성공유무
		
		try {
			con=pool.getConnection();
			//--트랜잭션=>오라클의 필수(p410~413)=>자동commitX
			con.setAutoCommit(false);//default->true
			sql="insert into member values(?,?,?,?,?,?,?,?)";
			//--------------------------------------------------
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,mem.getMem_id());//메모리저장
			pstmt.setString(2,mem.getMem_passwd());
			pstmt.setString(3,mem.getMem_name());
			pstmt.setString(4,mem.getMem_email());
			pstmt.setString(5,mem.getMem_phone());
			pstmt.setString(6,mem.getMem_zipcode());
			pstmt.setString(7,mem.getMem_address());
			pstmt.setString(8,mem.getMem_job());
			//1->성공,0->실패
			int insert=pstmt.executeUpdate();
			System.out.println("insert(데이터입력유무)=>"+insert);
			con.commit();
			if(insert > 0) {//if(insert==1)
				check=true;//데이터입력 성공확인
			}
		}catch(Exception e) {
			System.out.println("memberInsert()실행오류=>"+e);
		}finally {
			pool.freeConnection(con, pstmt);//rsX (select 아님)
		}
		return check;//memberInsert.jsp에서 메서드의 반환값
		                     //if(check.equals("ture")){  if(check){
	}
	//5)회원수정->특정회원찾기
	//select * from member where id = 'kkk';
	//레코드가 여러개 public ArrayList<MemberDTO>
	public MemberDTO getMember(String mem_id) {
		MemberDTO mdto = null; //id값에 해당되는 레코드 한개저장
	 
		//SangDTO sang = null;
		
		try {
			con = pool.getConnection();
			sql = "select * from member where id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id); //index ~ 에러 유발 
			rs = pstmt.executeQuery();
			//id 값에 해당되는 레코드를 찾아서 담기  -> if -> 한개이상 while(rs.next()) 
			if(rs.next()) {
				//찾은값 Setter 의 매개변수로 저장 - > 웹에 출력 Getter
				mdto = new MemberDTO();
				mdto.setMem_id(rs.getString("id"));
				mdto.setMem_passwd(rs.getString("passwd"));
				mdto.setMem_name(rs.getString("name"));
				mdto.setMem_phone(rs.getString("phone"));
				mdto.setMem_zipcode(rs.getString("zipcode"));
				mdto.setMem_address(rs.getString("address"));
				mdto.setMem_email(rs.getString("email"));
				mdto.setMem_job(rs.getString("job"));
				
			
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("회원 정보수정 에러(getMember()호출) = > " + e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		
		return mdto;
	}
	
	
	//6)찾은회원 수정 = > 회원가입해주는 메서드와 동일 (sql 구문만 다르다) 				
	public boolean memberUpdate(MemberDTO mem) {
boolean check=false;//회원가입 성공유무
		
		try {
			con=pool.getConnection();
			//--트랜잭션=>오라클의 필수(p410~413)=>자동commitX
			con.setAutoCommit(false);//default->true
			sql="update member set passwd =? , name = ?, email =?, phone =?, zipcode = ?, address = ?, job = ? where id = ? ";
			//--------------------------------------------------
			pstmt=con.prepareStatement(sql);

			pstmt.setString(1,mem.getMem_passwd());
			pstmt.setString(2,mem.getMem_name());
			pstmt.setString(3,mem.getMem_email());
			pstmt.setString(4,mem.getMem_phone());
			pstmt.setString(5,mem.getMem_zipcode());
			pstmt.setString(6,mem.getMem_address());
			pstmt.setString(7,mem.getMem_job());
			pstmt.setString(8,mem.getMem_id());//메모리저장
			//1->성공,0->실패
			int update =pstmt.executeUpdate();
			System.out.println("insert(데이터수정유무)=>"+update);
			con.commit();
			if(update > 0) {//if(insert==1)
				check=true;//데이터입력 성공확인
			}
		}catch(Exception e) {
			System.out.println("memberInsert()실행오류=>"+e);
		}finally {
			pool.freeConnection(con, pstmt);//rsX (select 아님)
		}
		return check;//memberInsert.jsp에서 메서드의 반환값
		                     //if(check.equals("ture")){  if(check){
	}
	
	
	//7)회원탈퇴
	//select passwd from member where id = 'kkk';
	//delete from member where id = 'kkk';
	public int memberDelete (String id , String passwd) {
		String dbpasswd = "" ; //DB상에서 찾은 암호를 저장 
		int x =-1; //회원탈퇴유무
		
		try {
			
			con = pool.getConnection();
			//트랜잭션처리 시작
			con.setAutoCommit(false);
			//인증처리
			sql = "select passwd from member where id = ? ";
			pstmt = con.prepareStatement(sql); // NullPointerExcecute
			pstmt.setString(1, id);// index ~ 
			rs = pstmt.executeQuery();
			//암호를 찾았다면
			if(rs.next()) {
				dbpasswd = rs.getString("passwd");
				System.out.println("dbpasswd = >  " + dbpasswd );
				//dbpasswd (DB상의 암호 )== passwd (웹상에서 입력한암호
				if(dbpasswd.equals(passwd)) {
					sql = "delete from member where id = ? ";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					int delete = pstmt.executeUpdate();
					System.out.println("delete(회원탈퇴 성공유무) => "  + delete );
					con.commit();
					x = 1 ; //회원탈퇴 성공 
				}else { //암호가 틀리다면 
					x = 0  ;
					
				}
			}
			
			
		} catch (Exception e) {
			System.out.println("memberDelete() = > " + e);
		}finally {
			pool.freeConnection(con,pstmt,rs); //암호 찾기 
		}
		return x ;
		
	}
	
	//8)회원리스트->게시판의 글목록보기=>관리자
	
	
}
