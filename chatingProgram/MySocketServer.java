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
	Vector<NewSocketThread> vt; // ��̸���Ʈ�� ��������� ���������� �������� �� queue�� �̿��Ͽ� ������� ó���Ѵ�

	public MySocketServer() throws Exception {
		vt = new Vector<>(); // �� ���� ��ü ����

		serverSocket = new ServerSocket(3000);

		while (true) {

			// socket �� �����س����Ѵ� - �޽��� �������� ������ ����ؾ� �ϱ� ����
			// ��û�� ��� - ������ �Ǹ� ������ �����ϰ� �������Ͽ� ����� ���� ���´�
			Socket socket = serverSocket.accept();
			System.out.println("��û�� ����");

			// �����带 ������ ���� ��ü ����(socket�� �޴´�)
			// ���� ������� �ڱⰡ �����ؾ��� socket�� ��ü �ȿ� ��� �ְ� �ȴ�
			NewSocketThread nt = new NewSocketThread(socket);

			// �� �����忡 nt��ü(Ÿ��)�� �ִ´�
			Thread newWorker = new Thread(nt);

			// �����带 �����Ѵ�
			newWorker.start();

			// ���͹迭�� nt��ü�� �߰��Ѵ�
			vt.add(nt);

		}
	}

	// ������ �����̾��� Ŭ���� ���ο����� ���Ŷ�� ����Ŭ���� ����
	// ���ο� �����忡�� ���۸� ������ �� �ְ� socket�� ����
	class NewSocketThread implements Runnable {

		Socket socket; // MySocketServer()���� ���������� ���� ������ ����
		BufferedReader br;
		BufferedWriter bw;

		public NewSocketThread(Socket socket) {
			this.socket = socket; // ���� ������ ����
		}

		@Override
		public void run() {
			try {

				// ���Ͽ��� �����͸� �ޱ� ���� ����
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				// ���Ͽ� �����͸� ���� ���� ����
				bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

				String msg = "";

				// ���ۿ��� ���� �����͸� msg�� �ְ� msg�� null�� �ƴϸ� ������ �����Ѵ�
				while ((msg = br.readLine()) != null) {
					System.out.println("Ŭ���̾�Ʈ : " + msg);

					// Ŭ���̾�Ʈ�鿡�� �������� ���� �޽����� �ٽ� ������
					for (NewSocketThread newSocketThread : vt) {

						// �޽����� ���� ����ڸ� �����ϰ� ������ Ŭ���̾�Ʈ�鿡�� ������
						if (newSocketThread != this) {
							newSocketThread.bw.write(msg + "\n");
							newSocketThread.bw.flush(); // ���۸� ���� ��
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
			new MySocketServer(); // ���� ���� ����
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}