package guiTest02;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class BackMove {

	ImageIcon backIc = new ImageIcon("img/back1.png");
	Image backImg = backIc.getImage();
	
//	1��° �̹���
	int back1X = 0;
	
//	2���� �̹����� �ڵ��� �;��ϹǷ� backImg�� ���̸� �����´�.
	int back2X = backImg.getWidth(null);
	
	private JFrame frame;


	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BackMove window = new BackMove();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	class MyPanel extends JPanel{
		public MyPanel() {
			
			setFocusable(true);
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						back1X--;
						back2X--;
						
//						�̹����� ȭ�� ������ ������ ������
//						X���� �̹����� ���� ��ǥ�� �ٽ� �ű��.
//						1�� �̹����� ���� ������ 2�� �ڿ� �ٰ�
//						2�� �̹����� ������ �ٽ� 1�� �ڿ� �Ѵ� ��.
						
						if(back1X < -(backImg.getWidth(null))) {
							back1X = backImg.getWidth(null);
						}
						if(back2X < -(backImg.getWidth(null))) {
							back2X = backImg.getWidth(null);
						}
						
						repaint();
						try {
							Thread.sleep(10);
						}catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}

		@Override
		protected void paintComponent(Graphics g) {	//�׸� �׷��ִ� �޼���
			super.paintComponent(g);	//ĵ������ ����ִ� �޼���
			
			g.drawImage(backImg, back1X, 0, this);	//1�� �׸�
			g.drawImage(backImg, back2X, 0, this);	//2�� �׸�
		}
	}


	public BackMove() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setTitle("swing ���ȭ�� �帣��");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}

}
