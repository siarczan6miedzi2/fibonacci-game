import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

public class NumberGrid extends JPanel //implements ActionListener
{
	// macros
	public static final int WAITING = 0; // no buttons clicked
	public static final int CLICKED = 1; // 1 button clicked
	public static final int ACTION = 2;  // 2 buttons clicked - ready for act
	
	private int fld1i; // coords of 1st clicked field
	private int fld1j;
	private int fld2i; // coords of 2nd clicked field
	private int fld2j;
	
	private NumberField[][] field;
	private int state;
	
	public NumberGrid(int d)
	{
		state = WAITING;
		fld1i = fld1j = fld2i = fld2j = 0;
//		System.out.println(state);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		field = new NumberField[d][d];
		for (int i = 0; i < d; i++)
			for (int j = 0; j < d; j++)
			{
				final int ii = i;
				final int jj = j;
				field[i][j] = new NumberField(0);
				this.add(field[i][j]);
				field[i][j].addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						buttonClicked(ii, jj);
					}
				});
			}
		this.setLayout(new GridLayout(d, d));
	}
	
	private void buttonClicked(final int i, final int j)
	{
		this.state++; // WAITING -> CLICKED, CLICKED -> ACTION
		if (this.state == CLICKED)
		{
			field[i][j].setBackground(Color.RED);
			fld1i = i;
			fld1j = j;
		}
		else // this.state == ACTION
		{
			fld2i = i;
			fld2j = j;
			merge();
			resetState();
		}
	}
	
	public int getState()
	{
		return this.state;
	}
	
	public void resetState()
	{
		this.state = WAITING;
		for (int i = 0; i < field.length; i++)
			for (int j = 0; j < field.length; j++)
			{
				field[i][j].setBackground(Color.LIGHT_GRAY);
			}
	}
	
	private boolean vicinalFields()
	{
		if (fld1i == fld2i && ((fld1j - fld2j) == 1 || (fld2j - fld1j) == 1)) return true;
		if (fld1j == fld2j && ((fld1i - fld2i) == 1 || (fld2i - fld1i) == 1)) return true;
		return false;
	}
	
	private int mergeable()
	{
		int lvl1 = field[fld1i][fld1j].getLevel();
		int lvl2 = field[fld2i][fld2j].getLevel();
		if (lvl1 == 0 && lvl2 == 0) return 1; // mergeable (add 1 level)
		if (lvl1 - lvl2 == 1) return 2;       // mergeable (add 2 levels)
		if (lvl2 - lvl1 == 1) return 1;       // mergeable (add 1 level)
		return 0;                             // non mergeable
	}
	
	private void merge()
	{
		int mergeflag = mergeable();
		if (vicinalFields() && mergeflag > 0)
		{
			field[fld2i][fld2j].upgrade(mergeflag); // 1 level for default order, 2 levels for reversed order
		}
	}
/*	
	public void actionPerformed(ActionEvent event)
	{
		
	}*/
}
