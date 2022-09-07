package gameItemGet;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class getItem {

	ImageIcon backIc = new ImageIcon("img/back3.png");
	Image backImg = backIc.getImage();
	
	ImageIcon jellyIc1 = new ImageIcon("img/jelly1.png");
	Image jelly1 = jellyIc1.getImage();
	
	ImageIcon carrotIc1 = new ImageIcon("img/carrot.png");
	Image carrot = carrotIc1.getImage();
	
	ImageIcon bonusFirstCookieIc = new ImageIcon("img/c2gif.gif");
	Image bonusFirstCookie = bonusFirstCookieIc.getImage();
	
	List<Item> imgList = new ArrayList<>();
	
//  1번째 이미지
	int back1X = 0;
	
//  2번쨰 이미지가 뒤따라 와야하므로 backImg의 넓이를 가져온다.
	int back2X = backImg.getWidth(null);
	
	int jellyX = 400;
	
	int c1X = 50;
	int c1Y = 60;
	
	private JFrame frame;


	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					getItem window = new getItem();
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
			
			int itemX = 500;
			for(int i=1; i < 10; i++) {
				itemX += 100;
				imgList.add(new Item(jelly1, 0, itemX, 100));
			}
			for(int i=1; i < 10; i++) {
				itemX += 100;
				imgList.add(new Item(carrot, 0, itemX, 100));
			}
			
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						back1X--;
						back2X--;

						
						if(back1X < -(backImg.getWidth(null))) {
							back1X = backImg.getWidth(null);
						}
						if(back2X < -(backImg.getWidth(null))) {
							back2X = backImg.getWidth(null);
						}
						
//						아이템 x 좌표 줄여주기
						for(int i= 0; i < imgList.size(); i++) {
							imgList.get(i).setX(imgList.get(i).getX()-3);
						}
						
//						캐릭터, 아이템 좌표 만날때 remove 해주기
						for(int i= 0; i < imgList.size(); i++) {
							if(imgList.get(0).getX() > c1X
								&& imgList.get(0).getX() < c1X + bonusFirstCookie.getWidth(null)
								&& imgList.get(0).getY() > c1Y
								&& imgList.get(0).getY() < c1Y + bonusFirstCookie.getHeight(null)) {
								
								imgList.remove(i);
							}
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
		protected void paintComponent(Graphics g) {	//그림 그려주는 메서드
			super.paintComponent(g);	//캔버스 비워주는 메서드
			
			g.drawImage(backImg, back1X, 0, this);	//1번째 그림		
			g.drawImage(backImg, back2X, 0, this);	//2번째 그림
		
			
			for(int i=0; i<imgList.size(); i++) {
				g.drawImage(imgList.get(i).getImage(),
						imgList.get(i).getX(),
						imgList.get(i).getY(),
						this);
			}
			
			g.drawImage(bonusFirstCookie, c1X, c1Y, this);	// 쿠키
			
		}
	}


	public getItem() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setTitle("jump rabbit🐰");
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}

}
