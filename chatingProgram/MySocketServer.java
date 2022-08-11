package chatingProgram;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MySocketServer {

	ServerSocket serverSocket;
	Vector<NewSocketThread> vt; // 어레이리스트와 비슷하지만 여러곳에서 동시접근 시 queue를 이용하여 순서대로 처리한다

	public MySocketServer() throws Exception {
		vt = new Vector<>(); // 새 벡터 객체 생성

		serverSocket = new ServerSocket(3000);

		while (true) {

			// socket 을 보관해놔야한다 - 메시지 보내야할 소켓을 기억해야 하기 때문
			// 요청을 대기 - 연결이 되면 소켓을 리턴하고 서버소켓에 연결된 선을 끊는다
			Socket socket = serverSocket.accept();
			System.out.println("요청이 들어옴");

			// 스레드를 돌리기 위한 객체 생성(socket을 받는다)
			// 각각 스레드는 자기가 연결해야할 socket을 객체 안에 들고 있게 된다
			NewSocketThread nt = new NewSocketThread(socket);

			// 새 스레드에 nt객체(타겟)을 넣는다
			Thread newWorker = new Thread(nt);

			// 스레드를 실행한다
			newWorker.start();

			// 벡터배열에 nt객체를 추가한다
			vt.add(nt);

		}
	}

	// 딴곳에 쓸일이없이 클래스 내부에서만 쓸거라면 내부클래스 생성
	// 새로운 스레드에게 버퍼를 연결할 수 있게 socket을 전달
	class NewSocketThread implements Runnable {

		Socket socket; // MySocketServer()에서 서버소켓이 받은 소켓을 저장
		BufferedReader br;
		BufferedWriter bw;

		public NewSocketThread(Socket socket) {
			this.socket = socket; // 받은 소켓을 저장
		}

		@Override
		public void run() {
			try {

				// 소켓에서 데이터를 받기 위한 버퍼
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				// 소켓에 데이터를 쓰기 위한 버퍼
				bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

				String msg = "";

				// 버퍼에서 받은 데이터를 msg로 넣고 msg가 null이 아니면 내용을 실행한다
				while ((msg = br.readLine()) != null) {
					System.out.println("클라이언트 : " + msg);

					// 클라이언트들에게 누군가가 보낸 메시지를 다시 보낸다
					for (NewSocketThread newSocketThread : vt) {

						// 메시지를 보낸 당사자를 제외하고 나머지 클라이언트들에게 보낸다
						if (newSocketThread != this) {
							newSocketThread.bw.write(msg + "\n");
							newSocketThread.bw.flush(); // 버퍼를 비우는 것
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		try {
			new MySocketServer(); // 소켓 서버 가동
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}