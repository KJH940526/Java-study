package prob1;

import java.util.Scanner;

public class Prob1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// 무한 루프
		while (true) {
			int result = 0;
			System.out.print("숫자를 입력하세요 :");
			int inputNumber = scanner.nextInt();
			for (int i = 0; i <= inputNumber; i++) {
				if (inputNumber % 2 == 0 && i % 2 == 0) {
//					System.out.println(i);
					result += i;
				} else if (inputNumber % 2 != 0 && i % 2 != 0) {
//					System.out.println(i);
					result += i;
				}
			}
			System.out.println(result);
		}

	}

}
