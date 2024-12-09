package Day3;

import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

public class Check2 {
    public static void main(String[] args) {
    	int sum = 0;
    	String[] tokens;
    	String[] toDo;
        String input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
        String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";
        
        tokens = input.split("do\\(\\)");
        
        for(String token : tokens) {
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

            // Print the array of matches
            System.out.println("Matches: " + String.join(", ", matchesArray));
        }
        System.out.println("Sum: "+sum);

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
