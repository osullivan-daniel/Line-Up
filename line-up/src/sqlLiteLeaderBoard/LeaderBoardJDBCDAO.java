package sqlLiteLeaderBoard;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.ArrayList;


public class LeaderBoardJDBCDAO 
{
	ResultSet resultSet = null;
	Connection connection = null;
    PreparedStatement ptmt = null;

    private Connection getConnection() throws SQLException 
    {
            Connection conn;
            conn = ConnectionFactory.getInstance().getConnection();
            return conn;
    }
    
    public void add(sqlLiteLeaderBoard.HighScore highScore)
    {
    	try
    	{
    		String queryString = "INSERT INTO high_score(name, shuffles, time) VALUES(?,?,?)";
    		connection = getConnection();
    		
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, highScore.getName());
            ptmt.setInt(2, highScore.getShuffles());
            ptmt.setString(3, highScore.getTime());
            ptmt.executeUpdate();
            System.out.println("Data Added Successfully");
    	} 
    	catch (SQLException e) 
    	{
            e.printStackTrace();
    	} 
    	finally 
    	{
            try 
            {
            	if (ptmt != null)
            	{
            		ptmt.close();
            	}
                if (connection != null)
                {
                	connection.close();
                }
            }
            catch (SQLException e) 
            {
            	e.printStackTrace();
            } 
            catch (Exception e) 
            {
            	e.printStackTrace();
            }
    	}
    }
    
    public ArrayList<String> getName()
    {
    	ArrayList<String> name = new ArrayList<String> ();
    	
    	try
        {
        	String queryString = "SELECT name FROM high_score ORDER BY time asc, shuffles";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            
            int total = 0;
            
            while (resultSet.next() && total < 10) 
            {           	
            	name.add(resultSet.getString("name"));
            }
        } 
        catch (SQLException e) 
        {
        	e.printStackTrace();
        } 
        finally 
        {
        	try 
        	{
        		if (resultSet != null)
        		{
        			resultSet.close();
        		}
                if (ptmt != null)
                {
                	ptmt.close();
                }
                if (connection != null)
                {
                	connection.close();
                }
        	}
        	catch (SQLException e) 
        	{
            	e.printStackTrace();
            }
        	catch (Exception e) 
        	{
            	e.printStackTrace();
            }

        }
		return name;
    }

    public ArrayList<String> getShuffles() 
	{
    	ArrayList<String> shuffles = new ArrayList<String> ();
    	try
        {
        	String queryString = "SELECT shuffles FROM high_score ORDER BY time asc, shuffles";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            
            int total = 0;
            
            while (resultSet.next() && total < 10) 
            {     	
            	shuffles.add(resultSet.getString("shuffles"));
            	
            	total++;
            }
        } 
        catch (SQLException e) 
        {
        	e.printStackTrace();
        } 
        finally 
        {
        	try 
        	{
        		if (resultSet != null)
        		{
        			resultSet.close();
        		}
                if (ptmt != null)
                {
                	ptmt.close();
                }
                if (connection != null)
                {
                	connection.close();
                }
        	}
        	catch (SQLException e) 
        	{
            	e.printStackTrace();
            }
        	catch (Exception e) 
        	{
            	e.printStackTrace();
            }

        }
		return shuffles;
	}

	public ArrayList<String> getTime() 
	{
		ArrayList<String> time = new ArrayList<String> ();
		
    	try
        {
        	String queryString = "SELECT * FROM high_score ORDER BY time asc, shuffles";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            
            int total = 0;
            
            while (resultSet.next() && total < 10) 
            {     	
            	time.add(resultSet.getString("time"));
            	
            	total++;
            }
        } 
        catch (SQLException e) 
        {
        	e.printStackTrace();
        } 
        finally 
        {
        	try 
        	{
        		if (resultSet != null)
        		{
        			resultSet.close();
        		}
                if (ptmt != null)
                {
                	ptmt.close();
                }
                if (connection != null)
                {
                	connection.close();
                }
        	}
        	catch (SQLException e) 
        	{
            	e.printStackTrace();
            }
        	catch (Exception e) 
        	{
            	e.printStackTrace();
            }

        }
		return time;
	}
}