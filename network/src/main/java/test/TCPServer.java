package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String[] args) {
		ServerSocket serverSocket = null; // 변수를 밖으로 빼줌
		try {
			// 1. 서버소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩(binding): 소켓에 Socket Address(IP Address + Port)를 묶음
			serverSocket.bind(new InetSocketAddress("127.0.0.1", 9000));

			// 3. accept
			Socket socket = serverSocket.accept(); // 허락을 수용할때까지 blocking이 생김 => 수용되면 통신용 소켓이 생김
//			System.out.println("blocking");

			InetSocketAddress remoteInetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();

			InetAddress remoteInetAddress = remoteInetSocketAddress.getAddress();
			String remoteHostAddress = remoteInetAddress.getHostAddress();
			int remoteport = remoteInetSocketAddress.getPort();

			System.out.println("[Server] conneted by client[" + remoteHostAddress + ":" + remoteport + "]");

			try {
				// 4. IOStream 받아오기
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				while (true) {
					// 5. 데이터 읽기
					byte[] buffer = new byte[256]; // 정상종료가 아닌 이상 무한루프지만 여기서 블락킹이 생겨서 계속 돌지 않음
					int readByteCount = is.read(buffer); // blocking이 생김 => 글이 쓰기전까지

					if (readByteCount == -1) {
						// Client가 소켓을 정상적으로 종료했다는 뜻임 -> socket.close()를 정상적으로 부름
						System.out.println("[Server] closed by client");
						break;
					}
					// 인코딩
					String data = new String(buffer, 0, readByteCount, "UTF-8");
					System.out.println("[Server] received:" + data);

					// 6. 데이터쓰기 //소켓은 byte 단위로 읽고 쓰기 해야함
					os.write(data.getBytes("UTF-8"));
				}

			} catch (IOException e) { // 소켓에 대한 예외처리 => socket 스트림에 대한 예외처리
				System.out.println("[Server] error: " + e);
			} finally {
				try {
					if (socket != null && !socket.isClosed())
						socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) { // IO이기 때문에 예외처리해줘야함
			System.out.println("[Server] error: " + e); // 서버 소켓에 대한 예외처리
		} finally {
			try {
				if (serverSocket != null && serverSocket.isClosed() == false)
					serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
