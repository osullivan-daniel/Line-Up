package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.util.Iterator;
import java.util.ArrayList;

import lineUp.LineUp;



import cards.Shuffle;


public class ButtonPanel extends JPanel
{
	FlowLayout flowLayout = new FlowLayout();
	
	int[] cardPanelOrderTemp;
	
	private static JButton start;
	private static JButton reshuffle;
	private static int reshufflesLeft;
	private static boolean isValidCard;
		
	public ButtonPanel()
	{
		setLayout(flowLayout);
		setBounds(0, 600, 1151, 50);
		setOpaque(false);
		
		start = new JButton("Start");
		reshuffle = new JButton("Reshuffle");
		
		startButton();
		reshuffleButton();
	}
		
	public void startButton() 
	{
		start.setPreferredSize(new Dimension(125, 35));
		add(start);
		start.setVisible(true);
		
		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				start.setVisible(false);
				resetReshuffle();	
				checkAce();
			
				reshuffle.setVisible(true);
				Menu.startClock();
			}
		});
	}
	
	public void reshuffleButton() 
	{
		reshuffle.setPreferredSize(new Dimension(125, 35));
		add(reshuffle);
		reshuffle.setVisible(false);
		
		reshuffle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(getReshufflesLeft()>0)
				{
					setReshufflesLeft();
					reshuffle();
					Menu.addClock();
					CardPanel.clearBoarders();
				}
				else
				{
					Menu.stopClock();
					JOptionPane.showMessageDialog(null,"No shuffles left.","Game Over", JOptionPane.INFORMATION_MESSAGE);
					reshuffle.setEnabled(false);
					
					Menu.enableNewGame(true);
					Menu.enableQuitGame(false);
				}
			}
		});
	}
	
	//Checks for the 4 aces and removes the aces
	public void checkAce() 
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
	
	private void reshuffle()
	{				
		cardPanelOrderTemp  = convertIntegers(CardPanel.cardPanelOrder);
		CardPanel.shuffleList = new ArrayList<Integer>();
		
		removeOrderedCards();
				
		removeAces();
		
		//Shuffle the ShuffleList
		Shuffle.shuffleCardPanels(CardPanel.shuffleList);
		
		for(int i = 0; i<52; i++)
		{
			if(cardPanelOrderTemp[i] == -5 )
			{
				cardPanelOrderTemp[i] = CardPanel.shuffleList.get(0);
				CardPanel.shuffleList.remove(0);
			}
		}
		
		CardPanel.cardPanelOrder.clear();
				
		for (int i = 0; i < cardPanelOrderTemp.length; i++)
		{
			CardPanel.cardPanelOrder.add(cardPanelOrderTemp[i]);
		}
		
		LineUp.backPanel.removeAll();
				
		Menu.displayCardPanels();
		
		LineUp.backPanel.add(LineUp.buttonPanel);
	}
	
	//Remove Cards in order from the ShuffleList & reserve their spot on the OrderList
	public void removeOrderedCards() 
	{
		//i is set to the start of the row
		for(int i = 0; i < 40; i+=13)
		{
			//checks if it is a 2
			if(cardPanelOrderTemp[i] % 13 == 1)
			{
				//Sets a boolean that determines if the card is sequential to the previous card
				isValidCard = true;
				
				//check through the row			
				for(int j = i; j < 13 + i; j++)
			    {
					while(isValidCard == true)
					{
						//check if its a card in the right spot
						if(cardPanelOrderTemp[j] % 13 == (j % 13) + 1)
						{
							//check if its a two because if it is suit is irrelevant 
							//because its the start of the row
							if(cardPanelOrderTemp[j] % 13 == 1)
							{
								isValidCard = true;
								j++;
							}
							else
							{
								//check if its the same suit as the previous card
								if(cardPanelOrderTemp[j] == (cardPanelOrderTemp[j -1]) + 1)
								{
									isValidCard = true;
									j++;
								}
								//if it isnt change boolean
								else
								{
									isValidCard = false;
								}
							}
						}
						//if it isnt a sequential numbered card change boolean
						else
						{
							isValidCard = false;
						}
					}
					//add cards to the shuffle list and temp order is filled with -5
					CardPanel.shuffleList.add(cardPanelOrderTemp[j]);  
					cardPanelOrderTemp[j] = -5;
				}
			}
			//if the first card isnt a 2 all cards in that row are moved to the 
			//shuffle list and temp order is filled with -5
			else
			{
				for(int j = i; j < 13 + i; j++)
			    {
					CardPanel.shuffleList.add(cardPanelOrderTemp[j]);  
					cardPanelOrderTemp[j] = -5;
			    }
			}
		}
	}

	//Puts the aces after any ordered cards on the OrderList
	public void removeAces() 
	{	
		//Loop to check the first card of each of line
		for(int i = 0; i<40; i+=13)
		{
			boolean found = false;
			
			//checks if the first card is a cad or an empty slot
			if(cardPanelOrderTemp[i]  == -5)
			{
				//if its empty put the ace there
				cardPanelOrderTemp[i] = CardPanel.shuffleList.get(CardPanel.shuffleList.indexOf(i));
				//remove it from the shuffle list
				CardPanel.shuffleList.remove(CardPanel.shuffleList.indexOf(i));
			}
			//if it isnt empty
			else
			{		
				//Loop to check every card in the line
				for(int j = i; j<13+i; j++)
				{
					//while you havent found an epty slot
					while(found == false)
					{
						//if you find the empty slot
						if(cardPanelOrderTemp[j] == -5)
						{
							//change the boolean 
							found = true;
							//insert the ace
							cardPanelOrderTemp[j] = CardPanel.shuffleList.get(CardPanel.shuffleList.indexOf(i));
							//remove it from the shuffle list
							CardPanel.shuffleList.remove(CardPanel.shuffleList.indexOf(i));
						}
						j++;
					}
				}
			}
		}
	}
	
	//Converts Array list to an array. 
	//Note: toArray() wont work because arraylist is of type Integer.
	public static int[] convertIntegers(ArrayList<Integer> integers)
	{
	    int[] ret = new int[integers.size()];
	    Iterator<Integer> iterator = integers.iterator();
	    
	    for (int i = 0; i < ret.length; i++)
	    {
	        ret[i] = iterator.next().intValue();
	    }
	    
	    return ret;
	}
	
	//Set weather reshuffle button is enabled
	public static void enableReshuffle(boolean b) 
	{
		reshuffle.setEnabled(b);
	}

	//get shuffles left
	public static int getReshufflesLeft() 
	{
		return reshufflesLeft;
	}
	
	//decrements left shuffles
	public static void setReshufflesLeft() 
	{
		reshufflesLeft--;
	}
	
	//reset shuffle counter
	public static void resetReshuffle()
	{
		reshufflesLeft = 3;
	}
}