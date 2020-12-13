package practice01;

public class Prob03 {
	public static void main(String[] args) {

		int num = 99;

		for (int i = 1; i <= num; i++) {
			String s = String.valueOf(i);
			System.out.print(s + " ");
//			System.out.println(s.length());
			for (int j = 0; j < s.length(); j++) {

				char c = s.charAt(j); // ex) '6'
				
				if (c == '3' || c == '6' || c == '9') {
					System.out.print("짝");
				}
			}
			System.out.println();
		}

	}
}
