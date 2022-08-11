package guiTest;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class EventEx05 extends JFrame {

	private JLabel la; // new 는 생성자에서 하자
	int nowY = 0;
	Container c;

	public EventEx05() {

		la = new JLabel("Hello");
		setTitle("MouseEvent 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		c.addMouseListener(new MyMouseListener());
		c.setLayout(null); // absolute 레이아웃
		la.setSize(50, 20); // 라벨의 사이즈
		la.setLocation(30, 30); // 라벨의 위치
		c.add(la);
		setSize(250, 250);
		setVisible(true); // paint()

	}

	class MyMouseListener extends MouseAdapter {

		// 마우스를 클릭하고 놓았을 때
		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX(); // x 좌표
			int y = e.getY(); // y 좌표
			nowY = la.getY();

			if (la.getY() < y) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						while (la.getY() < y) {
							// setLocation은 repaint를 들고 있다
							la.setLocation(x, nowY + 1); // 라벨 위치 변경
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
							la.setLocation(x, nowY - 1); // 라벨 위치 변경
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
