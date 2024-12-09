package Day5;

import java.io.*;
import java.util.*;

public class Q2_Day5 {

    public static void main(String[] args) {
        List<String> rules = new ArrayList<>();
        List<List<Integer>> updates = new ArrayList<>();

        String inputFilePath1 = "/Users/hfspl/Desktop/Advent/Day5Order"; // Replace with your file paths
        String inputFilePath2 = "/Users/hfspl/Desktop/Advent/Day5Updates.xml";

        // Read rules
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath1))) {
            String line;
            while ((line = br.readLine()) != null) {
                rules.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading rules file: " + e.getMessage());
            return;
        }

        // Read updates
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

        // Process correctly ordered updates
        int correctlyOrderedSum = 0;
        List<List<Integer>> incorrectlyOrderedUpdates = new ArrayList<>();
        for (List<Integer> update : updates) {
            if (isOrdered(update, rules)) {
                correctlyOrderedSum += update.get(update.size() / 2);
            } else {
                incorrectlyOrderedUpdates.add(update);
            }
        }

        // Fix and process incorrectly ordered updates
        int incorrectlyOrderedSum = 0;
        for (List<Integer> update : incorrectlyOrderedUpdates) {
            List<Integer> fixedUpdate = reorderUpdate(update, rules);
            incorrectlyOrderedSum += fixedUpdate.get(fixedUpdate.size() / 2);
        }

        System.out.println("Sum of middle pages in correctly ordered updates: " + correctlyOrderedSum);
        System.out.println("Sum of middle pages in incorrectly ordered (fixed) updates: " + incorrectlyOrderedSum);
    }

    // Check if the update is ordered correctly
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

    // Reorder an update using topological sort
    public static List<Integer> reorderUpdate(List<Integer> update, List<String> rules) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();

        // Build graph and in-degree map
        for (int page : update) {
            graph.put(page, new ArrayList<>());
            inDegree.put(page, 0);
        }

        for (String rule : rules) {
            String[] pages = rule.split("\\|");
            int before = Integer.parseInt(pages[0]);
            int after = Integer.parseInt(pages[1]);
            if (graph.containsKey(before) && graph.containsKey(after)) {
                graph.get(before).add(after);
                inDegree.put(after, inDegree.get(after) + 1);
            }
        }

        // Topological sort
        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        List<Integer> sorted = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            sorted.add(current);

            for (int neighbour : graph.get(current)) {
                inDegree.put(neighbour, inDegree.get(neighbour) - 1);
                if (inDegree.get(neighbour) == 0) {
                    queue.offer(neighbour);
                }
            }
        }

        return sorted;
    }
}