package twodimensional;

import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.ArrayList;

public class RuleInput {
	
	// return binary array of string
		private static int[] stringToIntArray(String string) {
			final char[] CHARARRAY = string.toCharArray();
			final int LENGTH = CHARARRAY.length;
			int[] intArray = new int[LENGTH];
			for (int i = 0; i < LENGTH; i++)
				intArray[i] = (int) CHARARRAY[i] - 48;
			return intArray;
		}
		
		// update world
		private static int[] updateWorld(int[] world, int[] rule) {
			final int LENGTH = world.length;
			int[] newWorld = new int[LENGTH];
			for (int i = 0; i < LENGTH; i++) {
				newWorld[i] = rule[7 - 4*world[(i-1+LENGTH)%LENGTH] - 2*world[i] - world[(i+1)%LENGTH]];
			}
			return newWorld;
		}
		
		// update GUI array
		private static ArrayList<int[]> updateGUI(ArrayList<int[]> display, int[] world, int arraySize) {
			display.add(world);
			if (display.size() == arraySize)
				display.remove(0);
			return display;
		}
		
	// RuleNumber StartingWorldConfiguration
	// 30 00000000000000000000000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000
	public static void main(String[] args) throws IOException, InterruptedException {

		// read first input and get rule
		String ruleNumber = Integer.toBinaryString(Integer.parseInt(args[0]));
		while(ruleNumber.length() < 8)
			ruleNumber = 0 + ruleNumber;
		int[] rule = stringToIntArray(ruleNumber);
		
		// read second input and create world array
		int[] world = stringToIntArray(args[1]);

		// create graphical output
		int width = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
		int height = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
		int cellWidth = width / (world.length+1);
		int arraySize = height / cellWidth - 1;
		
		ArrayList<int[]> display = new ArrayList<int[]>(0);
		
		GUI gui = new GUI(cellWidth*world.length, cellWidth*(arraySize+1), cellWidth);
		
		// display automata forever
		while (true) {
			display = updateGUI(display, world, arraySize);
			gui.draw(display);
			world = updateWorld(world, rule);
			Thread.sleep(50);
		}
	}

}
