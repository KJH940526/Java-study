package prob6;

public class Prob6 {

	public static void main(String[] args) {
		Employee d = new Depart(); //기본 생성자
		
		Employee pt = new Depart("홍길동", 3000, "기획부");
		
		pt.getInformation();

	}

}
