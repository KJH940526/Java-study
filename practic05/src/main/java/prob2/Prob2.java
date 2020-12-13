package prob2;

public class Prob2 {
	public static String concatenate( String[] str ){
		String result = "";
		for(String concatenatedStr : str) {
        	result += concatenatedStr;
        }
		return result;
        //문자열을 결합하여 리턴하는 메소드 구현
    }

    public static void main(String args[])  {         
        String[] strArr = {"SuperMan", "BatMan", "SpiderMan"}; 
        String resultStr = concatenate( strArr );
        System.out.println( "결과 문자열 : " + resultStr ); 
    }
}
