package guiTest;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


// 메인 클래스에 ActionListener를 구현하고 this로 객체를 리스너의 타겟으로 만든다
public class EventEx02 extends JFrame implements ActionListener {
	
	public EventEx02() {
		setTitle("Action 이벤트 리스너 예제");
		
		// x버튼 클릭시 이벤트 분배스레드 끄기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		c.setLayout(new FlowLayout());
		
		JButton btn = new JButton("Action");
		
		// 리스너 생성
		btn.addActionListener(this);
		c.add(btn);
		setSize(350,150);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("버튼 클릭됨");
	}
	
	public static void main(String[] args) {
		EventEx02 ex012 = new EventEx02();
	}
}