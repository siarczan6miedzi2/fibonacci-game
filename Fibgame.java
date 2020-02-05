import java.awt.event.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*; // for currentTimeMillis
import java.io.*;
import javax.swing.SwingUtilities;

public class Fibgame
{
	static final int WIDTH = 1600;
	static final int HEIGHT = 900;

	private static NumberGrid grid;
	
	public Fibgame()
	{
		JFrame f = new JFrame("Fibonacci game");
		f.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setResizable(false);
		grid = new NumberGrid(6);
		grid.setBounds(50, 50, HEIGHT-100, HEIGHT-100);
		f.add(grid);
		f.pack();

		f.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new Fibgame();
				grid.resetState();
			}
		});
	}
}
