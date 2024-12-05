package Day4;

import java.util.ArrayList;
import java.io.*;

public class Q2_Day4 {
    public static void main(String[] args) {
    	ArrayList<String> grid = new ArrayList<>();
    	String row;
    	String inputFilePath = "/Users/hfspl/Desktop/Advent/Day4Input";
    	
    	try(BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));){
			while((row = bufferedReader.readLine()) != null) {
				grid.add(row);
			}
    	}catch (FileNotFoundException e) {
	        System.err.println("Error: Input file not found - " + e.getMessage());
	    } catch (IOException e) {
	        System.err.println("Error: An I/O error occurred - " + e.getMessage());
	    }
        String word = "MAS";
        System.out.println("Occurrences of 'MAS': " + countWordOccurrences(grid, word));
    }

    public static int countWordOccurrences(ArrayList<String> grid, String word) {
        int rows = grid.size();
        int cols = (grid.get(0)).length();
        int count = 0;

        
        

        for (int r = 1; r < rows - 1; r++) {
            for (int c = 1; c < cols - 1; c++) {
            	if (grid.get(r).charAt(c) == 'A') {
                    if(isXMAS(grid, r, c)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private static boolean isXMAS(ArrayList<String> grid, int r, int c) {
        return (grid.get(r - 1).charAt(c - 1) == 'M' && grid.get(r + 1).charAt(c + 1) == 'S' &&
               grid.get(r - 1).charAt(c + 1) == 'M' && grid.get(r + 1).charAt(c - 1) == 'S') || 
        		(grid.get(r + 1).charAt(c - 1) == 'M' && grid.get(r - 1).charAt(c + 1) == 'S' &&
               grid.get(r + 1).charAt(c + 1) == 'M' && grid.get(r - 1).charAt(c - 1) == 'S') || 
        		(grid.get(r - 1).charAt(c + 1) == 'M' && grid.get(r + 1).charAt(c - 1) == 'S' &&
                grid.get(r + 1).charAt(c + 1) == 'M' && grid.get(r - 1).charAt(c - 1) == 'S') ||
        		(grid.get(r - 1).charAt(c - 1) == 'M' && grid.get(r + 1).charAt(c + 1) == 'S' &&
                grid.get(r + 1).charAt(c - 1) == 'M' && grid.get(r - 1).charAt(c + 1) == 'S');
    }
}
