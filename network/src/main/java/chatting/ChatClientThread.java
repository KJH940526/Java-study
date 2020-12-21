package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ChatClientThread extends Thread {

	BufferedReader br;

	public ChatClientThread(BufferedReader br) {
		this.br = br;
	}

	@Override
	public void run() {
		String data;
		
		while (true) {
			try {
				data = br.readLine();
				if (data == null) {
					// server가 소켓을 정상 종료
					System.out.println("[client] closed by server");
					return;
				}
				System.out.println(data);

			} catch (IOException e) {
				System.out.println(" 쓰레드 [Client] error : " + e);
				System.exit(0); //쓰레드를 죽이고 자바 강제종료
			}
				
		}

	}

}
