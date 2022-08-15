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
	int imgY = 250; // �̹����� �����ϴ� Y��ǥ

	boolean fall = false; // ���� ���������� Ȯ��
	boolean jump = false; // ���� ���������� Ȯ��

	int doubleJump = 0; // ���� ī��Ʈ(2���Ǹ� �������� ����)

//	�ð� ��������
	static long getTime() {
		return Timestamp.valueOf(LocalDateTime.now()).getTime();
	}

//	1�̸� ������ �ְ� 0�̸� ����
	String fieldStr = "01111111110011111110110111110111110011111111011111111101111111101111111111110"
			+ "1111111110011111110110111110111110011111111011111111101111111101111111111110"
			+ "1111111110011111110110111110111110011111111011111111101111111101111111111110";
	List<Foot> fieldList = new ArrayList<>(); // ���� ��ü�� ���� ����Ʈ

	int count = 0; // ���� Ȯ�� ����

	int nowField = field; // ĳ���� ���̿� ���� ���� ��ġ ���� ����

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

//			repaint�� �����ϴ� ������
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

//			������ �������� �̵���Ű�¾����� (���� �̵� �ݺ���, ĳ���� �Ʒ� ���� �ִ��� Ȯ���ϴ� �ݺ���)
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {

						for (int i = 0; i < fieldList.size(); i++) {
							fieldList.get(i).setX(fieldList.get(i).getX() - 4);
						}

						int range = (int) (landimg.getWidth(null) * 1.2); // ĳ���Ͱ� ������ �� �ִ� ��ġ

//						range(ĳ������ ���� ��� ��) �ȿ� ������ �ִٸ� 1, ���ٸ� 0
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

//			ĳ������ ���̿� ���� ���� ��ġ�� �����ϴ� ������
			
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

			
			
//			���� ������
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {

//						�߹ٴ� ��ġ�� �̹����� y��ġ + �̹����� ����
						int foot = imgY + img.getHeight(null);

//						�߹ٴ��� ������ ���� ������ �۵�
//						���������� �ʰ� ���߿� ������ �������� ���� �ƴ� �� �۵�
						if (jump == false && foot < nowField && fall == false) {
							fall = true; // �������� ������ ��ȯ
							System.out.println("���� ����");
							
							long t1 = getTime();
							long t2;
							int set = 1; // ó�� ���Ϸ����� �׽�Ʈ
							while (foot < nowField) { // ���� ���� ��� ������ �ݺ�
								
								t2 = getTime() - t1; // ���� �ð����� t1�� ����.
								int fallY = set + (int) ((t2) / 40); // ���Ϸ��� �ø�
								System.out.println("����");
		

								if (foot + fallY > nowField) { // �������� ���� �ʹ�ũ�� ������ �Ʒ��� �� �� ����
									fallY = nowField - foot;
								}
							
								imgY += fallY;	// Y��ǥ�� ���Ϸ��� ����

								foot = imgY + img.getHeight(null); // ���� �߹ٴ� ��ġ ����

								if (jump == true) { // �������ٰ� ���������� �ϸ� ���� ����
									break;
								}

								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							fall = false;
							if (jump == false) { // ���� ���� ��� �������� �ƴ� �� ���� ���� ī��Ʈ�� 0���� ����
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
					if (e.getKeyCode() == KeyEvent.VK_SPACE && doubleJump < 2) {// �����̽� Ű������ ���������� 2�� �ƴҶ�

						new Thread(new Runnable() {

							@Override
							public void run() {

								doubleJump++; // ���� Ƚ�� ����
								int nowJump = doubleJump; // ��������, ������������ ����

								jump = true; // ���������� ����

								if (doubleJump == 1)
									System.out.println("����");
								else if (doubleJump == 2)
									System.out.println("���� ����");

//									�߹ٴ� ��ġ�� �̹����� y��ġ + �̹����� ����
								int foot = imgY + img.getHeight(null); // �߹ٴ���ġ y��ǥ+�̹�������

								if (fall == false) { // �������� ���� �ƴҋ� ����
									jump = true;
									System.out.println("���� ����");
									long t1 = getTime();
									long t2;
									int set = 8; // ó�� ���Ϸ����� �׽�Ʈ
									int jumpY = 8;
									while (jumpY > 0) { // ��� ���̰� 0�϶����� �ݺ�
										t2 = getTime() - t1; // ���� �ð����� t1�� ����.
										jumpY = set - (int) ((t2) / 40); // jump ����
										imgY -= jumpY; // y�� ����
										foot = imgY + img.getHeight(null); // �߹ٴ� ��ġ ����
										repaint();

										if (nowJump != doubleJump) { // ������ �ѹ� ���Ǹ� ������ �����
											break;
										}

										try {
											Thread.sleep(10);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
									if (nowJump == doubleJump) { // ������ ��¥ ������ ���� Ȯ��
										jump = false;
									}
								}
							}

						}).start();
					}
				}
			});

			for (int i = 0; i < fieldStr.length(); i++) { // fieldStr�� ���� ��ŭ �ݺ�
				int tempX = i * landimg.getWidth(null); // �ݺ��Ҷ����� x��ǥ�� �÷��ش�.
				if (getGround(fieldStr, i) == 1) { // fieldStr�� ���� �ִ� ��ġ���� ������ ��ġ�Ѵ�.
//					getGround �޼��� => 1�� ��츸 ���� �߰�
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
