package gui;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JLayeredPane;

import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;


public class BackPanel extends JLayeredPane
{
	private Image img;
	
	public BackPanel()
	{
		//try to set the back image
		try
		{			
			//Will get the location of the Program Files folder in the same folder as jar when exported.
			java.io.File currentDir = new java.io.File("");
			File imageIn = new File(currentDir.getAbsolutePath() + ("/Program Files/other/felt.jpg"));
			//Set image
			img = ImageIO.read(imageIn);
		}
		catch (IOException e) 
		{
			missingImage();
		}
				
		Dimension size = new Dimension(img.getWidth(null),img.getHeight(null));
		
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);	
	}

	//If there is a missing image terminate program
	public void missingImage()
	{
		JOptionPane.showMessageDialog(null, "Could not open file. Program will now close.", "Fatal Error", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
	
	public void paintComponent(Graphics g) 
	{
		g.drawImage(img, 0, 0, null);
	}
}