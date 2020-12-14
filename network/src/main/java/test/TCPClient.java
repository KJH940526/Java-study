package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient {
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 9000;

	public static void main(String[] args) {
		Socket socket = null;
		try {

			// 1.소켓 생성
			socket = new Socket();

			// 2.서버 연결
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			System.out.println("[Client] connect!");
			
			// 3. IOStream 받아오기
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			// 4. 쓰기
			String data = "Hello World";
			os.write(data.getBytes("UTF-8")); // 소켓통신은 바이트로
			
			// 5. 읽기
			byte[] buffer = new byte[256];
			int readByteCount = is.read(buffer);
			
			if(readByteCount == -1) { //서버가 끝었을 때 => server 정상종료(socket.close) => 정상종료가 아닌 경우 예외처리가 발생
				System.out.println("[Client] closed by server");
				return;
			}
			
			data = new String(buffer, 0, readByteCount, "UTF-8");
			System.out.println("[Client] received : " + data);
			
		} catch (IOException e) {
			System.out.println("[Client] error : " + e);
		} finally {
			if (socket != null && !socket.isClosed())
				try {
//					System.out.println("닫을꺼임");
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

}
