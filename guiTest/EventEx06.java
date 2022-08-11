package guiTest;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


// �ܺ� Ŭ������ ������ Ÿ�� ����
class MmActionListener implements ActionListener{
	
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

public class EventEx06 extends JFrame {
	
	public EventEx06() {
		setTitle("Action �̺�Ʈ ������ ����");
		
		// x��ư Ŭ���� �̺�Ʈ �й轺���� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		c.setLayout(new FlowLayout());
		
		JButton btn = new JButton("Action");
		
		// ������ ����
		btn.addActionListener(new MmActionListener());
		c.add(btn);
		setSize(350,150);
		setVisible(true);
	}

	public static void main(String[] args) {
		new EventEx06();
	}
}

