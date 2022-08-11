package guiTest;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FlowLayoutEx01 extends JFrame{

	public FlowLayoutEx01() {
		setTitle("FlowLayout Sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container  c = getContentPane();
		
//		����Ʈ�ҿ� FlowLayout ��ġ ������ �ޱ�
		c.setLayout(new FlowLayout(FlowLayout.LEFT, 6, 40));
		
		c.add(new JButton("add"));
		c.add(new JButton("sub"));
		c.add(new JButton("mul"));
		c.add(new JButton("div"));
		c.add(new JButton("Calculate"));
		
		setSize(300, 200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new FlowLayoutEx01();
	}

}
