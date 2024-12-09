package Day7;

import java.io.*;
import java.util.*;

public class Q2_Day7 {
    public static void main(String[] args) {
        String inputFilePath = "/Users/hfspl/Desktop/Advent/Day7Input";
        List<String> grid = readInput(inputFilePath);

        long[] totals = new long[grid.size()];
        List<List<String>> operands = new ArrayList<>();

        for (int i = 0; i < grid.size(); i++) {
            String[] parts = grid.get(i).split(": ");
            totals[i] = Long.parseLong(parts[0]);  // Test value before colon
            operands.add(Arrays.asList(parts[1].split(" ")));  // Numbers after colon
        }

        long sum = 0;

        // Loop through each operand list and try to match permutations
        for (int i = 0; i < operands.size(); i++) {
            List<String> operandList = operands.get(i);
            long target = totals[i];

            // Generate all permutations of operators for the n-1 operator slots
            List<String> permutations = generatePermutations(operandList.size() - 1);

            // Check if any of these permutations evaluate to the target value
            if (isValid(operandList, target, permutations)) {
                sum += target;
            }
        }

        System.out.println("Total sum is : " + sum);
    }

    // Helper method to read input file
    private static List<String> readInput(String filePath) {
        List<String> grid = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                grid.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return grid;
    }

    // Generate all permutations of operators for n-1 operator slots
    private static List<String> generatePermutations(int operatorSlots) {
        List<String> permutations = new ArrayList<>();
        String[] operators = {"+", "*", "||"};
        int totalCombinations = (int) Math.pow(operators.length, operatorSlots);

        // Generate all combinations of operators for the operatorSlots
        for (int i = 0; i < totalCombinations; i++) {
            StringBuilder sb = new StringBuilder();
            int current = i;
            for (int j = 0; j < operatorSlots; j++) {
                sb.append(operators[current % operators.length]);
                if(j<operatorSlots-1) {
                	sb.append(",");
                }
                current /= operators.length;
            }
            permutations.add(sb.toString());
        }
        return permutations;
    }

    // Check if any of the permutations of operators evaluate to the target value
    private static boolean isValid(List<String> operands, long target, List<String> permutations) {
        for (String permutation : permutations) {
        	String[] permut = permutation.split(",");
            if (permut.length != operands.size() - 1) {
                continue;  // Skip permutations that don't match the number of operators needed
            }

            long result = evaluate(operands, permut);
            if (result == target) {
                return true;  // Return true if any permutation matches the target value
            }
        }
        return false;  // No valid permutation found
    }

    // Evaluate the expression with left-to-right evaluation
    private static long evaluate(List<String> operands, String[] operators) {
        long result = Long.parseLong(operands.get(0));  // Start with the first operand

        // Loop through operators and apply them to operands
        for (int i = 0; i < operators.length; i++) {
            String operator = String.valueOf(operators[i]);  // Current operator
            long nextOperand = Long.parseLong(operands.get(i + 1));  // Next operand

            // Apply operator between operands
            switch (operator) {
                case "+":
                    result += nextOperand;
                    break;
                case "*":
                    result *= nextOperand;
                    break;
                case "||":
                    // Concatenate result and next operand (as strings) and convert back to long
                    result = Long.parseLong(String.valueOf(result) + nextOperand);
                    break;
            }
        }

        return result;
    }
}