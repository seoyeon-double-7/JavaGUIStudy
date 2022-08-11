package chatingProgram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MySocketClient {
	
	// 소켓 통신을 위한 변수 생성
	Socket socket;

	public MySocketClient() throws Exception {
		
		// 소켓 객체 생성 - ip주소와 포트를 입력한다
		socket = new Socket("192.168.0.85", 3000);
		
		// 서버에서 보낸 데이터를 읽어들이는 쓰레드의 타겟을 생성한다
		ReadThread rt = new ReadThread();
		
		// 타겟을 쓰레드에 넣는다
		Thread newWorker = new Thread(rt);
		
		// 쓰레드 실행
		newWorker.start();
		
		// 소켓에 데이터를 보낼 bw 객체 생성
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

		// 키보드로 받은 데이터를 읽어들일 버퍼 keyboardIn 생성
		BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));

		String outPutMsg = "";

		// 메인쓰레드는 여기서 무한루프
		while ((outPutMsg = keyboardIn.readLine()) != null) {
			bw.write(outPutMsg + "\n");
			bw.flush();
		}

	}
	
	// 쓰레드 실행을 위한 런 메서드를 포함한 러너블 클래스
	class ReadThread implements Runnable {
		
		@Override
		public void run() {
			try {
				
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				String inputMsg = "";
				
				// 상대방이 보낸 데이터를 서버가 이 클라이언트로 보내면 br로 읽어서 실행
				while ((inputMsg = br.readLine()) != null) {
					System.out.println("상대방 : " + inputMsg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		try {
			new MySocketClient(); // 클라이언트 메서드 실행
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}