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

public class Jump {

	private JFrame frame;

	int field = 250; // ���ϰ� ���ߴ� ����

	ImageIcon ic = new ImageIcon("img/c1gif.gif");
	Image img = ic.getImage();

	int imgY = 5; // �̹����� �����ϴ� Y��ǥ

	boolean fall = false; // ���� ���������� Ȯ��
	boolean jump = false; // ���� ���������� Ȯ��

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
					Jump window = new Jump();
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

			addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}

				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_SPACE && jump == false) {// �����̽� Ű������ �������� �ƴҶ�
						new Thread(new Runnable() {

							@Override
							public void run() {

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
										int jumY = set - (int) ((t2) / 40); // jump ����
										imgY -= jumY; // y�� ����
										foot = imgY + img.getHeight(null); // �߹ٴ� ��ġ ����
										repaint();
										try {
											Thread.sleep(10);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
									jump = false;
								}
							}

						}).start();
					}
				}
			});

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
								int jumY = set + (int) ((t2) / 40); // ���Ϸ��� �ø�
								imgY += jumY; // y������ ���Ϸ� ����
								foot = imgY + img.getHeight(null); // ���� �߹ٴ� ��ġ ����
								repaint();
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							fall = false;
						}
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
			g.drawImage(img, 100, imgY, this);
		}

	}

	/**
	 * Create the application.
	 */
	public Jump() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}

}
