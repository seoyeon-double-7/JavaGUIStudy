package scaffolding;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Foots {

	private JFrame frame;

	ImageIcon ic = new ImageIcon("img/c2gif.gif");
	Image img = ic.getImage();

	int field = 500;
	
	int imgX = 100;
	int imgY = 250; // 이미지가 시작하는 Y좌표

	boolean fall = false; // 현재 떨어지는지 확인
	boolean jump = false; // 현재 점프중인지 확인

	int doubleJump = 0; // 점프 카운트(2가되면 더블점프 상태)

//	시간 가져오기
	static long getTime() {
		return Timestamp.valueOf(LocalDateTime.now()).getTime();
	}

//	1이면 발판이 있고 0이면 없다
	String fieldStr = "01111111110011111110110111110111110011111111011111111101111111101111111111110"
			+ "1111111110011111110110111110111110011111111011111111101111111101111111111110"
			+ "1111111110011111110110111110111110011111111011111111101111111101111111111110";
	List<Foot> fieldList = new ArrayList<>(); // 발판 객체를 담을 리스트

	int count = 0; // 발판 확인 변수

	int nowField = field; // 캐릭터 높이에 따른 발판 위치 조정 변수

	ImageIcon landic = new ImageIcon("img/field1.png");
	Image landimg = landic.getImage();

	static int getGround(String ground, int index) {
		return Integer.parseInt(ground.substring(index, index + 1));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Foots window = new Foots();
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

//			repaint만 전담하는 쓰레드
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						repaint();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
			}).start();

//			발판을 왼쪽으로 이동시키는쓰레드 (발판 이동 반복문, 캐릭터 아래 발판 있는지 확인하는 반복문)
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {

						for (int i = 0; i < fieldList.size(); i++) {
							fieldList.get(i).setX(fieldList.get(i).getX() - 4);
						}

						int range = (int) (landimg.getWidth(null) * 1.2); // 캐릭터가 서있을 수 있는 위치

//						range(캐릭터의 발이 닿는 곳) 안에 발판이 있다면 1, 없다면 0
						for (int i = 0; i < fieldList.size(); i++) {
							if (fieldList.get(i).getX() >= 0 && fieldList.get(i).getX() < range) {
								count = 1;
							} else if (i == fieldList.size() - 1) {
								count = 0;
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

//			캐릭터의 높이에 따라 발판 위치를 저장하는 쓰레드
			
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {

						int foot = imgY + img.getHeight(null);
						
						if(count == 0) {
							nowField = 2000;
						}else if(count == 1 && foot > field) {
							nowField = 2000;
						}else if(count == 1 && foot < field) {
							nowField = field;
						}

						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();

			
			
//			낙하 스레드
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {

//						발바닥 위치는 이미지의 y위치 + 이미지의 높이
						int foot = imgY + img.getHeight(null);

//						발바닥이 땅보다 위에 있으면 작동
//						점프중이지 않고 공중에 있으며 떨어지는 중이 아닐 때 작동
						if (jump == false && foot < nowField && fall == false) {
							fall = true; // 떨어지는 중으로 전환
							System.out.println("낙하 시작");
							
							long t1 = getTime();
							long t2;
							int set = 1; // 처음 낙하량까지 테스트
							while (foot < nowField) { // 발이 땅에 닿기 전까지 반복
								
								t2 = getTime() - t1; // 지금 시간에서 t1을 뺀다.
								int fallY = set + (int) ((t2) / 40); // 낙하량을 늘림
								System.out.println("착지");
		

								if (foot + fallY > nowField) { // 떨어지는 양이 너무크면 땅보다 아래에 갈 수 있음
									fallY = nowField - foot;
								}
							
								imgY += fallY;	// Y좌표에 낙하량을 더함

								foot = imgY + img.getHeight(null); // 현재 발바닥 위치 저장

								if (jump == true) { // 떨어지다가 더블점프를 하면 낙하 중지
									break;
								}

								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							fall = false;
							if (jump == false) { // 발이 땅에 닿고 점프중이 아닐 때 더블 점프 카운트를 0으로 변경
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
					if (e.getKeyCode() == KeyEvent.VK_SPACE && doubleJump < 2) {// 스페이스 키누르고 더블점프가 2가 아닐때

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
										jumpY = set - (int) ((t2) / 40); // jump 세팅
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
									if (nowJump == doubleJump) { // 점프가 진짜 끝났을 때를 확인
										jump = false;
									}
								}
							}

						}).start();
					}
				}
			});

			for (int i = 0; i < fieldStr.length(); i++) { // fieldStr의 길이 만큼 반복
				int tempX = i * landimg.getWidth(null); // 반복할때마다 x좌표를 늘려준다.
				if (getGround(fieldStr, i) == 1) { // fieldStr로 땅이 있는 위치에만 발판을 설치한다.
//					getGround 메서드 => 1인 경우만 발판 추가
					fieldList.add(new Foot(landimg, tempX, 400, landimg.getWidth(null), landimg.getHeight(null)));
				}
			}

		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			for (int i = 0; i < fieldList.size(); i++) {
				Image tempImg = fieldList.get(i).getImage();
				int tempX = fieldList.get(i).getX();
				int tempY = fieldList.get(i).getY();
				int tempWidth = fieldList.get(i).getWidth();
				int tempHeight = fieldList.get(i).getHeight();
				g.drawImage(tempImg, tempX, tempY, tempWidth, tempHeight, null);
			}

			g.drawImage(img, landimg.getWidth(null) / 2, imgY, img.getWidth(null), img.getHeight(null), this);
		}
	}

	/**
	 * Create the application.
	 */
	public Foots() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(1200, 800);
		frame.setTitle("jump rabbit");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}

}
