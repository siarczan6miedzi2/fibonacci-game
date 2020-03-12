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
	private boolean listenerFlag;
	
	private final int[][] colors =
	{
		{255, 255, 255}, //  0 -> white
		{210, 210, 210}, //  1 -> graying
		{150, 150, 200}, //  2 -> blueing
		{120, 120, 240}, //  3 
		{ 80,  80, 255}, //  4
		{  0, 160, 255}, //  5 -> cyaning
		{  0, 210, 255}, //  6
		{  0, 255, 255}, //  7
		{  0, 255, 200}, //  8 -> greening
		{  0, 255, 100}, //  9
		{170, 255,   0}, // 10 -> yellowing
		{220, 255,   0}, // 11
		{255, 255,   0}, // 12
		{255, 220,   0}, // 13 -> redding
		{255, 140,   0}, // 14
		{255, 100,   0}, // 15
		{255,   0,   0}, // 16
		{255,   0, 120}, // 17 -> magentaing
		{255,   0, 180}, // 18
	};
	
	private void reset()
	{
		//System.out.print("dupa");
		this.setText("" + GeneralMethods.fib(level+2));
		// recolorize
		int[] colorset = {0, 0, 0};
		if (level > 18)
			colorset = new int[] {255, 0, 255}; // magenta
		else
			colorset = colors[level];
		this.setBackground(new Color(colorset[0], colorset[1], colorset[2]));
	}
	
	public NumberField(int no)
	{
		super();
		level = no;
		//this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		this.setFont(new Font("Courier", Font.BOLD, 30));
		this.listenerFlag = true;
		reset();
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public boolean getListening()
	{
		return listenerFlag;
	}
	
	public void setListening(boolean b)
	{
		listenerFlag = b;
	}
	
	public void upgrade(int i)
	{
		level += i;
		this.reset();
	}
	
	public void renew(int i)
	{
		level = i;
		this.reset();
	}
	
	public void steal(NumberField n)
	{
		level = n.level;
		this.reset();
	}
}