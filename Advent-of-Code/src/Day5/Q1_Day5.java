package Day5;

import java.io.*;
import java.util.*;

public class Q1_Day5 {
    public static void main(String[] args) {
        List<String> rules = new ArrayList<>();
        List<List<Integer>> updates = new ArrayList<>();
        
        String inputFilePath1 = "/Users/hfspl/Desktop/Advent/Day5Order";
        String inputFilePath2 = "/Users/hfspl/Desktop/Advent/Day5Updates.xml";
        
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath1))) {
            String line;
            while ((line = br.readLine()) != null) {
                rules.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading rules file: " + e.getMessage());
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath2))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Integer> update = new ArrayList<>();
                for (String s : line.split(",")) {
                    update.add(Integer.parseInt(s.trim()));
                }
                updates.add(update);
            }
        } catch (IOException e) {
            System.err.println("Error reading updates file: " + e.getMessage());
            return;
        }

        int sum = 0;
        for (List<Integer> update : updates) {
            if (isOrdered(update, rules)) {
                sum += update.get(update.size() / 2);
            }
        }

        System.out.println("The sum of middle pages in correctly ordered updates: " + sum);
    }

    public static boolean isOrdered(List<Integer> update, List<String> rules) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < update.size(); i++) {
            indexMap.put(update.get(i), i);
        }

        for (String rule : rules) {
            String[] pages = rule.split("\\|");
            int before = Integer.parseInt(pages[0]);
            int after = Integer.parseInt(pages[1]);
            if (indexMap.containsKey(before) && indexMap.containsKey(after)) {
                if (indexMap.get(before) > indexMap.get(after)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    
}