package Day4;

import java.util.ArrayList;
import java.io.*;

public class Q1_Day4 {
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
        String word = "XMAS";
        System.out.println("Occurrences of 'XMAS': " + countWordOccurrences(grid, word));
    }

    public static int countWordOccurrences(ArrayList<String> grid, String word) {
        int rows = grid.size();
        int cols = (grid.get(0)).length();
        int wordLength = word.length();
        int count = 0;

        // Directions: right, left, down, up, diagonal down-right, diagonal down-left, diagonal up-right, diagonal up-left
        int[][] directions = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}, 
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                for (int[] dir : directions) {
                    if (isWordAt(grid, word, r, c, dir[0], dir[1], wordLength)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private static boolean isWordAt(ArrayList<String> grid, String word, int r, int c, int dr, int dc, int wordLength) {
        int rows = grid.size();
        int cols = (grid.get(0)).length();
        for (int i = 0; i < wordLength; i++) {
            int nr = r + dr * i;
            int nc = c + dc * i;
            if (nr < 0 || nr >= rows || nc < 0 || nc >= cols || grid.get(nr).charAt(nc) != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
