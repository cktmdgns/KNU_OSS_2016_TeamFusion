
public class Main {
	public static void main(String[] args){
			
			Thread desktopServerThread = new Thread(new TCPServer());
	        desktopServerThread.start();
			
			new mygui();
	        
	}
}
