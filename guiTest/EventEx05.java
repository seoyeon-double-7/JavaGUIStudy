package guiTest;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class EventEx05 extends JFrame {

	private JLabel la; // new �� �����ڿ��� ����
	int nowY = 0;
	Container c;

	public EventEx05() {

		la = new JLabel("Hello");
		setTitle("MouseEvent ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		c.addMouseListener(new MyMouseListener());
		c.setLayout(null); // absolute ���̾ƿ�
		la.setSize(50, 20); // ���� ������
		la.setLocation(30, 30); // ���� ��ġ
		c.add(la);
		setSize(250, 250);
		setVisible(true); // paint()

	}

	class MyMouseListener extends MouseAdapter {

		// ���콺�� Ŭ���ϰ� ������ ��
		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX(); // x ��ǥ
			int y = e.getY(); // y ��ǥ
			nowY = la.getY();

			if (la.getY() < y) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						while (la.getY() < y) {
							// setLocation�� repaint�� ��� �ִ�
							la.setLocation(x, nowY + 1); // �� ��ġ ����
							nowY = la.getY();

							try {
								Thread.sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}).start();

			} else if (la.getY() > y) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						while (la.getY() > y) {
							la.setLocation(x, nowY - 1); // �� ��ġ ����
							nowY = la.getY();

							try {
								Thread.sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}).start();
			}
		}
	}

	public static void main(String[] args) {
		new EventEx05();
	}
}
