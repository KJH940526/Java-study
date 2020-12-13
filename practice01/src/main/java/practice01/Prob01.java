package practice01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Prob01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("수를 입력하시오 : ");
		int num = scan.nextInt();
		
		try {
			if(num == 0 ) {
				System.out.println("0을 입력했습니다.");
			}else if(num%3 == 0) {
				System.out.println("3의 배수입니다.");
			}else if(num%3 != 0) {
				System.out.println("3의 배수가 아닙니다.");
			}
		}catch(InputMismatchException e){
			System.out.println("error:"+ e);
		}finally {
			scan.close();
		}
	}

}
