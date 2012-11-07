package sqlLiteLeaderBoard;

public class HighScore 
{
	String name;
	int shuffles;
	String time;
	
	public HighScore(){}
	
	public HighScore(String name, int shuffles, String time)
	{
		this.name = name;
		this.shuffles = shuffles;
		this.time = time;
	}
	
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getShuffles() 
	{
		return shuffles;
	}

	public void setShuffles(int shuffles) 
	{
		this.shuffles = shuffles;
	}

	public String getTime() 
	{
		return time;
	}

	public void setTime(String time) 
	{
		this.time = time;
	}
}