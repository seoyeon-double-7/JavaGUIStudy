package guiTest;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class EventEx04 extends JFrame{
	private JLabel la;
	
	public EventEx04() {
		la = new JLabel("Hello");
		setTitle("MouseEvent ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container  c = getContentPane();
		c.addMouseListener(new MyMouseListener());
		c.setLayout(null);	// absolute ���̾ƿ�
		la.setSize(50, 20);	//�� ������
		la.setLocation(30, 30);	//�� ��ġ
		c.add(la);
		setSize(250, 250);
		setVisible(true);
		
	}
	
	class MyMouseListener extends MouseAdapter{

//		���콺 Ŭ���ϰ� ������ ��
		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			la.setLocation(x, y);
		}
		
	}
	
	/*class MyMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}*/
	
	public static void main(String[] args) {
		new EventEx04();
	}
}
