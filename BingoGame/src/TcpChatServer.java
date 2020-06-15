import java.io.BufferedReader; 
import java.io.BufferedWriter; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.util.ArrayList; 
import java.util.List; 

class TcpChatServerManager { 
	private List<Socket> socketList; 

	public TcpChatServerManager() { 
		socketList = new ArrayList<Socket>(); 
	} 

	public void addSocket(Socket socket) { 
		this.socketList.add(socket); 
		new Thread(new ReceiverThread(socket)).start(); 
	} 

	class ReceiverThread implements Runnable { 
		private Socket socket; 
		// 소켓
		public ReceiverThread(Socket socket) { 
			this.socket = socket; 
		}
		@Override public void run() { 

			try { 
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
				while (true) { 
					String msg = br.readLine(); 
					System.out.println(msg); 
					Socket tmpSocket = null; 
					try { 
						for (int i = 0; i < socketList.size(); i++) { 
							tmpSocket = socketList.get(i); 
							if (socket.equals(tmpSocket)) continue; 
							BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(tmpSocket.getOutputStream())); 
							bw.write(msg + "\n"); 
							bw.flush(); 
						} 
					}catch(Exception e) { 
						System.out.println(tmpSocket.getRemoteSocketAddress() + "Connection Ended");
						socketList.remove(tmpSocket); 

						System.out.println("===============Current Participants================="); 
						for(Socket s : socketList) { 
							System.out.println(s.getRemoteSocketAddress()); 
						} 
						System.out.println("=========================================="); 
					} 
				}
			}catch (IOException e) { 
				// TODO Auto-generated catch block 
				// e.printStackTrace(); 
			} finally {
				if(socket != null) { 
					System.out.println(socket.getRemoteSocketAddress() + "Connection Ended"); 
					socketList.remove(socket); 
					System.out.println("===============Current Participants================="); 
					for(Socket s : socketList) { 
						System.out.println(s.getRemoteSocketAddress()); 
					} 
					System.out.println("=========================================="); 
				} 
			} 
		} 
	} 
}

public class TcpChatServer {
	public static void main(String[] args) { 
		TcpChatServerManager tcsm = new TcpChatServerManager();
		try {
			ServerSocket serverSocket = new ServerSocket(6000);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println(socket.getRemoteSocketAddress() + " : Connected");
				tcsm.addSocket(socket);
			} 
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	} 
}
