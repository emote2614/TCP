package project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}
	private void start() {
		ServerSocket serverSock = null;
		Socket socket = null;
		
		try {
			serverSock = new ServerSocket(9005);
			
			while(true) {
				System.out.println("[클라이언트 접속 대기중...]");
				socket = serverSock.accept();
				
				ServerThread serverThread = new ServerThread(socket);
				serverThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("[서버 종료]");
				socket.close();
				serverSock.close();
			} catch (IOException e) {
				System.out.println("[서버 통신 에러]");
			}
		}
	}
}
