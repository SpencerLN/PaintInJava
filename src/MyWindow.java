import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


// TODO: Auto-generated Javadoc
/**
 * The Class MyWindow.
 *
 * @author Spencer Niemi
 * @version 1.0
 * Class: CSS162 Winter 2014
 * Instructor: Rob Nash
 */
public class MyWindow extends JFrame {
	
	/**
	 * Instantiates a new my window.
	 */
	public MyWindow() {
		super("Name that window!");
		
		setLayout(new BorderLayout());
		
		add(new ButtonPanel(), BorderLayout.WEST);
		
		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		MyWindow app = new MyWindow();
	}
	
	/**
	 * The Class ButtonPanel.
	 *
	 * @author Spencer Niemi
	 * @version 1.0
	 * Class: CSS162 Winter 2014
	 * Instructor: Rob Nash
	 */
	private class ButtonPanel extends JPanel implements ActionListener {
		
		/** The sq. */
		ImageIcon sq = new ImageIcon("square-outline-32.jpg");
		
		/** The circ. */
		ImageIcon circ = new ImageIcon("circle-32.png");
		
		/** The square button. */
		JButton squareButton = new JButton(sq);
		
		/** The circle button. */
		JButton circleButton = new JButton(circ);
		
		/** The red button. */
		JButton redButton = new JButton("Red");
		
		/** The blue button. */
		JButton blueButton = new JButton("Blue");
		
		/**
		 * Instantiates a new button panel.
		 */
		public ButtonPanel() {
			
			setLayout(new GridLayout(2,2));
			add(squareButton);
			add(circleButton);
			add(redButton);
			add(blueButton);

			redButton.setBackground(Color.RED);
			blueButton.setBackground(Color.BLUE);
			
			squareButton.addActionListener(this);
			circleButton.addActionListener(this);
			redButton.addActionListener(this);
			blueButton.addActionListener(this);
			
			
			
		}
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == squareButton) {
				JOptionPane.showMessageDialog(this, "The Square!");
			} else {
				JOptionPane.showMessageDialog(this, "You clicked a button!");
			}
		}
	}
	
	
	
}
