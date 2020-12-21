package chat.client.win;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientApp {
	public static final String SERVER_IP="127.0.0.1";
	public static final int PORT=7000;
	
	public static void main(String[] args) {
		String name = null;
		String nickname=null;
		Scanner nameScanner = new Scanner(System.in);
		
		Socket socket=null;
		while( true ) {
			
			System.out.println("대화명을 입력하세요.");
			System.out.print(">>> ");
			name = nameScanner.nextLine();
			
			if (name.isEmpty() == false ) {
				break;
			}
			
			System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
		}
		
		
		try {
			//1. socket 생성
			socket=new Socket();
			
			//2. connect to Server
			socket.connect(new InetSocketAddress(SERVER_IP,PORT));
			
			//3. iostream 생성
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
			
			//4. join 프로토콜 요청 및 처리
			System.out.print("닉네임>>" );
			nickname = nameScanner.nextLine();
			pw.println( "join:" + nickname );
			pw.flush();
			
			nameScanner.close();
			//5. join 프로토콜 응당이 성공이면
			//   new ChatWindow(name, socket).show();
			new ChatWindow(name,socket,nickname).show();
		}catch(IOException e) {
			System.out.println(e);
		}
		
	}

}