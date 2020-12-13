package praticee02;

public class CurrencyConverter {
	private static double rate;
	
	public static void setRate(double r) {
		rate = r;
	}

	public static double toDollar(double won) {
		return won/rate;
		// 한국 원화를 달러로 변환
	}

	public static double toKWR(double dollar) {
		return dollar*rate;
		// 달러를 한국 원화로 변환
	}

}
