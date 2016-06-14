import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class SocketClient {
	
	HashMap<String, DataOutputStream> clients;
	private ServerSocket ServerSocket = null;
	
	public SocketClient() {

		// 연결부 hashmap 생성자(Key, value) 선언
		clients = new HashMap<String, DataOutputStream>();
		// clients 동기화
		Collections.synchronizedMap(clients);
	}

	void start() {

		// Port 값은 편의를위해 5001로 고정 (Random값으로 변경가능)
		int port = 9999
				;
		Socket socket = null;

		try {
			// 서버소켓 생성후 while문으로 진입하여 accept(대기)하고 접속시 ip주소를 획득하고 출력한뒤
			// MultiThread를 생성한다.
			ServerSocket = new ServerSocket(port);
			System.out.println("접속대기중");
			while (true) {
				socket = ServerSocket.accept();
				InetAddress ip = socket.getInetAddress();
				System.out.println(ip + "  connected");
				new MultiThread(socket).start();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	class MultiThread extends Thread {

		Socket socket = null;

		String mac = null;
		String msg = null;

		DataInputStream input;
		DataOutputStream output;

		public MultiThread(Socket socket) {
			this.socket = socket;
			try {
				// 객체를 주고받을 Stream생성자를 선언한다.
				input = new DataInputStream(socket.getInputStream());
				output = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
			}
		}

		public void run() {

			try {
				// 접속된후 바로 Mac 주소를 받아와 출력하고 clients에 정보를 넘겨주고 클라이언트에게 mac주소를보낸다.
				mac = input.readUTF();
				System.out.println("Mac address : " + mac);
				clients.put(mac, output);
				sendMsg(mac + "   접속");

				// 그후에 채팅메세지수신시
				while (input != null) {
					try {
						String temp = input.readUTF();
						sendMsg(temp);
						System.out.println(temp);
					} catch (IOException e) {
						sendMsg("No massege");
						break;
					}
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}

		// 메세지수신후 클라이언트에게 Return 할 sendMsg 메소드
		private void sendMsg(String msg) {

			// clients의 Key값을 받아서 String 배열로선언
			Iterator<String> it = clients.keySet().iterator();

			// Return 할 key값이 없을때까지
			while (it.hasNext()) {
				try {
					OutputStream dos = clients.get(it.next());
					// System.out.println(msg);
					DataOutputStream output = new DataOutputStream(dos);
					output.writeUTF(msg);

				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}
}