package praticee02;

public class Prob04 {
	public static void main(String[] args) {
			
		CurrencyConverter.setRate(1084.52);
		
		double w1 = 1000000;
		double d1 = CurrencyConverter.toDollar(w1);
		System.out.println("백만원은 " + d1 + "달러입니다.");
		
		double d2 = 100;
		double w2 = CurrencyConverter.toKWR(d2);
		System.out.println("백달러는 " + w2 + "원입니다.");
		
		//스태틱 접근하는 방법 아는지 모르는지? => 객체의 생성없이 호출 가능
		/*
		 		CurrencyConverter cu = new CurrencyConverter();
				cu.setRate(1084.52);
		 */
		
	}

}
