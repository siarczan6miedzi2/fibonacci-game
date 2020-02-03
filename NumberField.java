import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*; // for currentTimeMillis
import java.io.*;

public class NumberField extends JButton
{
	private int level;
	
	private void reset()
	{
		this.setText("" + GeneralMethods.fib(level+2));
	}
	
	public NumberField(int no)
	{
		super();
		level = no;
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		this.setFont(new Font("Courier", Font.BOLD, 30));
		reset();
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void upgrade(int i)
	{
		level += i;
		this.reset();
		//System.out.println("dupa");
	}
/*	
	public NumberField()
	{
		super();
		reset();
	}
	
	public NumberField(String s)
	{
		super(s);
		reset();
	}*/
}