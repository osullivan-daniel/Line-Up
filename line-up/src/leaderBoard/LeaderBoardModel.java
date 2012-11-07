package leaderBoard;

import java.util.Vector;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

import sqlLiteLeaderBoard.LeaderBoardJDBCDAO;

//
public class LeaderBoardModel extends AbstractTableModel 
{
	static final public ColumnData m_columns[] = { new ColumnData("", 30, JLabel.CENTER),
												   new ColumnData("Name", 80, JLabel.CENTER),
												   new ColumnData("Shuffles Used", 130, JLabel.CENTER),
												   new ColumnData("Time", 113, JLabel.CENTER) };
	protected Vector<LeaderBoardData> vector;

	
	public LeaderBoardModel() 
	{
		vector = new Vector<LeaderBoardData>();
		setDefaultData();
	}

	public void setDefaultData() 
	{
		vector.removeAllElements();

		LeaderBoardJDBCDAO lb = new LeaderBoardJDBCDAO();

		ArrayList<String> name = lb.getName();
		ArrayList<String> shuffles = lb.getShuffles();
		ArrayList<String> time = lb.getTime();
		
	    for(int i = 0; i<name.size(); i++)
	    {
	    	vector.addElement(new LeaderBoardData(i+1 , name.get(i), shuffles.get(i), time.get(i)));	
	    }
	    
	    for(int i = name.size(); i<10; i++)
	    {
	    	vector.addElement(new LeaderBoardData(i+1 , "-------", "--", "--:--:---"));
	    }
	}
	
	public int getRowCount() 
	{
	    return vector == null ? 0 : vector.size();
	}

	public int getColumnCount() 
	{
		return m_columns.length;
	}

	public String getColumnName(int column) 
	{
		return m_columns[column].m_title;
	}

	public boolean isCellEditable(int nRow, int nCol) 
	{
		return false;
	}

	public Object getValueAt(int nRow, int nCol) 
	{
		if (nRow < 0 || nRow >= getRowCount())
		{
			return "";
		}
	
		LeaderBoardData row = (LeaderBoardData) vector.elementAt(nRow);
	
		switch (nCol) 
		{
			case 0:
			{
				return row.rank;
			}
		
			case 1:
			{
				return row.name;
			}
			
			case 2:
			{
				return row.usedShuffles;
			}
			case 3:
			{
				return row.time;
			}
		}
		return "";
	}
}