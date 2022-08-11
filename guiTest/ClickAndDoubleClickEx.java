package guiTest;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class ClickAndDoubleClickEx extends JFrame {
	
	class MyMouseListener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2) { // 더블클릭의 경우
				int r= (int)(Math.random()*256);
				int g= (int)(Math.random()*256);
				int b= (int)(Math.random()*256);
				
				Component c = (Component)e.getSource(); // 마우스가 클릭된 컴포넌트
				c.setBackground(new Color(r,g,b));
			}
		}
	}
	
	public ClickAndDoubleClickEx() {
		setTitle("Click and DoubleClick 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.addMouseListener(new MyMouseListener());
		setSize(300, 200);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new ClickAndDoubleClickEx();
	}
}