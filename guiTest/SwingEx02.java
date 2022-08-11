package guiTest;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SwingEx02 extends JFrame{ // JFrame�� ����Ͽ� GUI ��������

	public SwingEx02() {
		setTitle("ControlPane�� JFrame");
		
//		x ��ư ������ ���α׷� �����ϵ��� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		�г� ��ü�� ����
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.orange);
		
//		����Ʈ�ҿ� FlowLayout ��ġ ������ �ޱ�
		contentPane.setLayout(new FlowLayout());
		
		contentPane.add(new JButton("OK"));
		contentPane.add(new JButton("Cancel"));
		contentPane.add(new JButton("Ignore"));
		
		setSize(300, 150);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new SwingEx02();
	}

}
