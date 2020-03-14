import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Dimension;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Scores extends JPanel
{
	private Fibgame parent; // fibgame, the scores belong to
	private JPanel buttons;
	private JButton restartButton;
	private JButton quitButton;
	//private JTextArea scoreboard;
	private JLabel scoreboard;
	
	void displayScores(int pts)
	{
		// read scores from scores.log (there are 10 of them)
		String[][] scs = new String[10][2];
		try
		{
			Scanner str = new Scanner(new File("./scores.log"));
			for (int i = 0; i < 10; i++)
			{
				scs[i] = str.nextLine().split("\t");
			}
			str.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		String scores = "";
		for (int i = 0; i < 10; i++)
			scores += scs[i][0] + "\t" + scs[i][1] + "\n";
		
		int place = 10; // > 9; remains if the new score below the scoreboard
		
		// if new highscore
		if (pts > Integer.parseInt(scs[9][1]))
		{
			// determine the place
			place = 9;
			while (pts > Integer.parseInt(scs[place-1][1])) // place is too low
			{
				place--; // move the "pointer" to higher place
				if (place == 0) break; // 1st place!
			}
			
			// insert where it should be
			for (int i = 8; i >= place; i--)
			{
				scs[i+1][0] = new String(scs[i][0]); // drop place to lower
				scs[i+1][1] = new String(scs[i][1]);
			}
			scs[place][0] = "CuSO4";
			scs[place][1] = "" + pts;
		}
		
		// prepare text for writing to file
		String textToWrite = "";
		for (int i = 0; i < 10; i++) // for every place
			textToWrite += scs[i][0] + "\t" + scs[i][1] + "\n";
		
		// write to scores.log
		try
		{
			FileWriter str = new FileWriter(new File("./scores.log"), false);
			str.write(textToWrite);
			str.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		// prepare text for displaying in the scoreboard
		String textDisplayed = "<html><style>p{font-size:40px; font-family:\"Lucida Console\"}</style>";
		for (int i = 0; i < 10; i++) // for every place
		{
			if (i == place) textDisplayed += "<font color=\"red\">";
			textDisplayed += "<p>" + scs[i][0] + " ";
			// add dots to fill places up to 30
			for (int j = 30; j > scs[i][0].length(); j--)
				textDisplayed += ".";
			textDisplayed += " " + scs[i][1] + "</p>";
			if (i == place) textDisplayed += "</font>";
		}
		
		scoreboard.setText(textDisplayed);
	}
	
	public Scores(Fibgame f)
	{
		parent = f;
		this.setLayout(new BorderLayout());
		
		//scoreboard = new JTextArea(""); // initiate scorebord text area
		//scoreboard.setFont(new Font("Courier", Font.BOLD, 50));
		//scoreboard.setLineWrap(false);
		//scoreboard.setWrapStyleWord(false);
		//scoreboard.setEditable(false);
		scoreboard = new JLabel("");
		this.add(scoreboard, BorderLayout.CENTER);
		
		buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 0, 50, 50));
		buttons.setPreferredSize(new Dimension(1400, 100));
		this.add(buttons, BorderLayout.PAGE_END);
		
		restartButton = new JButton("RESTART (not working for now; to be programmed :) )");
		restartButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				// TODO: restart
			}
		});
		buttons.add(restartButton);
		
		quitButton = new JButton("QUIT");
		quitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0);
			}
		});
		buttons.add(quitButton);
		
		//this.setLayout(new GridLayout(1, 2, 50, 50));
		//this.setLayout(new FlowLayout());
	}
}
