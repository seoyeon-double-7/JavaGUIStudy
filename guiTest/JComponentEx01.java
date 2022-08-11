package guiTest;


import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JComponentEx01 extends JFrame implements ActionListener{

	private JButton b1, b2, b3;
	
	// Å¸°Ù
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		b.setText("Hello");
	}
	
	public JComponentEx01() {
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		b1 = new JButton("b1");
		b2 = new JButton("b2");
		b3 = new JButton("b3");
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		c.add(b1);
		c.add(b2);
		c.add(b3);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(260, 200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new JComponentEx01();
	}

}
