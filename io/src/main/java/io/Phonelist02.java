package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Phonelist02 {
	public static void main(String[] args) {
		Scanner scanner = null;
		
		try {
		File file = new File("phone.txt");
		System.out.println("================파일정보==================");
		System.out.println(file.getAbsolutePath());
		System.out.println(file.length() + "bytes");
		System.out.println(file.lastModified());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String sTime = sdf.format(file.lastModified());
		System.out.println(sTime);
		
		System.out.println("================전화번호==================");
		
		scanner = new Scanner(file);
			
		while(scanner.hasNextLine()) {
			//스캐너는 내부에 토크나이저가 있어서 탭,스페이스바,강제 줄바꿈 가 있음
			String name = scanner.next();
			String phone1 = scanner.next();
			String phone2 = scanner.next();
			String phone3 = scanner.next();
			
			System.out.println(name + ":" + phone1 +"-"+ phone2 +"-"+ phone3);
			}
		} catch(FileNotFoundException ex) {
			System.out.println("error:" + ex);
		} finally {
			
			if(scanner != null) {		
				scanner.close();  //new file에서 파일이 없는 경우에는 scanner가 null이기 때문에 
								  //nullpoint 에러가 발생한다
			}

		}
		
		
			
	}
}
