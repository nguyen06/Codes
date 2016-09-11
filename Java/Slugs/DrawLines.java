import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * DrawLines is a class created specifically for UVic CSC110
 * students to visualize lines created by their assignment programs.
 * Modifications are encouraged but should include credit to the
 * original author.
 * Last update: 2014-10-21
 * 
 * @author Bultena
 * @version 1.0.0
 */

public class DrawLines extends JPanel {

	private int[][] lines;

/**
 * Used to check the lines by printing out all the points.
 */
	private void debug() {
		for (int i=0; i<lines.length; i++) {
			for (int j=0; j<4; j++) {
				System.out.print(lines[i][j]+" ");
			}
			System.out.println();
		}
	}

/**
 * Creates a panel and sets it up to draw.
 * @param canvasSize The size of the actual frame.
 * @param localLines An array of sets of 4 integers:
 *		representing (x1,y1),(x2,y2), the two points
 *		that define a line segment.
 */
	public DrawLines(Dimension canvasSize, int[][] localLines) {
		super();
		this.lines = localLines;
		this.setPreferredSize(canvasSize);
		repaint();
	}

/**
 * Draws the lines.
 * @param g The paint brush for this panel.
 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i=0; i<lines.length; i++) {
			g.drawLine(lines[i][0],lines[i][1],lines[i][2],lines[i][3]);
		}
	}

/**
 * Creates the frame to hold the canvas.
 */
	public void showFrame() {
		JFrame frame = new JFrame("Rendering of Lines");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(this);
		frame.pack();
		frame.setVisible(true);
	}

/**
 * A helper method to determine the number of actual lines in a file.
 * @param filename The name of the text file to check.
 * @return The number of lines in the file.
 */
	public static int numLines(String filename) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(filename));
		int count = 0;
		while(scan.hasNextLine()) {
			scan.nextLine();
			count++;
		}
		scan.close();
		return count;
	}

/**
 * Determines the name of the file that contains the lines, builds an array 
 * representing those lines and instantiates the drawing of them.
 */
	public static void main(String[] args) {
		int[][] lines;
		int numLines = 0;
		Scanner lineTokens;
		Scanner in = new Scanner(System.in);
		System.out.print("What is the name of the input file? ");
		String filename = in.next();
		try {
			numLines = numLines(filename);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			return;
		}
		in.close();
		lines = new int[numLines-1][];
		try {
			lineTokens = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to continue; lines file not readable");
			return;
		}
		Dimension canvasSize = new Dimension(lineTokens.nextInt(), lineTokens.nextInt());
		for (int i=0; i<lines.length; i++) {
			lines[i] = new int[4];
			lines[i][0] = lineTokens.nextInt();
			lines[i][1] = lineTokens.nextInt();
			lines[i][2] = lineTokens.nextInt();
			lines[i][3] = lineTokens.nextInt();
		}
		lineTokens.close();
		DrawLines s = new DrawLines(canvasSize,lines);
		s.showFrame();
	}
}

		
