package guiTest02;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class ThreadSwing1 {

	private JFrame frame;

	ImageIcon ic = new ImageIcon("img/z.png"); // 이미지 아이콘 객체 생성
	Image img = ic.getImage();

	private int imgX = 50;
	private int imgY = 50;
	
	private MyPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThreadSwing1 window = new ThreadSwing1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	class MyPanel extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, imgX, imgY, this);
		}

		public MyPanel() {
			setFocusable(true); // 입력 포커스 받기

			Thread nt = new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {

						repaint(); // 그림 다시그리기
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			nt.setDaemon(true);//스레드를 데몬으로 만들어야 메인을 종료할 때 같이 종료된다.
			nt.start(); // 스레드 실행

			addMouseMotionListener(new MouseAdapter() { // 드래그-> 모션리스너

				@Override
				public void mouseDragged(MouseEvent e) {
					imgX = e.getX(); // 마우스의 좌표를 받아서 이미지 좌표에 저장
					imgY = e.getY();
					System.out.println(e.getX());
				}

			});
		}

	}

	/**
	 * Create the application.
	 */
	public ThreadSwing1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}

}
