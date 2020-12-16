package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	private static final String SERVER_IP = "127.0.0.1";

	public static void main(String[] args) {
		
		Scanner scanner = null;
		Socket socket = null;
		try {
		   //1. 키보드 연결
			scanner = new Scanner(System.in);
			
		   //2. socket 생성
			socket = new Socket();
			
		   //3. connect
			socket.connect(new InetSocketAddress(SERVER_IP,ChatServer.PORT));
			
		   //4. IOStream 생성(받아오기)
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
			
			//5. join 프로토콜
			System.out.print("닉네임>>" );
			String nickname = scanner.nextLine();
			pw.println( "join:" + nickname );
			pw.flush();
		   
			//6. ChatClientReceiveThread 시작
			new ChatClientThread(br).start();
			
			//7. 키보드 입력 처리
			while( true ) {
				System.out.print( ">>" );
				String data = scanner.nextLine();
						
				if("quit".equals(data)) {
					// 8. quit 프로토콜 처리
					pw.print("nicname: " +nickname);
					pw.flush();
					break;
				} else {
					// 9. 메시지 처리 
					pw.println("message: "+data);
					pw.flush();
				}
			}			
			
		} catch( IOException e ) {
			System.out.println("클라이언트 IO 에러");
		} finally {
			try {
				System.out.println("종료합니다.");
				if(scanner  != null) {
					scanner.close();
				}
				if(socket != null && !socket.isClosed()) {
					socket.close();
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}

	}

}