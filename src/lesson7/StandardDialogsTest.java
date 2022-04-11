package lesson7;

import java.io.File;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class StandardDialogsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JButton press = new JButton("Press me");
		
		JOptionPane.showMessageDialog(null, press, "Caption", JOptionPane.ERROR_MESSAGE);
		int x = JOptionPane.showConfirmDialog(null, press, "Caption", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	
		JFileChooser	fc = new JFileChooser(new File("./"));
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			System.err.println("Selected: "+fc.getSelectedFile());
		}
	}
}
