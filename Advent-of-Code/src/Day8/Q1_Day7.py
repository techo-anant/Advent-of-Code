def find_antinodes(map):
    # Get dimensions of the map
    rows = len(map)
    cols = len(map[0])

    # Dictionary to store positions of antennas by frequency
    antennas = {}

    # Traverse the map and collect positions of each antenna
    for r in range(rows):
        for c in range(cols):
            char = map[r][c]
            if char != '.':
                if char not in antennas:
                    antennas[char] = []
                antennas[char].append((r, c))

    # Set to store unique antinode positions
    antinodes = set()

    # Function to add antinode if within bounds
    def add_antinode(r, c):
        if 0 <= r < rows and 0 <= c < cols:
            row_string = map_example[r]
            new_row_string = row_string[:c] + '#' + row_string[c+1:]
            map_example[r] = new_row_string
            antinodes.add((r, c))

    # For each antenna frequency group
    for freq, positions in antennas.items():
        # For each pair of antennas of the same frequency
        for i in range(len(positions)):
            for j in range(i + 1, len(positions)):
                r1, c1 = positions[i]
                r2, c2 = positions[j]

                # Check if one antenna is exactly twice as far as the other in a straight line
                # We check 4 possible directions (horizontal, vertical, and two diagonals)
                if r1 == r2:  # Same row
                    # Horizontal check
                    if abs(c1 - c2) >= 1 and abs(r1 - r2) == 0:
                        dist_c = (c1 - c2)
                        add_antinode(r1, c1+dist_c)
                        add_antinode(r2, c2-dist_c)
                elif c1 == c2:  # Same column
                    # Vertical check
                    if abs(r1 - r2) >= 1 and abs(c1 - c2) == 0:
                        dist_r = (r1 - r2)
                        add_antinode(r1 + dist_r, c1)
                        add_antinode(r2 - dist_r, c2)
                elif abs(r1 - r2) != 0 and abs(c1 - c2) != 0:  # Diagonal
                    # Diagonal check
                    if abs(r1 - r2) >= 1 and abs(c1 - c2) >= 1:
                        mid_r = (r1 - r2)
                        mid_c = (c1 - c2)
                        add_antinode(r1 + mid_r, c1 + mid_c)
                        add_antinode(r2 - mid_r, c2 - mid_c)

    # Return the number of unique antinodes
    return len(antinodes)

# Function to read map from a text file
def read_map_from_file(file_name):
    with open(file_name, 'r') as f:
        # Read the map from the file and strip the newline characters
        map_lines = f.read().strip().splitlines()
    return map_lines


# Sample input map
map_example = read_map_from_file("Input.txt")

# Get the number of unique antinode locations
result = find_antinodes(map_example)
print(map_example)
print(result)  # Expected output: 14

