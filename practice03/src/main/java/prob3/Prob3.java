package prob3;

public class Prob3 {
	//리버스하기
	public static void main(String args[]) {
		
		char[] array1 = reverse("Hello World");
//		System.out.println(array1);
		printCharArray(array1);
		
		char[] array2 = reverse("Java Programming!");
		printCharArray(array2);
		
	}
	
	public static char[] reverse(String str){
		char[] array = new char[str.length()];
		StringBuffer sb = new StringBuffer(str);
		
		String str2 = sb.reverse().toString();
//		System.out.println(str2);
		
		for(int i=0; i<str2.length(); i++) {
//			System.out.println(str2.length());
			array[i] = str2.charAt(i);
//			System.out.println(array[i]);
		}
		return array;
	}
	
	public static void printCharArray(char[] array){
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
		}
		System.out.println();
		
	}
}
