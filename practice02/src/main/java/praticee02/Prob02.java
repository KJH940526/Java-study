package praticee02;

import java.util.Scanner;

public class Prob02 {	//클래스 앞에 final 있으면 상속금지!
	public static void main(String[] args) { //메소드 앞에 final이 있으면 오버라이딩 금지!
		final int GOODS_COUNT = 3; // 3으로 바꾸기 상수는 대문자가 관례
		
		Goods[] goods = new Goods[GOODS_COUNT]; //배열은 객체 => new를 썻기 때문에
		
		Scanner scan = new Scanner(System.in);
		for(int i =0; i<GOODS_COUNT; i++) {
			String info = scan.nextLine();
//			System.out.println(info);
			String[] infos = info.split(" ");  // space로 잘라냄 => 배열로 저장됨
			String name = infos[0];
			int price = Integer.parseInt(infos[1]);
			int stockCount = Integer.parseInt(infos[2]);
			goods[i] = new Goods(name, price, stockCount);
			
		}
		
		for(int i =0; i<GOODS_COUNT; i++) {
			System.out.println(goods[i].getName()+"(가격:"+goods[i].getPrice()+"원)이 "+goods[i].getStockCount()+"개 입고 되었습니다.");
			
		}
		
		
		scan.close();
	}

}
