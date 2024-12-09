//package Day3;
//
//import java.io.*;
//import java.util.regex.*;
//import java.util.*;
//
//public class Check1 {
//    public static void main(String[] args) {
//        String doStmt = "do\\(\\)";
//        String dontStmt = "don't\\(\\)";
//        String comPattern = "mul\\(\\d{1,3},\\d{1,3}\\)|";
//
//        int sum = 0;
//        boolean enabled = true;
//
//        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/hfspl/Desktop/Advent/Day3Input"))) {
//            StringBuilder content = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                content.append(line).append(" ");
//            }
//
//            Pattern commandPattern = Pattern.compile(comPattern);
//            Matcher commandMatcher = commandPattern.matcher(content.toString());
//
//            while (commandMatcher.find()) {
//                String command = commandMatcher.group();
//
//                if (command.matches(doStmt)) {
//                    enabled = true;
//                } else if (command.matches(dontStmt)) {
//                    enabled = false;
//                } else if (enabled) {
//                    Pattern numberPat = Pattern.compile(numberPattern);
//                    Matcher numberMatcher = numberPat.matcher(command);
//
//                    List<Integer> numbers = new ArrayList<>();
//                    while (numberMatcher.find()) {
//                        numbers.add(Integer.parseInt(numberMatcher.group()));
//                    }
//
//                    if (numbers.size() == 2) {
//                        sum += numbers.get(0) * numbers.get(1);
//                    }
//                }
//            }
//
//            System.out.println("Total sum: " + sum);
//        } catch (FileNotFoundException e) {
//            System.err.println("Error: File not found - " + e.getMessage());
//        } catch (IOException e) {
//            System.err.println("Error: An I/O error occurred - " + e.getMessage());
//        } catch(Exception e) {
//        	System.err.println("Error: error occurred - " + e.getMessage());
//        }
//    }
//}