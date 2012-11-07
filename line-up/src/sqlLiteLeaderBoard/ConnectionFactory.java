package sqlLiteLeaderBoard;

import java.sql.Connection;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;


public class ConnectionFactory 
{
	private static ConnectionFactory connectionFactory = null;
	private static final String driverClassName = "org.sqlite.JDBC";
	
	private ConnectionFactory() 
	{
		try 
        {
			Class.forName(driverClassName);
        } 
        catch (ClassNotFoundException e) 
        {
        	e.printStackTrace();
        }
	}
	 
	public Connection getConnection() throws SQLException 
	{
		Connection conn = null;
		SQLiteConfig config =  new SQLiteConfig();
		config.setReadOnly(false);
		config.enableFullSync(true);
		SQLiteDataSource ds = new  SQLiteDataSource(config);
		
		java.io.File currentDir = new java.io.File("");
		ds.setUrl("jdbc:sqlite:" + currentDir.getAbsolutePath() + ("/Program Files/leaders.db").toString());
		
		conn = ds.getConnection();
		
		return conn;
	}

	public static ConnectionFactory getInstance() 
	{
		if (connectionFactory == null) 
		{
			connectionFactory = new ConnectionFactory();
        }
		 
        return connectionFactory;
	}
}