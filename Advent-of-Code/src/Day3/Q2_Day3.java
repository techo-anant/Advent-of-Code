package Day3;

import java.io.*;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

public class Q2_Day3 {

	public static void main(String[] args) {
		int sum = 0;
    	String[] tokens;
    	String inputFilePath = "/Users/hfspl/Desktop/Advent/Day3Input";
    	String corrupted;
    	String[] divide;
    	String[] toDo;
    	
    	try(BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));){
			while((corrupted = bufferedReader.readLine()) != null) {
		        String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";
		
		        divide = corrupted.split("do\\(\\)");
		        
		        for(String token : divide) {
		        	toDo = token.split("don\'t\\(\\)");
		        	Pattern pattern = Pattern.compile(regex);
		            Matcher matcher = pattern.matcher(toDo[0]);

		            // List to store all matches
		            List<String> matches = new ArrayList<>();

		            // Find all matches
		            while (matcher.find()) {
		                matches.add(matcher.group());
		            }
		            
		            String[] matchesArray = matches.toArray(new String[0]);
		            for(String match : matchesArray) {
		            	tokens = match.split("mul|\\(|\\,|\\)");
		            	for(int i= 0; i<tokens.length-1 ; i++) {
		                	if(isNum(tokens[i]) && isNum(tokens[i+1])) {
		                		sum += mull(passMull(tokens[i]), passMull(tokens[i+1]));
		                	}else {
		                		continue;
		                	}
		                }
		            }
		        }
			}
			System.out.println("Sum: "+sum);
    	}catch (FileNotFoundException e) {
	        System.err.println("Error: Input file not found - " + e.getMessage());
	    } catch (IOException e) {
	        System.err.println("Error: An I/O error occurred - " + e.getMessage());
	    }
	}
	public static int mull(int a, int b) {
		return (a*b);
    }
    public static int passMull(String a) {
		return Integer.parseInt(a);
	}
	
	public static boolean isNum(String str) {
		try {
			Integer.parseInt(str);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

}
