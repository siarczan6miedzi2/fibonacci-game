import java.awt.event.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*; // for currentTimeMillis
import java.io.*;

public class Fibgame
{
	static final int WIDTH = 1600;
	static final int HEIGHT = 900;

	static NumberGrid numberGrid;
	
	Fibgame()
	{
		JFrame f = new JFrame("Fibonacci game");
		f.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setResizable(false);
		NumberGrid grid = new NumberGrid(6);
		grid.setBounds(50, 50, HEIGHT-100, HEIGHT-100);
		f.add(grid);
		f.pack();

		f.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new Fibgame();
	}
}
