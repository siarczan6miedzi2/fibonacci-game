import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*; // for currentTimeMillis
import java.io.*;

public class NumberField extends JButton
{
	private void reset()
	{
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
	}
	
	public NumberField()
	{
		super();
		reset();
	}
	
	public NumberField(String s)
	{
		super(s);
		reset();
	}
}