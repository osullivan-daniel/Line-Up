package gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;

import java.util.ArrayList;
import java.util.Collections;


import cards.Images;

import leaderBoard.LeaderBoard;
import lineUp.LineUp;

import sqlLiteLeaderBoard.AddHighScore;
import validation.Validation;


public class CardPanel extends JPanel implements MouseListener
{
	public static CardPanel[] cardPanel;
	public static ArrayList<Integer> shuffleList;
	public static ArrayList<Integer> cardPanelOrder;

	public static int cardOne = -5;
	public static int cardTwo = -5;
	public static int cardTest = -5;
	public static boolean cardOneSelected = false;
	
	public int card;
	public boolean ace;
	public boolean click;
	public static LeaderBoard leaderFrame;
	
	public CardPanel(int i)
	{
		this.setLayout(null);
		this.addMouseListener(this);
		this.setOpaque(false);
		
		card = i;
	}
		
	public static CardPanel CreateCardPanel(int i)
	{
		CardPanel cardPanel = new CardPanel(i);
				
		return cardPanel;
	}
	
	public static void generateCP() 
	{
		cardPanel = new CardPanel[52];
		cardPanelOrder = new ArrayList<Integer>();
		
		for(int i = 0; i<52; i++)
		{
			cardPanel[i] = CardPanel.CreateCardPanel(i);
			cardPanelOrder.add(i); 
		}
	}
	
	public static int getCardValue(int i)
	{
		return cardPanel[i].getCard();
	}
		
	public int getCard()
	{
		return this.card;
	}
	
	public void paintComponent(Graphics g) 
	{	   		
		super.paintComponent(g);
		
		if(this.click == true)
		{
			this.setBorder(BorderFactory.createLoweredBevelBorder());
			
		}
		else
		{
			this.setBorder(BorderFactory.createEmptyBorder());
		}
		
		if(!ace)
		{
			g.drawImage(Images.pic(card), 10, 10, this);
		}
	}
	
	public void setAce()
	{
		ace = true;
	}
	
	public void mouseClicked(MouseEvent e) 
	{	
		//if this panel hasn't been selected
		if(this.click == false)
		{
			//if the first card hasn't been selected
			if(cardOneSelected == false)
			{
				//if this card isn't an ace
				if(this.ace == false)
				{					
					this.click = true;
					cardOneSelected = true;
					cardOne = this.getCard();
				}
			}
			else if(cardOneSelected == true)
			{				
				if(this.ace == true)
				{						
					this.click = true;
					cardTwo = this.getCard();
					
					//cardTest is the index of the cardTwo
					cardTest = cardPanelOrder.indexOf(cardTwo);

					//check if your putting a "2" in a first blank space 
					if(cardOne % 13 == 1 & cardTest % 13 == 0)
					{
						swapCards();
					}
					else
					{
						//cardTest is the index of the card before the blank
						cardTest = cardPanelOrder.indexOf(cardTwo)-1;
						cardTest = cardPanelOrder.get(cardTest);
						
						//Check if the first card is sequential to the card before the space
						if(cardOne == cardTest + 1)
						{
							swapCards();
						}
					}
					resetValues();
					resetScreen();
					checkAce();
					checkWin();
				}
				else
				{		
					//Clear other boarders if there is any
					clearBoarders();
					
					cardOneSelected = false;
				}
			}
		}
		else
		{
			this.click = false;
			cardOneSelected = false;
			cardOne = -5;
		}

		this.repaint();
	}

	public void swapCards() 
	{
		cardOne = cardPanelOrder.indexOf(cardOne);
		cardTwo = cardPanelOrder.indexOf(cardTwo);

		Collections.swap(cardPanelOrder, cardOne, cardTwo);
	}

	public void resetValues() 
	{
		cardOneSelected = false;
		cardOne = -5;
		cardTwo = -5;
		
		for(int i=0; i<52; i++)
		{
			CardPanel.cardPanel[i].click = false;
			CardPanel.cardPanel[i].ace = false;
		}
	}

	public void resetScreen() 
	{
		LineUp.backPanel.removeAll();
		Menu.displayCardPanels();
		LineUp.backPanel.add(LineUp.buttonPanel);
		Menu.addClock();
	}

	public static void checkAce() 
	{
		for(int i=0; i<52; i++)
		{
			if(CardPanel.getCardValue(i) % 13 == 0)
			{
				CardPanel.cardPanel[i].setAce();
				CardPanel.cardPanel[i].repaint();
			}
		}
	}
	
	//Checks if all rows are sequential 
	public static void checkWin() 
	{
		boolean win = true;
		
		for(int i = 0; i<40; i+=13)
		{
			//Starts by seeing if the first card is a 2
			if(cardPanelOrder.get(i)%13 == 1)
			{
				//if so checks if the next card 
				for(int j = i+1; j< i+12; j++)
				{
					if(cardPanelOrder.get(j) != cardPanelOrder.get(j-1) + 1)
					{
						win = false;
					}
				}
			}
			else
			{
				win = false;
			}
		}
		//if after the checks they are all sequntial to one another
		if(win == true)
		{
			win();
		}
	}

	public static void leaderBoard() 
	{
		//LeaderBoard 
		leaderFrame = new LeaderBoard();
		leaderFrame.setVisible(true);
		leaderFrame.setLocationRelativeTo(LineUp.gameFrame);
	}

	public static void win() 
	{
		Menu.stopClock();
		
		ButtonPanel.enableReshuffle(false);
		JOptionPane.showMessageDialog(null, "Congratulations. You Win!!!");
		
		String name = Validation.checkStringInput(JOptionPane.showInputDialog("Enter Your Name: "));
		
		AddHighScore.addHighScore(name, (3 - ButtonPanel.getReshufflesLeft()), ClockLabel.getTimetaken());
		
		leaderBoard();
	}
		
	//A method to clear boarders of selected cards
	public static void clearBoarders()
	{
		for(int i = 0; i<cardPanel.length; i++)
		{
			cardPanel[i].click = false;
			cardPanel[i].repaint();
		}
	}
	
	
	@Override
	public void mouseReleased(MouseEvent e)	{}	
	@Override
	public void mouseEntered(MouseEvent e) 	{}
	@Override
	public void mousePressed(MouseEvent e) 	{}
	@Override
	public void mouseExited(MouseEvent e) 	{}
}