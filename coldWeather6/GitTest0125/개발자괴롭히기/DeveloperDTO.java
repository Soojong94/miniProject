package 개발자괴롭히기;

//DTO: Data Transfer Object

//데이터를 저장하고 건내는 용도의 클래스
//VO: Setter 메소드를 사용하지 않는 것
//(정확하게는 이 클래스는 VO 가 맞음)

public class DeveloperDTO {

	// 필드
	private String id;
	private String pw;


	// 생성자 메소드

	public DeveloperDTO(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;

	}

	// getter 메소드


	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

}
