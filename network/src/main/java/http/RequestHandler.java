package http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;

public class RequestHandler extends Thread {
	private static final String DOCUMENT_ROOT = "./webapp";
	private Socket socket;

	public RequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// get IOStream
			OutputStream outputStream = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

			
			// logging Remote Host IP Address & Port
			InetSocketAddress inetSocketAddress = ( InetSocketAddress )socket.getRemoteSocketAddress();
			consoleLog( "connected from " + inetSocketAddress.getAddress().getHostAddress() + ":" + inetSocketAddress.getPort() );

			String request = null;
			while(true) {
				String line = br.readLine();
				
				// 브라우저가 연결을 끊음
				if(line == null){
					consoleLog("closed by client");
					break;
				}
				
				// Request Header의 첫번째 라인만 읽음
				if(request == null) {
					request = line;
					break;
				}
			}

				consoleLog(request);

				String[] tokens = request.split(" ");
				if("GET".equals(tokens[0])) {
					responseStaticResource(outputStream, tokens[1], tokens[2]);
				} else { // POST, DELETE, PUT, HEAD, CONNECT

					// HTTP/1.1 400 Bad Request\r\n
					// Content-Type:text/html; charset=utf-8\r\n
					// \r\n //개행처리
					// html 에러 문서(./webapp/error/400.html)

					// response400Error();
				}
		} catch (Exception ex) {
			consoleLog("error:" + ex);
		} finally {
			// clean-up
			try {
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}

			} catch (IOException ex) {
				consoleLog("error:" + ex);
			}
		}
	}

	public void responseStaticResource(OutputStream outputStream, String uri, String protocol) throws IOException {
		if ("/".equals(uri)) {
			uri = "/index.html";
		}

		File file = new File(DOCUMENT_ROOT + uri);
		if (!file.exists()) {
			// HTTP/1.1 404 Not Found\r\n
			// Content-Type:text/html; charset=utf-8\r\n
			// \r\n
			// html 에러 문서(./webapp/error/404.html)

			// response404Error();
			return;
		}

		// 파일이 있다는 뜻!!
		// nio. //예외처리 해줘야는데 위에 throws를 해줌 이걸 호출하는 곳에서 예외처리해야함
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());

		// response
		outputStream.write((protocol + " 200 OK\r\n").getBytes("UTF-8"));
		outputStream.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes("UTF-8"));
		outputStream.write("\r\n".getBytes("UTF-8"));
		outputStream.write(body);
	}
	
	public void response400Error(OutputStream outputStream) throws IOException{
		File file = new File(DOCUMENT_ROOT +"/error/400.html");
		// nio
		byte[] body = Files.readAllBytes(file.toPath());
		
		// response
		outputStream.write("HTTP/1.1 400 Bad Request\r\n".getBytes("UTF-8"));
		outputStream.write("Content-Type:text/html; charset=utf-8\r\n".getBytes("UTF-8"));
		outputStream.write("\r\n".getBytes("UTF-8"));
		outputStream.write(body);
	}
	
	public void response404Error(OutputStream outputStream) throws IOException{
		File file = new File(DOCUMENT_ROOT +"/error/404.html");
		
		byte[] body = Files.readAllBytes(file.toPath());
		
		outputStream.write("HTTP/1.1 404 Not Found\r\n".getBytes("UTF-8"));
		outputStream.write("Content-Type:text/html; charset=utf-8\r\n".getBytes("UTF-8"));
		outputStream.write("\r\n".getBytes("UTF-8"));
		outputStream.write(body);
	}
	

	public void consoleLog(String message) {
		System.out.println("[RequestHandler#" + getId() + "] " + message);
	}
}