package validation;

import javax.swing.JOptionPane;


//Validation Methods
public class Validation 
{
	//Validate input for not blank strings
	public static String checkStringInput(String x) 
	{
		if(x.trim().length() == 0)
		{
			do
			{
				x = JOptionPane.showInputDialog("Your name can not be blank. Please enter your name");
			}
			while(x.trim().length() == 0);
		}
		
		x = x.trim();
		
		JOptionPane.showMessageDialog(null, "Thank You " + x);
		
		return x;
	}

	//Confirms quit
	public static void catchExit()
	{
		int selectedValue = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Quit?", "Quit", JOptionPane.YES_NO_OPTION);

		if(selectedValue == JOptionPane.YES_OPTION)
		{
			System.exit(0);
		}
	}
}