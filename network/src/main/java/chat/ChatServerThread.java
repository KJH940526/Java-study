package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;


public class ChatServerThread extends Thread {
	private String nickname;
	private Socket socket;
	List<Writer> listWriter;

	public ChatServerThread(Socket socket, List<Writer>listWriters) {
		this.socket = socket;
		this.listWriter = listWriters;
	}

	BufferedReader br=null;
	PrintWriter pw=null;
	
	@Override
	public void run() {

		InetSocketAddress remoteInetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
		
		InetAddress remoteInetAddress = remoteInetSocketAddress.getAddress();
		String remoteHostAddress = remoteInetAddress.getHostAddress();
		int remotePort = remoteInetSocketAddress.getPort();
		System.out.println("[server] connected by client[" + remoteHostAddress + ":" + remotePort + "]");
		
		
		try {

			//1. IOStream 생성(받아오기)
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

			// 2.요청 처리
			String request = null;
			while (true) {
				// 데이터 읽기
				request = br.readLine();
				if (request == null) {
					// client가 소켓을 정상 종료
					System.out.println("[server] closed by client");
					doQuit(pw);
					break;
				}


				String[] tokens = request.split(":");

				// 요청을 구분하는 프로토콜!!
				if ("join".equals(tokens[0])) {
					doJoin(tokens[1], pw);
					
				} else if ("message".equals(tokens[0])) {
					doMessage(tokens[1]);
					
				} else if ("quit".equals(tokens[0])) {
					doQuit(pw);
					break;
					
				} else {
					System.out.println("에러 : 알수 없는 요청(" + tokens[0] + ")");
				}

			}

		} catch (SocketException e) {
			// client가 비정상 종료
			System.out.println("[server] suddenly closed by client");
			doQuit(pw);
		} catch (IOException e) {
			System.out.println("[server] error:" + e);
			doQuit(pw);
		} finally {
			try {
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	private void doJoin(String nickName, PrintWriter pw) {
		this.nickname = nickName;

		String data = nickName + "님이 참여 하였습니다";
		broadcast(data);
		addWriter(pw);
		pw.flush();
	}
	
	private void addWriter(PrintWriter pw) {
		synchronized (listWriter) {
			listWriter.add(pw);
		}
	}
	
	private void removeWriter(Writer writer) {
		synchronized (listWriter) {
			listWriter.remove(writer);
        }
	}
	
	private void doQuit(Writer writer) {
		removeWriter(writer);
		String data=nickname + "님이 퇴장 하였습니다!";
		broadcast(data);
	}

	private void doMessage(String meg) {
		broadcast(nickname + ":"  + meg);
	}
	
	//2. 서버는 여러 클라이언트에게 동시에 메시지를 보낼 수 있는 브로드캐스팅(broadcasting) 기능이 있어야 한다
	private void broadcast(String data) {
		synchronized (listWriter) {
			for(Writer writer : listWriter) {
				PrintWriter printWriter=(PrintWriter)writer;
				printWriter.println(data);
				printWriter.flush();
			}
		}
	}	
}


