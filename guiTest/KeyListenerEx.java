package guiTest;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class KeyListenerEx extends JFrame {
	private JLabel[] keyMessage;

	public KeyListenerEx() {
		setTitle("keyListener");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c= getContentPane();
		c.setLayout(new FlowLayout());
		c.addKeyListener(new MyKeyListener());
		
		
		// 레이블배열을 3개 생성하고 각레이블 컴포넌트생성
		keyMessage = new JLabel[3]; // 레이블 배열 생성
		keyMessage[0] = new JLabel(" getKeyCode() ");
		keyMessage[1] = new JLabel(" getKeyCode() ");
		keyMessage[2] = new JLabel(" getKeyCode() ");
		
		// 3개의 레이블 컴포넌트를 컨텐트팬에 부착
		for (int i = 0; i < keyMessage.length; i++) {
			c.add(keyMessage[i]);
			keyMessage[i].setOpaque(true); // 배경색이 보이도록 불투명 속성 설정
			keyMessage[i].setBackground(Color.YELLOW);
		}
		
		setSize(300, 150);
		setVisible(true);
		
		// 컨텐트팬이 키입력을 받을 수 있도록 포커스 강제 지정
		c.setFocusable(true);
		c.requestFocus();
	}

	// key 리스너 구현
	class MyKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			char keyChar = e.getKeyChar();
			keyMessage[0].setText("아스키코드 : " + Integer.toString(keyCode));
			keyMessage[1].setText("char : " + Character.toString(keyChar));
			keyMessage[2].setText(KeyEvent.getKeyText(keyCode));

			System.out.println("KeyPressed");
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("keyReleased");
		}

		@Override
		public void keyTyped(KeyEvent e) {
			System.out.println("KeyTyped");
		}

	}

	public static void main(String[] args) {
		new KeyListenerEx();
	}

}