package Day2;

import java.io.*;

public class Q1_Day2 {
	public static void main(String args[]) {
		int[] levels;
		String inputFilePath = "/Users/hfspl/Desktop/Advent/Day2Input.rb";
		String records;
		String[] level;
		int counter =0;
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));){
			outerLoop:
			while((records = bufferedReader.readLine()) != null) {
				level = records.split(" ");
				levels = new int[level.length];
				
				for(int i =0; i<level.length; i++) {
					levels[i] = Integer.parseInt(level[i]);
				}
				
				if(levels[0] > levels[1]) {
					for(int j =0; j<level.length-1; j++) {
						if(levels[j]<levels[j+1] || (levels[j]-levels[j+1])>3 || (levels[j]-levels[j+1])<1) {
							continue outerLoop;
						}
					}
					counter++;
					System.out.print(counter+". ");
					for(int i =0; i<level.length; i++) {
						System.out.print(levels[i]+ ", ");
					}
					System.out.println();
				}else if(levels[0] < levels[1]) {
					for(int j = 0; j<level.length-1; j++) {
						if(levels[j]>levels[j+1] || (levels[j+1]-levels[j])>3 || (levels[j+1]-levels[j])<1) {
							continue outerLoop;
						}
					}
					counter++;
					System.out.print(counter+". ");
					for(int i =0; i<level.length; i++) {
						System.out.print(levels[i]+ ", ");
					}
					System.out.println();
				}else {
					continue outerLoop;
				}
			}
			System.out.println("Total correct records: "+ counter);
		} catch (FileNotFoundException e) {
	        System.err.println("Error: Input file not found - " + e.getMessage());
	    } catch (IOException e) {
	        System.err.println("Error: An I/O error occurred - " + e.getMessage());
	    }
		
	}
	
}
