package guiTest;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SwingEx01 extends JFrame{ // JFrame�� ����Ͽ� GUI ��������

	public SwingEx01() {
		Container contentPane = getContentPane();	//�г� ��ü�� ����
//		JFrame�� �⺻�г��� Border�ε� Flow�� �ٲ���
		contentPane.setLayout(new FlowLayout());
		contentPane.add(new JButton("ù��° ��ư"));
		setTitle("���� ������");
		setSize(300, 300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new SwingEx01();
	}

}
