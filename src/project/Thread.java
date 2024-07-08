package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingWorker;

// 서버 - 클라이언트가 접속할 때마다 새로운 스레드 생성함
class ServerThread extends Thread{
	static List<PrintWriter> list = Collections.synchronizedList(new ArrayList<PrintWriter>());
	static int state;
	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;
	
	// 클라이언트 소켓의 입출력 버퍼를 저장하는 생성자라고 보면 됨.
	public ServerThread(Socket socket) {
		this.socket = socket;
		
		try {
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			list.add(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			while(in != null) {
				for(int i = 0; i < 1; i++) {
					String data = in.readLine();
					
					if (data.equals("enter")) 
						add();
					else 
						sendAll(data);
					
					if(state == 2) {
						sendAll("start");
						state = 0;
						
						Timer timer = new Timer();
						TimerTask task = new TimerTask() {
							public void run() {
								ServerRandomThread random = new ServerRandomThread();
								random.start();
							}
						};
						timer.schedule(task, 8000);
					}
						
				}
			}
		} catch (IOException e) {
			System.out.println("[log: 접속이 끊어졌습니다.]");
		} finally {
			list.remove(out);
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 서버가 접속한 클라이언트에게 모두 메시지를 보낼 때 사용하는 메서드
	// 리스트인 이유는 클라이언트가 여러 개 일 수 있기 때문
	private void sendAll (String s) {
		for (PrintWriter out: list) {
			out.println(s);
			out.flush();
		}
	}
	
	public synchronized void add() {
		state += 1;
	}
}

class ServerRandomThread extends Thread{
	PrintWriter out = null;
	Socket socket;
	
	public ServerRandomThread() {
	}
	
	public void run() {
		int row, col;
		
		while(true) {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 7; j++) {
					Random random = new Random();
					row = random.nextInt(18);
					col = random.nextInt(10);
					
					String str = (i + "," + row + "," + col);
					sendRandom(str);
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
	
	private void sendRandom (String str) {
		for (PrintWriter out: ServerThread.list) {
			out.println(str);
			out.flush();
		}
	}
}

class SendThread extends Thread{
	Socket socket = null;
	private gameFrame gf;
	private gameStart gs;
	static private int flag;
	static private String msg, pid;
	
	public SendThread(Socket socket, gameFrame gf) {
		this.socket = socket;
		this.gf = gf;
		flag = 1;
	}
	
	public SendThread(Socket socket, gameStart gs) {
		this.socket = socket;
		this.gs = gs;
		flag = 2;
	}
	
	public SendThread(Socket socket, gameFrame gf, String pid) {
		this.socket = socket;
		this.gf = gf;
		this.pid = pid;
		flag = 3;
	}
	
	@Override
	public void run() {
		try {
			PrintStream out = new PrintStream(socket.getOutputStream());
			
			if(flag == 1) {
				String btn_info = gf.getData();
				out.println(btn_info);
				out.flush();
			} else if(flag == 2) {
				String client_flag = "enter";
				out.println(client_flag);
				out.flush();
			} else if(flag == 3) {
				out.println(pid);
				out.flush(); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
	}
}

class receiveThread extends Thread{
	static Socket socket;
	static gameFrame gf;
	static gameStart gs;
	static resultFrame rf;
	static int flag;
	static String data;
	
	public receiveThread(Socket socekt, gameFrame gf) {
		this.socket = socekt;
		this.gf = gf;
		flag = 1;
	}
	
	public receiveThread(Socket socekt, gameStart gs) {
		this.socket = socekt;
		this.gs = gs;
		flag = 2;
	}
	
	public receiveThread(Socket socekt, resultFrame rf) {
		this.socket = socekt;
		this.rf = rf;
		flag = 3;
	}
	
	public receiveThread(Socket socekt, gameFrame gf, int val) {
		this.socket = socekt;
		this.gf = gf;
		flag = 3;
	}
	
	public static void startThread()
    {
		SwingWorker sw = new SwingWorker() {

			@Override
			protected Object doInBackground() throws Exception {
				BufferedReader in = null;
				
				try {
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					if(flag == 1) {
						while(in != null) {
							for(int i = 0; i < 1; i++) {
								data = in.readLine();
								gf.setSeat(data);
							}
						}
					}
					else if(flag == 2) {
						data = in.readLine();
						gs.setString(data);
					}
					else if(flag == 3) {
						data = in.readLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
		sw.execute();
    }
	
	public String getString(){
		return data;
	}	
}