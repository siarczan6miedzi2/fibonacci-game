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
		{240, 240, 240}, //  1 -> graying
		{210, 210, 210}, //  2
		{170, 170, 170}, //  3
		{150, 150, 200}, //  4 -> blueing
		{120, 120, 240}, //  5 
		{ 80,  80, 255}, //  6
		{ 40, 120, 255}, //  7 -> cyaning
		{  0, 160, 255}, //  8
		{  0, 190, 255}, //  9
		{  0, 220, 255}, // 10
		{  0, 255, 255}, // 11
		{  0, 255, 200}, // 12 -> greening
		{  0, 255, 120}, // 13
		{  0, 255,   0}, // 14
		{150, 255,   0}, // 15 -> yellowing
		{190, 255,   0}, // 16
		{220, 255,   0}, // 17
		{255, 255,   0}, // 18
		{255, 220,   0}, // 19 -> redding
		{255, 180,   0}, // 20
		{255, 140,   0}, // 21
		{255, 100,   0}, // 22
		{255,   0,   0}, // 23
		{255,   0, 120}, // 24 -> magentaing
		{255,   0, 180}, // 25
	};
	
	private void reset()
	{
		//System.out.print("dupa");
		this.setText("" + GeneralMethods.fib(level+2));
		// recolorize
		int[] colorset = {0, 0, 0};
		if (level > 25)
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