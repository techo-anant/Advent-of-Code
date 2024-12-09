package Day2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Check {
	
	// Method to check if a list is monotonic
    public static boolean isMonotonic(List<Integer> nums) {
        boolean decreasing = false;
        boolean increasing = false;

        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) > nums.get(i + 1)) {
                decreasing = true;
            } else if (nums.get(i) < nums.get(i + 1)) {
                increasing = true;
            } else {
                return false;
            }
        }
        return increasing != decreasing;
    }

    // Method to check if any difference between consecutive elements is greater than 3
    public static int countUnsafeDifferences(List<Integer> nums) {
        int unsafe = 0;
        for (int i = 0; i < nums.size() - 1; i++) {
            if (Math.abs(nums.get(i) - nums.get(i + 1)) > 3) {
                unsafe++;
            }
        }
        return unsafe;
    }

	
	public static void main(String args[]) {
		String inputFilePath = "/Users/hfspl/Desktop/Advent/Day2Input.rb";
		String records;
		ArrayList<Integer> level;
		boolean isRecord;
		String[] str;
		ArrayList<Integer> levels;
		int counter =0;
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));){
			while((records = bufferedReader.readLine()) != null) {
				str = records.split(" ");
				levels = new ArrayList<>();
				for(int i =0; i<str.length; i++) {
					levels.add(Integer.parseInt(str[i]));
				}
				level = new ArrayList<>(levels);
				
				isRecord = isMonotonic(levels) && countUnsafeDifferences(levels)==0;
				if(!isRecord) {
					for(int k=0; k<levels.size(); k++) {
						level = new ArrayList<>(levels);
						level.remove(k);
						isRecord = isMonotonic(level) && countUnsafeDifferences(level)==0;
						if(isRecord) break;
					}
				}
				counter = (isRecord) ? counter+1 : counter; 
				
				System.out.print(counter+". ");
				for(int i =0; i<levels.size(); i++) {
					System.out.print(levels.get(i)+ ", ");
				}
				System.out.println();
			}
			System.out.println("Total correct records: "+ counter);
		} catch (FileNotFoundException e) {
	        System.err.println("Error: Input file not found - " + e.getMessage());
	    } catch (IOException e) {
	        System.err.println("Error: An I/O error occurred - " + e.getMessage());
	    }
		
	}
	
}
