package sqlLiteLeaderBoard;

public class AddHighScore
{
	public static void addHighScore(String name, int shuffles, String time) 
	{
        LeaderBoardJDBCDAO leaderBoard = new LeaderBoardJDBCDAO();
        HighScore highScore = new HighScore();
        highScore.setName(name);
        highScore.setShuffles(shuffles);
        highScore.setTime(time);
       
        leaderBoard.add(highScore);
	}
}