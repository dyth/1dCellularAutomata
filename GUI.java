package twodimensional;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

class World extends JComponent {
	int cellWidth;
	ArrayList<int[]> display;
	
	public World(int cellWidth, ArrayList<int[]> display) {
		this.cellWidth = cellWidth;
		this.display = display;
	}
	
	public void paint(Graphics graphics) {
		for (int y = 0; y < display.size(); y++) {
			int[] row = display.get(y);
			for (int x = 0; x < row.length; x++) {
				if (row[x] == 1)
					graphics.fillRect(x*cellWidth, y*cellWidth, cellWidth, cellWidth);
			}
		}
	}
	
}


public class GUI extends JFrame {
	private int cellWidth;
	private JFrame frame;
	
	public GUI(int x, int y, int cellWidth) {
		this.cellWidth = cellWidth;
        frame = new JFrame("Cellular Automata");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(x, y);
        frame.setVisible(true);
	}
	
	public void draw(ArrayList<int[]> display) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(new World(cellWidth, display));
		frame.setVisible(true);
	}

}
