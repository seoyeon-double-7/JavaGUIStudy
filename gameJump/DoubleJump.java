package gameJump;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MenuKeyListener;

public class DoubleJump {

	private JFrame frame;

	int field = 500; // 낙하가 멈추는 지점

	ImageIcon ic = new ImageIcon("img/c3gif.gif");
	Image img = ic.getImage();

	int imgX = 500;
	int imgY = 250; // 이미지가 시작하는 Y좌표

	boolean fall = false; // 현재 떨어지는지 확인
	boolean jump = false; // 현재 점프중인지 확인

	int doubleJump = 0; // 점프 카운트(2가되면 더블점프 상태)

//	시간 가져오기
	static long getTime() {
		return Timestamp.valueOf(LocalDateTime.now()).getTime();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoubleJump window = new DoubleJump();
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

			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {

//						발바닥 위치는 이미지의 y위치 + 이미지의 높이
						int foot = imgY + img.getHeight(null);

//						발바닥이 땅보다 위에 있으면 작동
//						점프중이지 않고 공중에 있으며 떨어지는 중이 아닐 때 작동
						if (jump == false && foot < field && fall == false) {
							fall = true; // 떨어지는 중으로 전환
							System.out.println("낙하 시작");
							long t1 = getTime();
							long t2;
							int set = 1; // 처음 낙하량까지 테스트
							while (foot < field) { // 발이 땅에 닿기 전까지 반복
								t2 = getTime() - t1; // 지금 시간에서 t1을 뺀다.
								int fallY = set + (int) ((t2) / 60); // 낙하량을 늘림
								System.out.println("착지");
								
								if(foot + fallY >= field) {	//떨어지는 양이 너무크면 땅보다 아래에 갈 수 있음
									imgY = field - img.getHeight(null);	//보정해줌
									break;
								} else {
									imgY += fallY;
								}
								
								foot = imgY + img.getHeight(null); // 현재 발바닥 위치 저장
								
								repaint();
								
								if(jump == true) {	//떨어지다가 더블점프를 하면 낙하 중지
									break;
								}
								
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							fall = false;
							if(jump == false) {	//발이 땅에 닿고 점프중이 아닐 때 더블 점프 카운트를 0으로 변경
								doubleJump = 0;
							}
						}
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

			}).start();

			addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}

				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_LEFT) {
						System.out.println("왼쪽");
						if(imgX <= 0) imgX = 0;
						else imgX -= 50;						
					}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
						System.out.println("오른쪽");
						if(imgX >= 1200-img.getWidth(null)) imgX = 1200-img.getWidth(null);
						else imgX += 50;
					}else if (e.getKeyCode() == KeyEvent.VK_SPACE && doubleJump < 2) {// 스페이스 키누르고 더블점프가 2가 아닐때

						new Thread(new Runnable() {

							@Override
							public void run() {

								doubleJump++; // 점프 횟수 증가
								int nowJump = doubleJump; // 점프인지, 더블점프인지 저장

								jump = true; // 점프중으로 변경

								if (doubleJump == 1)
									System.out.println("점프");
								else if (doubleJump == 2)
									System.out.println("더블 점프");

//									발바닥 위치는 이미지의 y위치 + 이미지의 높이
								int foot = imgY + img.getHeight(null); // 발바닥위치 y좌표+이미지높이

								if (fall == false) { // 떨어지는 중이 아닐떄 점프
									jump = true;
									System.out.println("점프 시작");
									long t1 = getTime();
									long t2;
									int set = 8; // 처음 낙하량까지 테스트
									int jumpY = 8;
									while (jumpY > 0) { // 상승 높이가 0일때까지 반복
										t2 = getTime() - t1; // 지금 시간에서 t1을 뺀다.
										jumpY = set - (int) ((t2) / 60); // jump 세팅
										imgY -= jumpY; // y값 변경
										foot = imgY + img.getHeight(null); // 발바닥 위치 저장
										repaint();

										if (nowJump != doubleJump) { // 점프가 한번 더되면 점프는 멈춘다
											break;
										}

										try {
											Thread.sleep(10);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
									if (nowJump == doubleJump) {	//점프가 진짜 끝났을 때를 확인
										jump = false;
									}
								}
							}

						}).start();
					}
				}
			});

		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, imgX, imgY, this);
		}

	}

	/**
	 * Create the application.
	 */
	public DoubleJump() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}

}
