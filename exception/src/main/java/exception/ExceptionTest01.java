package exception;

public class ExceptionTest01 {
	public static void main(String[] args) {
		int i = 10; //11로 바꾸기  // some code 1,3만 나옴!!
		try {
				System.out.println("some codes...");
				int k = 9999 / (i - 10);
				System.out.println("some codes...2");
		} catch(ArithmeticException ex) {
			//1.사과
			System.out.println("미안합니다. 프로그램이 예기....");
			
			//2. 로그 남기기
			System.out.println(ex);
			
			//3. 정상종료
			return; 
			
		} finally { //catch에서 리턴을 만나도 실행된다.
			System.out.println("some codes...3");
		}
		
		System.out.println("some codes...4");
	}
}
