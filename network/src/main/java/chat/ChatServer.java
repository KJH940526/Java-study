package chat;

import java.io.IOException;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

	public static final int PORT = 7000;
	private static final String SERVER_IP = "127.0.0.1";

	public static void main(String[] args) {

		ServerSocket serverSocket = null;
		List<Writer> listWriters = new ArrayList<Writer>();

		try {

			// 1. 서버소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩(binding): Socket Address(IP Address + Port)
			serverSocket.bind(new InetSocketAddress(SERVER_IP, PORT));
			System.out.println("연결 기다림 " + SERVER_IP + ":" + PORT);

			// 3. 요청대기
			while (true) {
				Socket socket = serverSocket.accept();
				new ChatServerThread(socket, listWriters).start();
			}

		} catch (IOException e) {
			System.out.println("[server] error:" + e);
		} finally {
			try {
				if (serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}
