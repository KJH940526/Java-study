package exception;

import java.io.IOException;

public class MyClass {
	public void danger() throws IOException, MyException {
		System.out.println("some code1");
		System.out.println("some code2");
		
		if( 10 - 5 == 5 ) {
			throw new MyException();  //던지면 뒤에꺼는 버려지기 때문에 에러가 뜸
			}
		
		if( 5 - 5 == 0 ) {
		throw new IOException();  //던지면 뒤에꺼는 버려지기 때문에 에러가 뜸
		}
		
		System.out.println("some code3");
		System.out.println("some code4");
	}

}
