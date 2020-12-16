package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class TCPClient {
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 6000;
	
	public static void main(String[] args) {
		Socket socket = null;
		 
		try {
			//1. 소켓 생성
			socket = new Socket();
			
			//2. 서버 연결
			socket.connect(new InetSocketAddress(SERVER_IP,SERVER_PORT));
			System.out.println("[client] connected!");
			
			//3. IOStream 받아오기
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			//4. 쓰기 //서버와 다르게 클라이언트는 쓰고 읽기!!  서버는 연결후 읽기에서 블락킹중!!
			String data = "Hello World";
			os.write(data.getBytes("UTF-8"));

			//5. 읽기
			byte[] buffer = new byte[256];
			int readByteCount = is.read(buffer);
			
			if(readByteCount == -1) {
				// server 정상 종료
				System.out.println("[client] closed by server");
				return; //이건 while 문이 아니기떄문에!! if를 빠져나옴
			}
			
			data = new String(buffer, 0, readByteCount, "UTF-8");
			System.out.println("[clinet] received:" + data);
			
		} catch(SocketException e) {
			// server 비정상 종료
			System.out.println("[clinet] suddenly closed by server");
			
		} catch (IOException e) {
			System.out.println("[client] error:" + e);
		} finally {
			try {
				if(socket != null && !socket.isClosed()) {
					socket.close();
					System.out.println("클라이언트 소켓 종료");
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
