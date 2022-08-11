package chatingProgram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MySocketClient {
	
	// ���� ����� ���� ���� ����
	Socket socket;

	public MySocketClient() throws Exception {
		
		// ���� ��ü ���� - ip�ּҿ� ��Ʈ�� �Է��Ѵ�
		socket = new Socket("192.168.0.85", 3000);
		
		// �������� ���� �����͸� �о���̴� �������� Ÿ���� �����Ѵ�
		ReadThread rt = new ReadThread();
		
		// Ÿ���� �����忡 �ִ´�
		Thread newWorker = new Thread(rt);
		
		// ������ ����
		newWorker.start();
		
		// ���Ͽ� �����͸� ���� bw ��ü ����
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

		// Ű����� ���� �����͸� �о���� ���� keyboardIn ����
		BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));

		String outPutMsg = "";

		// ���ξ������ ���⼭ ���ѷ���
		while ((outPutMsg = keyboardIn.readLine()) != null) {
			bw.write(outPutMsg + "\n");
			bw.flush();
		}

	}
	
	// ������ ������ ���� �� �޼��带 ������ ���ʺ� Ŭ����
	class ReadThread implements Runnable {
		
		@Override
		public void run() {
			try {
				
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				String inputMsg = "";
				
				// ������ ���� �����͸� ������ �� Ŭ���̾�Ʈ�� ������ br�� �о ����
				while ((inputMsg = br.readLine()) != null) {
					System.out.println("���� : " + inputMsg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		try {
			new MySocketClient(); // Ŭ���̾�Ʈ �޼��� ����
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}