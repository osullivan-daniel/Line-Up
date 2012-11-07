package gui;


import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.JLabel;


public class ClockLabel extends JLabel implements ActionListener 
{
	 private long start;
	 private long time;
	 private int minutes;
	 private int seconds;
	 private int miliSeconds;
	 private Timer t;
	 private String labelText;
     private static String timetaken;
     
	  public ClockLabel() 
	  {
	    super("00:00:000");
	 		
	    //Use system time to determine Start
	    start = System.currentTimeMillis();
	    //Time taken will be current time minus start time
        time = System.currentTimeMillis() - start;
        
        //Timer delay in milliseconds(1) and its actionlistener(this)
	    t = new Timer(1, this);
	  }

	  public void start()
	  {
		  t.start();
	  }
	 
	  public void stop()
	  {
		  t.stop();
		  timetaken = (String.format("%02d:%02d:%03d", minutes, seconds, miliSeconds));
	  }
	  
	  public void actionPerformed(ActionEvent ae) 
	  {
		  this.setBounds(700,600,450,50);
		  
		  this.setHorizontalAlignment(JLabel.LEFT);
		  this.setVerticalTextPosition(JLabel.CENTER);
		  this.setFont(new Font("Biondi", Font.ITALIC ,15));
		  this.setForeground(Color.white);
		  
		  time = (System.currentTimeMillis() - start);
		  minutes = (int)(time/60000)%60000;;
	      seconds = (int)(time/1000)%60;
	      miliSeconds = (int)(time%1000);
		  	      
	      labelText  = (String.format("Reshuffles left: %02d", ButtonPanel.getReshufflesLeft()));
	      labelText += ("                    ");
	      labelText += (String.format("%02d:%02d:%03d", minutes, seconds, miliSeconds));
	      
	      this.setText(labelText);
	  }

	public static String getTimetaken() 
	{
		return timetaken;
	}
}