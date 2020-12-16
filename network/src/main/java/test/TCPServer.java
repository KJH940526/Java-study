package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			//1. 서버소켓 생성
			serverSocket = new ServerSocket();
			
			//2. 바인딩(binding): Socket Address(IP Address + Port)
			//InetSocketAddress = InetAddress(ip주소) + port가 맵핑되어 있음
			InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6000);
					
			// 소켓 서버에 ip주소와 port번호를 바인딩 한다.
			serverSocket.bind(inetSocketAddress);
//			System.out.println("Server Socket Accept 전");
			
			//3. accept  //서버 소켓에서는 클라이언트에서 생성된 소켓객체가 보낸 connect 요청을 받아줘야한다.
			Socket socket = serverSocket.accept();  // 클라이언트에서 커넥팅이 들어오기 전까지 blocking
			// socket 은 클라이언트와 통신을 직접 당담하는 소켓이 된다. 
			
//			System.out.println("Server Socket Accept 후");
			
			//그냥 주소뽑기 위해서 한 곳임
			//InetSocketAddress에는 ip주소와 port 번호를 담아야함
														//(다운캐스팅) 서버와 연결되어 있기떄문에  클라이언트의 주소와 port를 알아낼수 있다.
			InetSocketAddress remoteInetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			
			InetAddress remoteInetAddress = remoteInetSocketAddress.getAddress(); //클라이언트
			String remoteHostAddress = remoteInetAddress.getHostAddress(); // 클라이언트의 ip 주소
			int remotePort = remoteInetSocketAddress.getPort();				//클라이언트의 포트번호
			
			System.out.println("[server] connected by client[" + remoteHostAddress + ":" + remotePort + "]");
			
			
			//클라이언트가 글을 쓰면 읽는 부분
			try {
				//4. IOStream 받아 오기(생성)
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				
				while(true) { //클라이언트가 종료할떄 까지
					//5. 데이터 읽기
					byte[] buffer = new byte[256]; //바이트 단위로 읽어야하기 떄문에 추가함(이미자나 여러가지 이유로)
					int readByteCount = is.read(buffer); // blocking -> 클라이언트가 글을 써야지 읽기 떄문에
					// 데이터를 읽고 리턴해주는데 읽은 사이즈만큼 
					
					if(readByteCount == -1){
						// client가 소켓을 정상 종료
						System.out.println("[server] closed by client");
						break; // 종료가 되면 브레이크 되서 나감
					}
					
					//버퍼에서 나온것을 스트링으로 바꿔준다. -> 인코딩
					//buffer에는 바이트가 담겨져 있고, 0에서부터 readByteCount(받은 수만큼)을 UTF-8로 인코딩함
					String data = new String(buffer, 0, readByteCount, "UTF-8");
					System.out.println("[server] received:" + data);
					 
					
					//6. 데이터쓰기(응답) 소켓이랑 통신하는 아웃풋 스트림 -> 화면에다가 서버소켓이 읽은거 보내주기
					os.write(data.getBytes("UTF-8")); // 보내줄떄는 위의 스트링(data)을 다시 바이트로 인코딩해줘야함
					// 소켓은 바이트 단위로 읽고 쓰기 해야함!!
					
										
					
				} //while문 끝
				
			} catch(SocketException e) {
				System.out.println("[server] suddenly closed by client" + e);	
			} catch(IOException e) { //데이터 통신하는 소켓 예외처리	
				System.out.println("[server] error:" + e);
				
				//빠져나와서 소켓을 닫음
			} finally { 
				try {   //소켓을 닫을떄는 socket이 null이 아니거나 소켓이  닫혀있는데 또 닫은 경우!!
					if(socket != null && !socket.isClosed()) {
						socket.close();
					}
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			
			//서버소켓
		} catch (IOException e) { // 서버 소켓에 대한 예외처리
			System.out.println("[server] error:" + e);
			
		} finally {
			try {
				if(serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
