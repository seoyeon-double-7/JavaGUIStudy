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

	ImageIcon backIc = new ImageIcon("img/back1.png");
	Image backImg = backIc.getImage();
	
	ImageIcon jellyIc1 = new ImageIcon("img/jelly1.png");
	Image jelly1 = jellyIc1.getImage();
	
	ImageIcon carrotIc1 = new ImageIcon("img/carrot.png");
	Image carrot = carrotIc1.getImage();
	
	ImageIcon bonusFirstCookieIc = new ImageIcon("img/rabbit_b.png");
	Image bonusFirstCookie = bonusFirstCookieIc.getImage();
	
	List<Item> imgList = new ArrayList<>();
	
//	1๋ฒ์งธ ?ด๋ฏธ์?
	int back1X = 0;
	
//	2๋ฒ์?? ?ด๋ฏธ์?๊ฐ? ?ค?ฐ?ผ ???ผ?๋ฏ?๋ก? backImg? ??ด๋ฅ? ๊ฐ?? ธ?จ?ค.
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
						
//						?ด๋ฏธ์?๊ฐ? ?๋ฉ? ๋ฐ์ผ๋ก? ?? ? ?๊ฐ?๋ฉ?
//						X์ถ์ ?ด๋ฏธ์?? ??ด ์ขํ๋ก? ?ค? ?ฎ๊ธด๋ค.
//						1๋ฒ? ?ด๋ฏธ์?๊ฐ? ๋จผ์? ?๊ฐ?? 2๋ฒ? ?ค? ๋ถ๊ณ 
//						2๋ฒ? ?ด๋ฏธ์?๊ฐ? ?๊ฐ?๋ฉ? ?ค? 1๋ฒ? ?ค? ๋ถ๋ ?ค.
						
						if(back1X < -(backImg.getWidth(null))) {
							back1X = backImg.getWidth(null);
						}
						if(back2X < -(backImg.getWidth(null))) {
							back2X = backImg.getWidth(null);
						}
						
//						??ด??ค? ?ด๋ฏธ์?๋ฅ? ๋ชจ๋ ?ด???จ?ค. (? ค๋ฆฌ๋ค? X์ขํ๋ฅ? ์ค์ด? ๊ฒ?)
						for(int i= 0; i < imgList.size(); i++) {
							imgList.get(i).setX(imgList.get(i).getX()-3);
						}
						
//						??ด??ค?ด ์บ๋ฆญ?ฐ ?ด๋ฏธ์? ๋ฒ์ ?? ?ค?ด?ค๋ฉ? ? ๊ฑฐ๋?ค. (์บ๋ฆญ?ฐ ?ด๋ฏธ์?? ? ค๋ฆฌ์ ?? ์ขํ๊ฐ? ๋ง๋๋ฉ? ?ฌ?ผ์ง?)
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
		protected void paintComponent(Graphics g) {	//๊ทธ๋ฆผ ๊ทธ๋ ค์ฃผ๋ ๋ฉ์?
			super.paintComponent(g);	//์บ๋ฒ?ค๋ฅ? ๋น์์ฃผ๋ ๋ฉ์?
			
			g.drawImage(backImg, back1X, 0, this);	//1๋ฒ? ๊ทธ๋ฆผ		
			g.drawImage(backImg, back2X, 0, this);	//2๋ฒ? ๊ทธ๋ฆผ
		
			
			for(int i=0; i<imgList.size(); i++) {
				g.drawImage(imgList.get(i).getImage(),
						imgList.get(i).getX(),
						imgList.get(i).getY(),
						this);
			}
			
			g.drawImage(bonusFirstCookie, c1X, c1Y, this);	// ์ฟ ํค
			
		}
	}


	public getItem() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setTitle("jump rabbit??");
		frame.setBounds(100, 100, 450, 300);
//		frame.setSize(700, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}

}
