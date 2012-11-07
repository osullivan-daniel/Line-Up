package gui;

import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import lineUp.LineUp;


import cards.Shuffle;

import validation.Validation;


public class Menu 
{
	private static ClockLabel clock;
	private final static JMenuBar menuBar = new JMenuBar();
	private final static JMenu file = new JMenu("File");	
	private final static JMenu lineUp = new JMenu("Line Up");
	private final static JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_E);
	private final static JMenuItem newGame = new JMenuItem("New Game", KeyEvent.VK_N);
	private final static JMenuItem quitGame = new JMenuItem("Quit Game", KeyEvent.VK_Q);
	private final static JMenuItem highScore = new JMenuItem("High Scores", KeyEvent.VK_H);
	
	
	public static JMenuBar createMenuBar()
	{		
		//Sets key shortcut for file
		file.setMnemonic(KeyEvent.VK_F);
		//Adds the menu to the menu bar
		menuBar.add(file);

		//Sets key shortcut for linup
		lineUp.setMnemonic(KeyEvent.VK_L);
		//Adds the menu to the menu bar
		menuBar.add(lineUp);
		
		//add exit to file menu
		file.add(exit);
		//setup exit functions
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				Validation.catchExit();
				//System.exit(0);
			}
		});
		
		//add new game to linup menu
		lineUp.add(newGame);
		//setup newGame functions
		newGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{	
				//reset everything
				quitGame();
				//Generate the card panels
				CardPanel.generateCP();
				//Shuffle the order of the panels
				Shuffle.shuffleCardPanels(CardPanel.cardPanelOrder);
				//display the panels
				dispalayPanels();

				LineUp.buttonPanel = new ButtonPanel();
				LineUp.backPanel.add(LineUp.buttonPanel);
				
				newGame.setEnabled(false);
				quitGame.setEnabled(true);
			
				setClock(new ClockLabel());
				LineUp.backPanel.add(clock);
			}

			public void dispalayPanels() 
			{
				displayCardPanels();
			}
		});
		
		//add quit game to linup menu
		lineUp.add(quitGame);
		//disable its selection
		quitGame.setEnabled(false);
		//setup quit functions
		quitGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{	
				quitGame();
			}
		});
		
		//add high score to linup menu
		lineUp.add(highScore);
		//setup high score functions
		highScore.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{	
				CardPanel.leaderBoard();
			}
		});
				
		return menuBar;
	}

	public static void displayCardPanels() 
	{
		int x = 20;
		int y = 20;
		
		for(int i = 0; i<40; i += 13)
		{
			for(int j = 0; j < 13; j++)
			{	
				CardPanel.cardPanel[CardPanel.cardPanelOrder.get(j + i)].setBounds(x, y, 85, 121);
				LineUp.backPanel.add(CardPanel.cardPanel[j + i]);
				
				x += 85;
			}
			
			x = 20;
			y = y + 131;
		}
	}

	public static void quitGame() 
	{
		LineUp.backPanel.removeAll();
		LineUp.backPanel.repaint();
					
		clock = null;
		CardPanel.cardPanel = null;
		CardPanel.cardPanelOrder = null;
		
		newGame.setEnabled(true);
		quitGame.setEnabled(false);
	}

	public static void setClock(ClockLabel clock) 
	{
		Menu.clock = clock;
	}

	public static void enableNewGame(boolean b) 
	{
		newGame.setEnabled(b);
	}

	public static void enableQuitGame(boolean b) 
	{
		quitGame.setEnabled(b);
	}

	public static void addClock() 
	{
		LineUp.backPanel.add(clock);
	}
	
	public static void startClock() 
	{
		clock.start();
	}
	
	public static void stopClock() 
	{
		clock.stop();
	}

}