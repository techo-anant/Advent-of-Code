import java.io.*;
import java.util.*;

public class Q2_Day7 {

    // Method to find the number of unique antinode locations
    public static int findAntinodes(List<String> map) {
        // Get dimensions of the map
        int rows = map.size();
        int cols = map.get(0).length();

        // Map to store positions of antennas by frequency
        Map<Character, List<int[]>> antennas = new HashMap<>();

        // Traverse the map and collect positions of each antenna
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char ch = map.get(r).charAt(c);
                if (ch != '.') {
                    antennas.computeIfAbsent(ch, k -> new ArrayList<>()).add(new int[]{r, c});
                }
            }
        }

        // Set to store unique antinode positions
        Set<String> antinodes = new HashSet<>();

        // Function to add antinode if within bounds
        // This modifies the map directly by adding '#' to the appropriate positions
        // It also ensures that the position is stored in the antinodes set
        for (Map.Entry<Character, List<int[]>> entry : antennas.entrySet()) {
            List<int[]> positions = entry.getValue();

            // For each antenna, it itself is an antinode
            for (int[] pos : positions) {
                int r = pos[0];
                int c = pos[1];
                addAntinode(r, c, antinodes, map);
            }

            // For each pair of antennas of the same frequency
            for (int i = 0; i < positions.size(); i++) {
                for (int j = i + 1; j < positions.size(); j++) {
                    int[] pos1 = positions.get(i);
                    int[] pos2 = positions.get(j);
                    int r1 = pos1[0], c1 = pos1[1];
                    int r2 = pos2[0], c2 = pos2[1];

                    // Check horizontal alignment (same row)
                    if (Math.abs(c1 - c2) >= 1 && Math.abs(r1 - r2) == 0) {
                        // Horizontal check
                        int dist_c = (c1 - c2);
                        while (c1 < cols || c2 < cols) {
                            if (c1 < cols) {
                                addAntinode(r1, c1 + dist_c, antinodes, map);
                                c1 = c1 + dist_c;
                            }
                            if (c2 < cols) {
                                addAntinode(r2, c2 - dist_c, antinodes, map);
                                c2 = c2 - dist_c;
                            }
                        }
                    }

                    // Check vertical alignment (same column)
                    if (Math.abs(r1 - r2) >= 1 && Math.abs(c1 - c2) == 0) {
                        // Vertical check
                        int dist_r = (r1 - r2);
                        while (r1 < rows || r2 < rows) {
                            if (r1 < rows) {
                                addAntinode(r1 + dist_r, c1, antinodes, map);
                                r1 = r1 + dist_r;
                            }
                            if (r2 < rows) {
                                addAntinode(r2 - dist_r, c2, antinodes, map);
                                r2 = r2 - dist_r;
                            }
                        }
                    }

                    // Check diagonal alignment (same diagonal slope)
                    if (Math.abs(r1 - r2) >= 1 && Math.abs(c1 - c2) >= 1) {
                        int mid_r = (r1 - r2);
                        int mid_c = (c1 - c2);
                        while ((r1 < rows && c1 < cols) || (r2 < rows && c2 < cols)) {
                            if (r1 < rows && c1 < cols) {
                                addAntinode(r1 + mid_r, c1 + mid_c, antinodes, map);
                                r1 += mid_r;
                                c1 += mid_c;
                            }
                            if (r2 < rows && c2 < cols) {
                                addAntinode(r2 - mid_r, c2 - mid_c, antinodes, map);
                                r2 -= mid_r;
                                c2 -= mid_c;
                            }
                        }
                    }
                }
            }
        }

        // Return the number of unique antinodes
        return antinodes.size();
    }

    // Function to add antinode if within bounds
    private static void addAntinode(int r, int c, Set<String> antinodes, List<String> map) {
        if (0 <= r && r < map.size() && 0 <= c && c < map.get(0).length()) {
            String rowString = map.get(r);
            String newRowString = rowString.substring(0, c) + "#" + rowString.substring(c + 1);
            map.set(r, newRowString);
            antinodes.add(r + "," + c);
        }
    }

    // Function to read map from a text file
    public static List<String> readMapFromFile(String fileName) throws IOException {
        List<String> map = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            map.add(line);
        }
        reader.close();
        return map;
    }

    // Main function to run the program
    public static void main(String[] args) {
        try {
            // Read the map from the file
            List<String> map = readMapFromFile("Input.txt");

            // Get the number of unique antinode locations
            int result = findAntinodes(map);

            // Print the number of unique antinodes
            System.out.println(map);
            System.out.println("Number of unique antinodes: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
