package cards;

import java.awt.Image;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JOptionPane;


public class Images 
{	
	public static Image pic(int cardNum)
	{
		String cardPath = null;
		int suit;
		int card;
		Image cardPic = null;
		
		suit = cardNum / 13;
		card = (cardNum % 13) + 1;
		
		//Sets suit path based on card
		switch(suit)
		{
			case 0:
			{
				cardPath = "/Program Files/hearts/";
				break;
			}
			case 1:
			{
				cardPath = "/Program Files/clubs/";
				break;
			}
			case 2:
			{
				cardPath = "/Program Files/diamonds/";
				break;
			}
			case 3:
			{
				cardPath = "/Program Files/spades/";
				break;
			}
		}
		
		//Use the suit plus the card number and the extension
		cardPath += card + ".gif";		
		
		try 
		{
			//Will get the location of the Program Files folder in the same folder as jar when exported.
			java.io.File currentDir = new java.io.File("");
			File imageIn = new File(currentDir.getAbsolutePath() + (cardPath));
			//Set image
			cardPic = ImageIO.read(imageIn);			
		} 
		catch (IOException e) 
		{
			missingImage(); 
		}
		
		return cardPic;
	}
	
	public static void missingImage()
	{
		JOptionPane.showMessageDialog(null, "Could not open file. Program will now close.", "Fatal Error", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
}