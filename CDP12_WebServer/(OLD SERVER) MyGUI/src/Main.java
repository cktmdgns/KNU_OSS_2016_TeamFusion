
public class Main {
	public static void main(String[] args){

		Thread desktopServerThread = new Thread(new TCPServer());
		desktopServerThread.start();
		
		//Thread desktopServerThread = new Thread(new SocketClient());
		//desktopServerThread.start();
		
		new mygui();

	}
}
