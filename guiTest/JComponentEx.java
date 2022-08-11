package guiTest;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JComponentEx extends JFrame {

	private JButton b1, b2, b3;

	public JComponentEx() {
		setTitle("JComponent의 공통 메소드 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		b1 = new JButton("Magenta/Yellow Button");
		b2 = new JButton("___Disabled Button___");
		b3 = new JButton("getX(), getY()");

		b1.setBackground(Color.YELLOW); // 백그라운드
		b1.setForeground(Color.MAGENTA); // 포그라운드
		b1.setFont(new Font("Arial", Font.ITALIC, 20));
		b2.setEnabled(false);
		b3.addActionListener(new ActionListener() {

			// 타겟 (콜백되는)
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource(); // Object를 리턴하기 때문에 다운캐스팅!!
//				b3.setText("hello");
				if (b.getText().equals("getX(), getY()")) {
					b.setText("==> "+b.getX() + "," + b.getY());
				} else {
					b.setText("getX(), getY()");
				}

			}
		});

		c.add(b1);
		c.add(b2);
		c.add(b3);

		setSize(260, 200);
		setVisible(true);

	}

	public static void main(String[] args) {
		new JComponentEx();
	}
}
