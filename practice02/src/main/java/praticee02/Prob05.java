package praticee02;

import java.util.Scanner;

public class Prob05 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("두 정수와 연산자를 입력하시오 >>");
		String line = scan.nextLine();
		String[] tokens = line.split(" "); // " "로 나눠서 배열에 쇽쇽

		int lValue = Integer.parseInt(tokens[0]);
		int rValue = Integer.parseInt(tokens[1]);
		String operator = tokens[2];
		int result = 0;
		switch (operator) {
		case "+": {
			Add add = new Add();
			add.setValue(lValue, rValue);
			result = add.calculate();
			System.out.println(result);
			break;
		}
		case "-": {
			Sub sub = new Sub();
			sub.setValue(lValue, rValue);
			result = sub.calculate();
			System.out.println(result);
			break;
		}
		case "*": {
			Mul mul = new Mul();
			mul.setValue(lValue, rValue);
			result = mul.calculate();
			System.out.println(result);
			break;
		}
		case "/": {
			Div Div = new Div();
			Div.setValue(lValue, rValue);
			result = Div.calculate();
			System.out.println(result);
			break;
		}
		default:
			System.out.println("연산자가 아닙니다.");
			break;
		}
		scan.close();

	}

}
