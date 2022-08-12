package guiTest02;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Clicking {

	private JFrame frame;
	
	MyPanel panel;
	
	ImageIcon ic1 = new ImageIcon("img/shot.png");
	ImageIcon ic2 = new ImageIcon("img/shotR.png");
	Image img = ic1.getImage();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clicking window = new Clicking();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	class MyPanel extends JPanel{

		@Override
//		�̹����� �׸��� �޼���
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);	// ĵ���� ����
			g.drawImage(img, 50, 50, this);
		}
		public MyPanel() {
			setFocusable(true);	//�Է� ��Ŀ�� �ޱ�
			addMouseListener(new MouseAdapter() {	// ���콺 ������ �߰�
				
				@Override
				public void mouseClicked(MouseEvent e) {	// ���콺�� Ŭ���Ǹ� �۵�
					if(img == ic1.getImage()) {	//�̹����� ic1�� �̹����� ic2�� �̹����ιٲٱ�
						System.out.println("���������� ����");
						img = ic2.getImage();
					} else {
						img = ic1.getImage();
						System.out.println("�������� ����");
					}
					repaint();	//�׸� �ٽ� �׸���
				}
				
			});
			
		}
		
	}
	
	
	public Clicking() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("swing���� �̹��� ����");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}

}
