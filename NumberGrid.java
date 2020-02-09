import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import javax.swing.Timer;

public class NumberGrid extends JPanel // implements ActionListener
{
	// macros
	public static final int WAITING = 0;  // no buttons clicked
	public static final int CLICKED = 1;  // 1 button clicked
	public static final int ACTION = 2;   // 2 buttons clicked - ready for act
	
	private int fld1i; // coords of 1st clicked field
	private int fld1j;
	private int fld2i; // coords of 2nd clicked field
	private int fld2j;
	
	private NumberField[][] field; // core of the game
	
	private int state;
	private int level;
	
	public NumberGrid(int d)
	{
		state = WAITING;
		fld1i = fld1j = fld2i = fld2j = 0;
		this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		level = 0; // start with lvl 0, which corresponds to lvl 3 of the maximum field
		field = new NumberField[d][d];
		for (int i = 0; i < d; i++)
			for (int j = 0; j < d; j++)
			{
				final int ii = i;
				final int jj = j;
				field[i][j] = new NumberField(GeneralMethods.randMax(3)); // start with fields of levels up to 3
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
		if (field[i][j].getListening() == false) return; // do not act when you should not
		state++; // WAITING -> CLICKED, CLICKED -> ACTION
		if (state == CLICKED)
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
	
	private int mergeable() // used in merging
	{
		int lvl1 = field[fld1i][fld1j].getLevel();
		int lvl2 = field[fld2i][fld2j].getLevel();
		if (lvl1 == 0 && lvl2 == 0) return 1; // mergeable (add 1 level)
		if (lvl1 - lvl2 == 1) return 2;       // mergeable (add 2 levels)
		if (lvl2 - lvl1 == 1) return 1;       // mergeable (add 1 level)
		return 0;                             // non mergeable
	}
	
	private boolean mergeable(int fld1i, int fld1j, int fld2i, int fld2j) // used only to check if game over
	{
		int lvl1 = field[fld1i][fld1j].getLevel();
		int lvl2 = field[fld2i][fld2j].getLevel();
		if (lvl1 == 0 && lvl2 == 0) return true; // mergeable
		if (lvl1 - lvl2 == 1) return true;       // mergeable
		if (lvl2 - lvl1 == 1) return true;       // mergeable
		return false;                            // non mergeable
	}
	
	private void merge()
	{
		int mergeFlag = mergeable();
		if (vicinalFields() && mergeFlag > 0)
		{
			field[fld2i][fld2j].upgrade(mergeFlag); // 1 level for default order, 2 levels for reversed order
			falldown(fld1i, fld1j);                 // first clicked field generates the hole
		}
	}
	
	boolean gameOver()
	{
		// check in verses
		for (int i = 0; i < field.length; i++)
			for (int j = 0; j < field.length-1; j++)
				if (mergeable(i, j, i, j+1)) return false;
		// check in columns
		for (int i = 0; i < field.length-1; i++)
			for (int j = 0; j < field.length; j++)
				if (mergeable(i, j, i+1, j)) return false;
		return true;
	}
	
	// IDEA: the function delay, instead of the [command] should receive the whole function as an argument.
	// This may be done by lambdas or passing a function as an argument, depends on the specifications
	// of JAVA language, which I don't know yet. To be considered later, as it is not crucial for the
	// program to work, but would enhance the code readability
	private void delay(int ms, int x, int y, String command)
	{
		Timer timer = new Timer(ms, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (command == "visible") // show field[x][y]
				{
					field[x][y].setVisible(true);
					field[x][y].getParent().revalidate();
					field[x][y].getParent().repaint();
				}
				else if (command == "invisible") // unshow field[x][y]
				{
					field[x][y].setVisible(false);
					field[x][y].getParent().revalidate();
					field[x][y].getParent().repaint();
				}
				else if (command == "steal") // steal properties from a field above
				{
					field[x][y].steal(field[x-1][y]);
				}
				else if (command == "renew") // renew the field
				{
					// every grid level which is a square, extends possible levels for new-created fields by 1
					field[x][y].renew(GeneralMethods.randMax(3+GeneralMethods.floorSqrt(level)));
					field[x][y].setVisible(true);
				}
			}
		});
		timer.setRepeats(false);
		timer.start();
	}
	
	private void delay(int ms, String command)
	{
		Timer timer = new Timer(ms, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (command == "halt") // halt clickability (during animations)
					for (int i = 0; i < field.length; i++)
						for (int j = 0; j < field.length; j++)
							field[i][j].setListening(false);
				else if (command == "resume") // resume clickability (after animations)
					for (int i = 0; i < field.length; i++)
						for (int j = 0; j < field.length; j++)
							field[i][j].setListening(true);
				else if (command == "recalculate") // recalculate the level
				{
				// the recalculation may also consider only the new-formed field,
				// as it is the only one, which can enhance the grids level.
				// To be considered in case of need of serious optimization
					int lvl = 0;
					for (int i = 0; i < field.length; i++)
						for (int j = 0; j < field.length; j++)
							if (field[i][j].getLevel() > lvl)
								lvl = field[i][j].getLevel();
					level = lvl - 3; // grid level is always 3 less than max field level
				}
				else if (command == "check") // check for game over
				{
					if (gameOver()) // simply disable all buttons (for now, ofc)
					{
						for (int i = 0; i < field.length; i++)
							for (int j = 0; j < field.length; j++)
								field[i][j].setEnabled(false);
					}
				}
			}
		});
		timer.setRepeats(false);
		timer.start();
	}
	
	private void falldown(int x, int y)
	{
		int delayTime = 150;                     // delay time [ms]
		delay(0, "halt");                        // halt clickability
		for (int i = 0; i < x; i++)
		{
			delay(delayTime*i, x-i, y, "invisible");   // field[i][y] vanishes
			delay(delayTime*i, x-i, y, "steal");       // field[i][y] steals properties of field[i-1][y]
			delay(delayTime*(i+1), x-i, y, "visible"); // field[i][y] reappeares after 300 ms
		}
		// finally, cope with the top field
		delay(delayTime*x, 0, y, "invisible");
		delay(delayTime*(x+1), 0, y, "renew");
		delay(delayTime*(x+1), "resume");              // resume clickability
		delay(delayTime*(x+1), "recalculate");         // recalculate the level
		delay(delayTime*(x+1)+50, "check");            // check for gameover (50 ms just to be sure, everything else has finished)
	}
}
