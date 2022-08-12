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
//		이미지를 그리는 메서드
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);	// 캔버스 비우기
			g.drawImage(img, 50, 50, this);
		}
		public MyPanel() {
			setFocusable(true);	//입력 포커스 받기
			addMouseListener(new MouseAdapter() {	// 마우스 리스너 추가
				
				@Override
				public void mouseClicked(MouseEvent e) {	// 마우스가 클릭되면 작동
					if(img == ic1.getImage()) {	//이미지가 ic1의 이미지면 ic2의 이미지로바꾸기
						System.out.println("오른쪽으로 변경");
						img = ic2.getImage();
					} else {
						img = ic1.getImage();
						System.out.println("왼쪽으로 변경");
					}
					repaint();	//그림 다시 그리기
				}
				
			});
			
		}
		
	}
	
	
	public Clicking() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("swing으로 이미지 변경");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}

}
