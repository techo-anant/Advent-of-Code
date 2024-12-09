package Day6;

import java.io.*;
import java.util.ArrayList;

public class Q1_Day6 {
	public static void main(String[] args) {
    	ArrayList<String> grid = new ArrayList<>();
    	String path;
    	String inputFilePath = "/Users/hfspl/Desktop/Advent/Day6Input";
    	
    	try(BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));){
			while((path = bufferedReader.readLine()) != null) {
				grid.add(path);
			}
    	}catch (FileNotFoundException e) {
	        System.err.println("Error: Input file not found - " + e.getMessage());
	    } catch (IOException e) {
	        System.err.println("Error: An I/O error occurred - " + e.getMessage());
	    }
    	
    	String[][] paths = new String[grid.size()][];
    	
    	for(int i = 0; i< grid.size(); i++) {
    		paths[i] = (grid.get(i)).split("");
    	}
    	
    	int steps = 1;
    	int [] guardPos = findGuard(grid);
    	int row = guardPos[0];
    	int col = guardPos[1];
    	char guard = '^';
    	int[] dim = new int[2];
    	dim[0] = -1;
    	dim[1] = 0;
    	
    	while(row > 0 && row < paths.length-1 && col > 0 && col < paths[0].length-1) {
    		if(paths[row+dim[0]][col+dim[1]].equals("#")) {
    			if(paths[row][col].equals(".")) steps +=1;
    			guard = changeDir(guard);
    			dim = changeDim(guard, dim);
    			paths[row][col] = "X";
    			row += dim[0];
    			col += dim[1];
    		}else if(paths[row+dim[0]][col+dim[1]].equals(".")) {
    			if(paths[row][col].equals(".")) steps += 1;
    			paths[row][col] = "X";
    			row += dim[0];
    			col += dim[1];
    		}else {
    			if(paths[row][col].equals(".")) steps += 1;
    			paths[row][col] = "X";
    			row += dim[0];
    			col += dim[1];
    		}
    	}
    	
    	System.out.println("The total steps is : "+ ++steps);
    	
    	for(String[] p : paths) {
    		for(String k : p) {
    			System.out.print(k);
    		}
    		System.out.println();
    	}
    	
    }
	
	public static int[] findGuard(ArrayList<String> grid) {
		int[] index = new int[2];
		String path;
		for(int j =0; j<grid.size(); j++) {
			path = grid.get(j);
			for(int i =0; i<path.length(); i++) {
				if(path.charAt(i) == '^') {
					index[0] = j;
					index[1] = i;
				}
			}
		}
		return index;
	}
	
	public static char changeDir(char guard) {
		if(guard == '^') {
			return '>';
		}else if(guard == '>') {
			return 'v';
		}else if(guard == 'v') {
			return '<';
		}else {
			return '^';
		}
	}
	
	public static int[] changeDim(char guard, int[] dim) {
		if(guard == '^') {
			dim[0] = -1;
			dim[1] = 0;
		}else if(guard == '>') {
			dim[0] = 0;
			dim[1] = 1;
		}else if(guard == 'v') {
			dim[0] = 1;
			dim[1] = 0;
		}else {
			dim[0] = 0;
			dim[1] = -1;
		}
		return dim;
	}
	
}
