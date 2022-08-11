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
		setTitle("JComponent�� ���� �޼ҵ� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		b1 = new JButton("Magenta/Yellow Button");
		b2 = new JButton("___Disabled Button___");
		b3 = new JButton("getX(), getY()");

		b1.setBackground(Color.YELLOW); // ��׶���
		b1.setForeground(Color.MAGENTA); // ���׶���
		b1.setFont(new Font("Arial", Font.ITALIC, 20));
		b2.setEnabled(false);
		b3.addActionListener(new ActionListener() {

			// Ÿ�� (�ݹ�Ǵ�)
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource(); // Object�� �����ϱ� ������ �ٿ�ĳ����!!
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
