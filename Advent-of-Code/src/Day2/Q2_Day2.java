package Day2;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Q2_Day2 {
    
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

    // Method to generate all sublists by removing one element at a time
    public static List<List<Integer>> generateSublists(List<Integer> nums) {
        List<List<Integer>> sublists = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> sublist = new ArrayList<>(nums);
            sublist.remove(i);
            sublists.add(sublist);
        }
        return sublists;
    }

    public static void main(String[] args) {
		String inputFilePath = "/Users/hfspl/Desktop/Advent/Day2Input.rb";
        int safe = 0;

        String records;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));){
			while((records = bufferedReader.readLine()) != null) {
	            String[] parts = records.split(" ");
	            List<Integer> nums = new ArrayList<>();
	
	            for (String part : parts) {
	                nums.add(Integer.parseInt(part));
	            }
	
	            boolean good = false;
	
	            if (isMonotonic(nums) && countUnsafeDifferences(nums) == 0) {
	                safe++;
	            } else {
	                List<List<Integer>> sublists = generateSublists(nums);
	                for (List<Integer> sublist : sublists) {
	                    if (isMonotonic(sublist) && countUnsafeDifferences(sublist) == 0) {
	                        good = true;
	                        break;
	                    }
	                }
	                if (good) {
	                    safe++;
	                }
	            }
	        }
        }catch (FileNotFoundException e) {
	        System.err.println("Error: Input file not found - " + e.getMessage());
	    } catch (IOException e) {
	        System.err.println("Error: An I/O error occurred - " + e.getMessage());
	    }
        System.out.println(safe);
    }
}