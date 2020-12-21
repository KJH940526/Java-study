package chat.client.win;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;
	private Socket socket;
	private String nickname;
	private BufferedReader br;
	private PrintWriter pw;
	
	public ChatWindow(String name, Socket socket,String nickname) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
		this.socket=socket;
		this.nickname=nickname;
	}

	public void show() {
		/*
		 * 1. UI 초기화
		 */
		// Button
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent actionEvent ) {
				sendMessage();
			}
		});

		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("채팅방 나오기 프로토콜 구현...");
				System.exit(0);
			}
		});
		frame.setVisible(true);
		frame.pack();
		
		/*
		 * 2. IOStream 초기화
		 */
		
		
		/*
		 * 3. 쓰레드 생성
		 */
	}
	
	private void sendMessage() {
		String message = textField.getText();
		textField.setText("");
		textField.requestFocus();
		
		// 소켓을 통해 전송
		pw.println(message);
		// 소켓을 통해 메세지가 온 경우(실제로는 쓰레드에서 있어야 하는 코드...)
		new ChatClientThread(message).start();
	}

	
	public class ChatClientThread extends Thread{
		private String message;
		
		public ChatClientThread(String message) {
			this.message=message;
		}
		@Override
		public void run() {
			try {
				while(true) {
					textArea.append(br.readLine());
					textArea.append("\n");
					
					if(br.readLine()==null) {
						break;
					}
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
			finally {
				System.exit(0);
			}
			
		}
		
	}
}