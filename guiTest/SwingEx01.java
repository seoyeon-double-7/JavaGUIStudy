package guiTest;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SwingEx01 extends JFrame{ // JFrame을 상속하여 GUI 가져오기

	public SwingEx01() {
		Container contentPane = getContentPane();	//패널 객체에 접근
//		JFrame의 기본패널은 Border인데 Flow로 바꿔줌
		contentPane.setLayout(new FlowLayout());
		contentPane.add(new JButton("첫번째 버튼"));
		setTitle("스윙 프레임");
		setSize(300, 300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new SwingEx01();
	}

}
