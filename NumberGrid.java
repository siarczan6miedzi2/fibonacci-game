import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

public class NumberGrid extends JPanel
{
	private NumberField[][] field;
	
	public NumberGrid(int d)
	{
		this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		field = new NumberField[d][d];
		for (int i = 0; i < d; i++)
			for (int j = 0; j < d; j++)
			{
				field[i][j] = new NumberField("" + i + " " + j);
				this.add(field[i][j]);
			}
		this.setLayout(new GridLayout(d, d));
	}
}
