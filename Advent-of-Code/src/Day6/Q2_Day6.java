package Day6;

import java.io.*;
import java.util.*;

public class Q2_Day6 {
    public static void main(String[] args) {
        ArrayList<String> grid = new ArrayList<>();
        String path;
        String inputFilePath = "/Users/hfspl/Desktop/Advent/Day6Input"; // Adjust path as needed

        // Read the grid from the input file
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath))) {
            while ((path = bufferedReader.readLine()) != null) {
                grid.add(path);
            }
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
            return;
        }

        char[][] paths = new char[grid.size()][];
        for (int i = 0; i < grid.size(); i++) {
            paths[i] = grid.get(i).toCharArray();
        }

        // Find the guard's initial position
        int[] guardPos = findGuard(paths);
        int row = guardPos[0];
        int col = guardPos[1];
        char guard = '^';  // Guard starts facing up
        int[] dim = changeDim(guard);
        Set<State> visited = new HashSet<>();
        List<State> pathHistory = new ArrayList<>();

        // Simulate the guard's movement
        while (!visited.contains(new State(row, col, guard))) {
            visited.add(new State(row, col, guard));
            pathHistory.add(new State(row, col, guard));

            int nextRow = row + dim[0];
            int nextCol = col + dim[1];

            // Ensure the next position is within bounds
            if (nextRow >= 0 && nextRow < paths.length && nextCol >= 0 && nextCol < paths[nextRow].length) {
                if (paths[nextRow][nextCol] == '#') {
                    guard = changeDir(guard);
                    dim = changeDim(guard);
                } else {
                    row = nextRow;
                    col = nextCol;
                }
            } else {
                // Guard exits the grid
                break;
            }
        }

        // Now that we have the guard's path, we will try to find the potential obstruction positions
        Set<String> obstructionPositions = new HashSet<>();
        Set<State> visitedPositions = new HashSet<>(visited);

        // Search surrounding positions of visited positions
        for (State state : visitedPositions) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int newRow = state.row + i;
                    int newCol = state.col + j;

                    // Skip the guard's initial position and out-of-bounds positions
                    if ((newRow == guardPos[0] && newCol == guardPos[1]) || newRow < 0 || newRow >= paths.length || newCol < 0 || newCol >= paths[newRow].length) {
                        continue;
                    }

                    // Check if the position is empty ('.') and add it to obstruction positions
                    if (paths[newRow][newCol] == '.') {
                        obstructionPositions.add(newRow + "," + newCol);
                    }
                }
            }
        }

        // Now let's check each potential obstruction position and see if placing it causes a loop
        int validObstructionCount = 0;
        for (String pos : obstructionPositions) {
            String[] parts = pos.split(",");
            int newRow = Integer.parseInt(parts[0]);
            int newCol = Integer.parseInt(parts[1]);

            // Place the obstruction temporarily
            paths[newRow][newCol] = '#';

            // Simulate the guard's movement again to check if it gets stuck in a loop
            Set<State> tempVisited = new HashSet<>();
            int tempRow = guardPos[0];
            int tempCol = guardPos[1];
            char tempGuard = '^';
            int[] tempDim = changeDim(tempGuard);
            boolean loopDetected = false;

            // Simulate the guard's movement with the new obstruction
            while (!tempVisited.contains(new State(tempRow, tempCol, tempGuard))) {
                tempVisited.add(new State(tempRow, tempCol, tempGuard));

                int nextRow = tempRow + tempDim[0];
                int nextCol = tempCol + tempDim[1];

                // Ensure the next position is within bounds
                if (nextRow >= 0 && nextRow < paths.length && nextCol >= 0 && nextCol < paths[nextRow].length) {
                    if (paths[nextRow][nextCol] == '#') {
                        tempGuard = changeDir(tempGuard);
                        tempDim = changeDim(tempGuard);
                    } else {
                        tempRow = nextRow;
                        tempCol = nextCol;
                    }
                } else {
                    // Guard exits the grid
                    break;
                }

                // Check if the guard revisits a position
                if (tempVisited.contains(new State(tempRow, tempCol, tempGuard))) {
                    loopDetected = true;
                    break;
                }
            }

            if (loopDetected) {
                validObstructionCount++;
            }

            // Reset the position to empty ('.')
            paths[newRow][newCol] = '.';
        }

        // Output the number of valid obstruction positions
        System.out.println("Possible obstruction positions to cause a loop: " + validObstructionCount);
    }

    public static int[] findGuard(char[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == '^') {
                    return new int[]{row, col};
                }
            }
        }
        throw new IllegalStateException("Guard not found on the grid.");
    }

    public static char changeDir(char guard) {
        switch (guard) {
            case '^': return '>';
            case '>': return 'v';
            case 'v': return '<';
            case '<': return '^';
            default: throw new IllegalArgumentException("Invalid guard direction: " + guard);
        }
    }

    public static int[] changeDim(char guard) {
        switch (guard) {
            case '^': return new int[]{-1, 0};
            case '>': return new int[]{0, 1};
            case 'v': return new int[]{1, 0};
            case '<': return new int[]{0, -1};
            default: throw new IllegalArgumentException("Invalid guard direction: " + guard);
        }
    }

    static class State {
        int row, col;
        char dir;

        State(int row, int col, char dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return row == state.row && col == state.col && dir == state.dir;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col, dir);
        }
    }
}
