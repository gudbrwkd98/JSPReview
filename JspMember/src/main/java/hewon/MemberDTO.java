package hewon;//기능별로 분리=>패키지역할(회원,설문조사,고객,,,)

/*
 * 웹상에서 입력받은 데이터를 테이블의 필드에 1:1 대응에 맞게
 * 저장할 수 있도록 설계된  클래스(=데이터 저장빈) DTO=>VO(스프링)
 */
public class MemberDTO {

	//입력받은 갯수만큼 멤버변수를 선언
	//input type="text" name="mem_id"
	private String mem_id;//회원id
	private String mem_passwd;//암호
	private String mem_name;//이름
	private String mem_email;//이메일
	private String mem_phone;//전번
	private String mem_zipcode;//우편번호
	private String mem_address;//주소지
	private String mem_job;//직업
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_passwd() {
		return mem_passwd;
	}
	public void setMem_passwd(String mem_passwd) {
		this.mem_passwd = mem_passwd;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public String getMem_zipcode() {
		return mem_zipcode;
	}
	public void setMem_zipcode(String mem_zipcode) {
		this.mem_zipcode = mem_zipcode;
	}
	public String getMem_address() {
		return mem_address;
	}
	public void setMem_address(String mem_address) {
		this.mem_address = mem_address;
	}
	public String getMem_job() {
		return mem_job;
	}
	public void setMem_job(String mem_job) {
		this.mem_job = mem_job;
	}
}
