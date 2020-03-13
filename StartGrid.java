import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

public class StartGrid extends JPanel
{
	private JButton startButton;
	private JButton helpButton;
	private JButton quitButton;
	
	private Fibgame parent; // fibgame, the grid belongs to
	
	private void displayHelp()
	{
		JDialog helpDialog = new JDialog(parent, "help");
		
		helpDialog.setLayout(new GridLayout(1,1));
		helpDialog.setPreferredSize(new Dimension(parent.WIDTH-200,parent.HEIGHT-200));
		
		String helpText = "Click two adjacent tiles to merge them.\n\nOnly tiles with successive Fibonacci numbers can be merged.\n\n";
		helpText += "With every merge you get points, equaling the value of new-formed tile - the sum of values of merged tiles.\n\n";
		helpText += "Try to get as many points as possible. Good luck!";
		JTextArea helpTextDisplay = new JTextArea(helpText);
		helpTextDisplay.setFont(new Font("Courier", Font.BOLD, 40));
		helpTextDisplay.setLineWrap(true);
		helpTextDisplay.setWrapStyleWord(true);
		helpTextDisplay.setEditable(false);
		helpDialog.add(helpTextDisplay);
		
		helpDialog.pack();
		helpDialog.setLocationRelativeTo(null);
		helpDialog.setVisible(true);
	}
	
	public StartGrid(Fibgame f)
	{
		parent = f;
		
		startButton = new JButton("START");
		startButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				parent.showGame(true);
				parent.showStart(false);
			}
		});
		
		this.add(startButton);
		
		helpButton = new JButton("HELP");
		helpButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				displayHelp();
			}
		});
		this.add(helpButton);
		
		quitButton = new JButton("QUIT");
		quitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0);
			}
		});
		this.add(quitButton);
		
		this.setLayout(new GridLayout(2, 0, 100, 100));
	}
}
