package exception;

public class ExceptionTest02 {
	public static void main(String[] args) {
		try {
			int[] a = { 0, 1, 2, 3, 4 };
			
			for (int i = 0; i <= a.length; i++) {  // <
				// System.out.println(a.length);
				System.out.println(a[i]);
			}
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println(ex);
		}
	}
}
