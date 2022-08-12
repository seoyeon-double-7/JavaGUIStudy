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
	
//	1Î≤àÏß∏ ?ù¥ÎØ∏Ï?
	int back1X = 0;
	
//	2Î≤àÏ?? ?ù¥ÎØ∏Ï?Í∞? ?í§?î∞?ùº ???ïº?ïòÎØ?Î°? backImg?ùò ?Ñì?ù¥Î•? Í∞??†∏?ò®?ã§.
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
						
//						?ù¥ÎØ∏Ï?Í∞? ?ôîÎ©? Î∞ñÏúºÎ°? ?ôÑ?†Ñ?ûà ?ÇòÍ∞?Î©?
//						XÏ∂ïÏùÑ ?ù¥ÎØ∏Ï??ùò ?Ñì?ù¥ Ï¢åÌëúÎ°? ?ã§?ãú ?òÆÍ∏¥Îã§.
//						1Î≤? ?ù¥ÎØ∏Ï?Í∞? Î®ºÏ? ?ÇòÍ∞??Ñú 2Î≤? ?í§?óê Î∂ôÍ≥†
//						2Î≤? ?ù¥ÎØ∏Ï?Í∞? ?ÇòÍ∞?Î©? ?ã§?ãú 1Î≤? ?í§?óê Î∂áÎäî ?ã§.
						
						if(back1X < -(backImg.getWidth(null))) {
							back1X = backImg.getWidth(null);
						}
						if(back2X < -(backImg.getWidth(null))) {
							back2X = backImg.getWidth(null);
						}
						
//						?ïÑ?ù¥?Öú?ì§?ùò ?ù¥ÎØ∏Ï?Î•? Î™®Îëê ?ù¥?èô?ãú?Ç®?ã§. (?†§Î¶¨Îì§?ùò XÏ¢åÌëúÎ•? Ï§ÑÏù¥?äî Í≤?)
						for(int i= 0; i < imgList.size(); i++) {
							imgList.get(i).setX(imgList.get(i).getX()-3);
						}
						
//						?ïÑ?ù¥?Öú?ì§?ù¥ Ï∫êÎ¶≠?Ñ∞ ?ù¥ÎØ∏Ï? Î≤îÏúÑ ?ïà?óê ?ì§?ñ¥?ò§Î©? ?†úÍ±∞Îêú?ã§. (Ï∫êÎ¶≠?Ñ∞ ?ù¥ÎØ∏Ï??óê ?†§Î¶¨Ïùò ?ãú?ûë Ï¢åÌëúÍ∞? ÎßåÎÇòÎ©? ?Ç¨?ùºÏß?)
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
		protected void paintComponent(Graphics g) {	//Í∑∏Î¶º Í∑∏Î†§Ï£ºÎäî Î©îÏÑú?ìú
			super.paintComponent(g);	//Ï∫îÎ≤Ñ?ä§Î•? ÎπÑÏõåÏ£ºÎäî Î©îÏÑú?ìú
			
			g.drawImage(backImg, back1X, 0, this);	//1Î≤? Í∑∏Î¶º		
			g.drawImage(backImg, back2X, 0, this);	//2Î≤? Í∑∏Î¶º
		
			
			for(int i=0; i<imgList.size(); i++) {
				g.drawImage(imgList.get(i).getImage(),
						imgList.get(i).getX(),
						imgList.get(i).getY(),
						this);
			}
			
			g.drawImage(bonusFirstCookie, c1X, c1Y, this);	// Ïø†ÌÇ§
			
		}
	}


	public getItem() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setTitle("jump rabbit?üê?");
		frame.setBounds(100, 100, 450, 300);
//		frame.setSize(700, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}

}
