import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*; // for currentTimeMillis
import java.io.*;
import javax.swing.SwingUtilities;

public class Fibgame extends JFrame
{
	static final int WIDTH = 1600;
	static final int HEIGHT = 900;
	
	private static JFrame f;
	private static NumberGrid grid;
	private static JLabel points;
	
	public Fibgame()
	{
		f = new JFrame("Fibonacci game");
		f.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		
		grid = new NumberGrid(7, this);
		grid.setBounds(50, 50, HEIGHT-100, HEIGHT-100);
		f.add(grid);
		
		points = new JLabel("0");
		points.setBounds(3*WIDTH/5, 50, WIDTH/4, HEIGHT/4);
		points.setFont(new Font("Courier", Font.BOLD, 100));
		points.setForeground(Color.RED);
		f.add(points);
		
		f.pack();
		f.setVisible(true);
	}
	
	public void setPoints(int i)
	{
		points.setText("" + i);
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
