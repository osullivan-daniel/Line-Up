package leaderBoard;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;

import lineUp.LineUp;

import gui.CardPanel;


public class LeaderBoard extends JFrame 
{
	protected JTable table;
	protected LeaderBoardModel tableModel;
	static JButton ok;

	public LeaderBoard() 
	{
		super("Top 10");
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(361, 425);
		setVisible(true);
		setResizable(false);
		
		setLocationRelativeTo(LineUp.gameFrame);

		tableModel = new LeaderBoardModel();

		table = new JTable();
	  	table.setAutoCreateColumnsFromModel(false);
	  	table.setModel(tableModel);
	  
	  	table.setFont(new Font("Serif", Font.BOLD, 20));
	  	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	  
	  	for(int i = 0; i<10; i++)
	  	{
	  		table.setRowHeight(i, 30);
		}

	  	for (int k = 0; k < LeaderBoardModel.m_columns.length; k++) 
	  	{
	  		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	  		renderer.setHorizontalAlignment(LeaderBoardModel.m_columns[k].m_alignment);
	  		TableColumn column = new TableColumn(k, LeaderBoardModel.m_columns[k].m_width, renderer, null);
	  		table.addColumn(column);
	  	}

	  	ok = new JButton("OK");
	  	okButton();
	  	this.add(ok);
	  	    
    
	  	JTableHeader header = table.getTableHeader();
	  	header.setFont(new Font("Serif", Font.BOLD, 20));
	  	header.setUpdateTableInRealTime(false);

	  	JScrollPane ps = new JScrollPane();
	  	ps.getViewport().add(table);
	  	getContentPane().add(ps, BorderLayout.CENTER);
	  	ps.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	  	setVisible(true);
	}
  
	public static void okButton()
	{
		ok.setBounds(145, 350, 71, 40);
		ok.setVisible(true);
		
		ok.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				CardPanel.leaderFrame.setVisible(false);
				CardPanel.leaderFrame.dispose();
			}
		});
	}
}