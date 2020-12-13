package prob5;

public class MyBase extends Base {
	//이놈만 수정할꺼 오버라이드밖에 없음
	
	@Override
	public void service(String state) {
		if (state.equals("낮")) {
			day();
		} else if (state.equals("오후")) {
			afternoon();
		} else {
			night();
		}
	}

	@Override
	public void day() {
		System.out.println("낮에는 열심히 일하자!");
	}
	
	public void afternoon() {
		System.out.println("오후도 낮과 마찬가지로 일해야 합니다.");
	}

}
