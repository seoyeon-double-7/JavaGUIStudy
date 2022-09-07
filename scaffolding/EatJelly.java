package scaffolding;

import java.awt.*;
//import java.awt.AlphaComposite;
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import scaffolding.FadeInOut.MyPanel;

public class EatJelly {

	ImageIcon jellyIc = new ImageIcon("img/jelly1.png");
	ImageIcon effIc = new ImageIcon("img/effectTest1.png");

	Jelly j1;
	Jelly j2;

	// 투명도 조절을 위한 변수
	private AlphaComposite alphaComposite;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EatJelly window = new EatJelly();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	class MyPanel extends JPanel {

		public MyPanel() {
			setFocusable(true);
			
//			패널에 젤리 추가
//			이미지, 시작 좌표 크기, 투명도 설정
			j1 = new Jelly(jellyIc.getImage(), 500, 150, 20, 20, 255);
			j2 = new Jelly(jellyIc.getImage(), 600, 150, 20, 20, 255);
			
			
			new Thread(new Runnable() {

				@Override
				public void run() {
					
					while(true) {
//						젤리들을 왼쪽으로 이동시킨다.
						j1.setX(j1.getX() - 1);
						j2.setX(j2.getX() - 1);
						System.out.println("젤리 이동중!");

//						젤리가 범위 안에 들어오면 이미지를 바꾸고 알파값을 줄인다.
						if (j1.getX() < 300) {
							j1.setImage(effIc.getImage());

							if (j1.getAlpha() > 1) {
								j1.setAlpha(j1.getAlpha() - 2);
							}
						}

						if (j2.getX() < 300) {
							j2.setImage(effIc.getImage());

							if (j2.getAlpha() > 1) {
								j2.setAlpha(j2.getAlpha() - 2);
							}
						}

						repaint();

						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}

			}).start();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			
//			검은 배경
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

//			흰색 선
			g.setColor(Color.WHITE);
			g.drawLine(300, 0, 300, this.getHeight());

//			알파값 조절
			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) j1.getAlpha() / 255);
			g2.setComposite(alphaComposite);

//			젤리 1 그리기
			g.drawImage(j1.getImage(), j1.getX(), j1.getY(), j1.getWidth(), j1.getHeight(), null);

			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) j2.getAlpha() / 255);
			g2.setComposite(alphaComposite);

//			젤리 2 그리기
			g.drawImage(j2.getImage(), j2.getX(), j2.getY(), j2.getWidth(), j2.getHeight(), null);
		}
	}

	/**
	 * Create the application.
	 */
	public EatJelly() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}

}
