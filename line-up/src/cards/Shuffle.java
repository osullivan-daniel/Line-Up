package cards;

import java.util.ArrayList;
import java.util.Collections;

public class Shuffle
{	
	//Shuffle Arraylists	
	public static void shuffleCardPanels(ArrayList<Integer> tempShuffle) 
	{
		for(int i = 0; i < 5; i++)
			Collections.shuffle(tempShuffle);
	}
}