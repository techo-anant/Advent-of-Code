package Day7;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q1_Day7 {
	public static void main(String[] args) {
    	ArrayList<String> grid = new ArrayList<>();
    	String path;
    	String inputFilePath = "/Users/hfspl/Desktop/Advent/Day7Input";
    	
    	try(BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));){
			while((path = bufferedReader.readLine()) != null) {
				grid.add(path);
			}
    	}catch (FileNotFoundException e) {
	        System.err.println("Error: Input file not found - " + e.getMessage());
	    } catch (IOException e) {
	        System.err.println("Error: An I/O error occurred - " + e.getMessage());
	    }
    	
    	long[] total = new long[grid.size()];
    	List<List<String>> oprands = new ArrayList<>();
    	
    	for(int i =0; i<grid.size(); i++) {
    		String[] str = grid.get(i).split(": ");
    		total[i] = Long.parseLong(str[0]);
    		String[] oprand = str[1].split(" ");
    		oprands.add(new ArrayList<>(Arrays.asList(oprand)));
    	}
    	
    	long sum = 0;
    	
    	for(int i =0; i<oprands.size(); i++) {
    		if(isBigger(oprands.get(i), total[i]) || isLower(oprands.get(i), total[i])) {
    			String op1 = "+";
    	    	String op2 = "*";
    	    	List<String> permutations = generatePermutations(op1, op2, (oprands.get(i).size()-1));
    	    	if(isEval(oprands.get(i), total[i], permutations)) {
    	    		sum += total[i];
    	    	}
    		}
    	}
    	
    	System.out.println("Total sum is: "+sum);
    	
	}
	
	public static List<String> generatePermutations(String op1, String op2, int n) {
        List<String> permutations = new ArrayList<>();
        int totalCombinations = (int) Math.pow(2, n); // 2^n combinations for binary choices

        for (int i = 0; i < totalCombinations; i++) {
            StringBuilder sb = new StringBuilder();

            // Create the binary string representing the permutation
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) == 0) {
                    sb.append(op1);
                } else {
                    sb.append(op2);
                }
            }

            permutations.add(sb.toString());
        }

        return permutations;
    }
	
	public static boolean isBigger(List<String> operands, long num) {
		long mul = 1;
		for(String op : operands) {
			mul *= Integer.parseInt(op);
		}
		return num<mul;
	}
	
	public static boolean isLower(List<String> operands, long num) {
		long add = 1;
		for(String op : operands) {
			add += Integer.parseInt(op);
		}
		return num>add;
	}
	
	public static boolean isEval(List<String> oprands, long num, List<String> oprators) {
		long eval;
		for(int i =0; i<oprators.size(); i++) {
			eval = Integer.parseInt(oprands.get(0));
			String op = oprators.get(i);
			for(int j =0; j<op.length(); j++) {
				if(op.charAt(j) == '+') {
					eval += Integer.parseInt(oprands.get(j+1));
				}else {
					eval *= Integer.parseInt(oprands.get(j+1));
				}
			}
			if(eval == num) return true;
		}
		return false;
	}
	
}
