package lineUp;

import gui.BackPanel;
import gui.Menu;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.JLayeredPane;


public class LineUp extends JFrame
{
	public static JFrame gameFrame;
	public static JMenuBar menuBar;	
	public static JLabel clockLabel;
	public static JPanel buttonPanel;
	public static JLayeredPane backPanel;

	public LineUp()
	{
		super("Line Up");
		setLayout(new FlowLayout());
	}
	
	public static JFrame createGameFrame()
	{
		//Casino_Test Frame
		gameFrame = new LineUp();
		gameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		gameFrame.setSize(1151, 700);
		gameFrame.setVisible(true);
		gameFrame.setResizable(false);
		
		return gameFrame;
	}

	public static void main(String[] args)
	{	
		//Attempts to set Nimbus look and feel if available
		try 
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    } 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Nimbus isn't available");
		}
		
		JFrame gameFrame = createGameFrame();
		 
		//Background
		backPanel = new BackPanel();
		gameFrame.add(backPanel);
		
		//Menu Bar
		menuBar = Menu.createMenuBar();
		gameFrame.setJMenuBar(menuBar);
	}	
}