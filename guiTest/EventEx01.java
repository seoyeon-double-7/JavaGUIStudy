package guiTest;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

//�ܺ� Ŭ������ ������ Ÿ�� ����
class MyActionListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText().equals("Action")) {
			b.setText("�׼�");
		} else {
			b.setText("Action");
		}
		
	}
}

//�͸� Ŭ������ Ÿ�� ����
public class EventEx01 extends JFrame {
	
	public EventEx01() {
		setTitle("Action �̺�Ʈ ������ ����");
		
		// x��ư Ŭ���� �̺�Ʈ �й轺���� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		c.setLayout(new FlowLayout());
		
		JButton btn = new JButton("Action");
		
		// ������ ����
		btn.addActionListener(new MyActionListener());
		c.add(btn);
		setSize(350,150);
		setVisible(true);
	}

	public static void main(String[] args) {
		new EventEx01();
	}
}