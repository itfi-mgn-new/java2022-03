package lesson6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.LineBorder;

public class SwingTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final JFrame	frame = new JFrame();
		
		frame.addWindowListener(new WindowListener() {
			@Override public void windowActivated(WindowEvent e) {}

			@Override 
			public void windowClosed(WindowEvent e) {
				frame.dispose();
				System.exit(0);
			}

			@Override public void windowClosing(WindowEvent e) {}
			@Override public void windowDeactivated(WindowEvent e) {}
			@Override public void windowDeiconified(WindowEvent e) {}
			@Override public void windowIconified(WindowEvent e) {}
			@Override public void windowOpened(WindowEvent e) {}
		});
		
		frame.setSize(800, 600);
		frame.setLocation(200,200);
		
		final JLabel label = new JLabel("<html><body><font color=yellow>sdkkdksjdk</font></body></html>");
		final JLabel labelA = new JLabel("111111111");
		final JLabel label2 = new JLabel("r9778798");
		final JButton label3 = new JButton("jvnkorfljgpdgpofdgpoiopfdgpokopdfgpoidfetkml");
		
		label3.setPreferredSize(new Dimension(100,100));
		
		label3.addActionListener((e)->{System.err.println("dsfhjdshfjhjsdkjfhjsdfhjsdfljsldkfjlk");});
		
		label.setBorder(new LineBorder(Color.BLUE,3));
		label2.setBorder(new LineBorder(Color.GREEN,3));
//		label3.setBorder(new LineBorder(Color.RED,3));
		

		final JPanel	panel = new JPanel();
		final BoxLayout	bl = new BoxLayout(panel, BoxLayout.Y_AXIS);
		
		panel.setLayout(bl);
		
		panel.setBorder(new LineBorder(Color.MAGENTA,3));
	
		panel.add(label2);
		panel.add(label3);
		
		final JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, label, labelA);
		
		
		frame.add(sp, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.SOUTH);
		
		JMenuItem	item = new JMenuItem("sdsdsdsdf");
		
		item.addActionListener((i)->System.err.println("URA!!!"));
		item.setActionCommand("menu");
		
		JMenu		menu = new JMenu("TEST");
		
		menu.add(item);
		
		JMenuBar	bar = new JMenuBar();
		
		bar.add(menu);
		
		frame.add(bar, BorderLayout.NORTH);

		
		frame.setVisible(true);
		System.err.println("sdfjlkdsjflkjdsf");
	}
}
