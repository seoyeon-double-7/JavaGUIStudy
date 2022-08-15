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

	int field = 500; // ���ϰ� ���ߴ� ����

	ImageIcon ic = new ImageIcon("img/c3gif.gif");
	Image img = ic.getImage();

	int imgX = 500;
	int imgY = 250; // �̹����� �����ϴ� Y��ǥ

	boolean fall = false; // ���� ���������� Ȯ��
	boolean jump = false; // ���� ���������� Ȯ��

	int doubleJump = 0; // ���� ī��Ʈ(2���Ǹ� �������� ����)

//	�ð� ��������
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

//						�߹ٴ� ��ġ�� �̹����� y��ġ + �̹����� ����
						int foot = imgY + img.getHeight(null);

//						�߹ٴ��� ������ ���� ������ �۵�
//						���������� �ʰ� ���߿� ������ �������� ���� �ƴ� �� �۵�
						if (jump == false && foot < field && fall == false) {
							fall = true; // �������� ������ ��ȯ
							System.out.println("���� ����");
							long t1 = getTime();
							long t2;
							int set = 1; // ó�� ���Ϸ����� �׽�Ʈ
							while (foot < field) { // ���� ���� ��� ������ �ݺ�
								t2 = getTime() - t1; // ���� �ð����� t1�� ����.
								int fallY = set + (int) ((t2) / 60); // ���Ϸ��� �ø�
								System.out.println("����");
								
								if(foot + fallY >= field) {	//�������� ���� �ʹ�ũ�� ������ �Ʒ��� �� �� ����
									imgY = field - img.getHeight(null);	//��������
									break;
								} else {
									imgY += fallY;
								}
								
								foot = imgY + img.getHeight(null); // ���� �߹ٴ� ��ġ ����
								
								repaint();
								
								if(jump == true) {	//�������ٰ� ���������� �ϸ� ���� ����
									break;
								}
								
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							fall = false;
							if(jump == false) {	//���� ���� ��� �������� �ƴ� �� ���� ���� ī��Ʈ�� 0���� ����
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
						System.out.println("����");
						if(imgX <= 0) imgX = 0;
						else imgX -= 50;						
					}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
						System.out.println("������");
						if(imgX >= 1200-img.getWidth(null)) imgX = 1200-img.getWidth(null);
						else imgX += 50;
					}else if (e.getKeyCode() == KeyEvent.VK_SPACE && doubleJump < 2) {// �����̽� Ű������ ���������� 2�� �ƴҶ�

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
										jumpY = set - (int) ((t2) / 60); // jump ����
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
									if (nowJump == doubleJump) {	//������ ��¥ ������ ���� Ȯ��
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
