# takes abiout 10 minutes to run

import heapq
from functools import cache

fileName = "input.txt"
liness = [list(map(int ,line.strip().split(','))) for line in open(fileName, 'r').readlines()]
lines = [[y, x] for x, y in liness]

boundry = set()

for i in range(0, len(lines)):
    if lines[i%len(lines)][0] == lines[(i+1)%len(lines)][0]:
        if lines[i%len(lines)][1] < lines[(i+1)%len(lines)][1]:
            boundry.update((lines[i%len(lines)][0], j) for j in range(lines[i%len(lines)][1], lines[(i+1)%len(lines)][1]+1))
        else:
            boundry.update((lines[i%len(lines)][0], j) for j in range(lines[i%len(lines)][1], lines[(i+1)%len(lines)][1]-1, -1))
    else:
        if lines[i%len(lines)][0] < lines[(i+1)%len(lines)][0]:
            boundry.update((j, lines[i%len(lines)][1]) for j in range(lines[i%len(lines)][0], lines[(i+1)%len(lines)][0]+1))
        else:
            boundry.update((j, lines[i%len(lines)][1]) for j in range(lines[i%len(lines)][0], lines[(i+1)%len(lines)][0]-1, -1))

max_x, max_y = max(x for x, _ in lines), max(y for _, y in lines)
min_x, min_y = min(x for x, _ in lines), min(y for _, y in lines)

@cache
def is_good(t1):
    if t1 in lines or t1 in boundry:
        return True
    
    # Choose the closest edge to minimize work
    dist_left = t1[1] - min_y
    dist_right = max_y - t1[1]
    dist_up = t1[0] - min_x
    dist_down = max_x - t1[0]
    
    min_dist = min(dist_left, dist_right, dist_up, dist_down)
    
    num_crossed = 0
    
    if min_dist == dist_left:  
        for col in range(t1[1] - 1, min_y - 1, -1):
            if ((t1[0], col) in boundry and (t1[0], col-1) not in boundry) or (t1[0], col) in lines:
                if (t1[0] == min_x or t1[0] == max_x) and (t1[0], col) in lines:
                    num_crossed += 1
                    continue
                elif (t1[0], col) in lines:
                    continue
                num_crossed += 1
    elif min_dist == dist_right: 
        for col in range(t1[1] + 1, max_y + 1):
            if ((t1[0], col) in boundry and (t1[0], col-1) not in boundry) or (t1[0], col) in lines:
                if (t1[0] == min_x or t1[0] == max_x) and (t1[0], col) in lines:
                    num_crossed += 1
                    continue
                elif (t1[0], col) in lines:
                    continue
                num_crossed += 1
    elif min_dist == dist_up:  
        for row in range(t1[0] - 1, min_x - 1, -1):
            if ((row, t1[1]) in boundry and (row-1, t1[1]) not in boundry) or (row, t1[1]) in lines:
                if (t1[1] == min_y or t1[1] == max_y) and (row, t1[1]) in lines:
                    num_crossed += 1
                    continue
                elif (row, t1[1]) in lines:
                    continue
                num_crossed += 1
    else:  
        for row in range(t1[0] + 1, max_x + 1):
            if ((row, t1[1]) in boundry and (row-1, t1[1]) not in boundry) or (row, t1[1]) in lines:
                if (t1[1] == min_y or t1[1] == max_y) and (row, t1[1]) in lines:
                    num_crossed += 1
                    continue
                elif (row, t1[1]) in lines:
                    continue
                num_crossed += 1

    return num_crossed % 2 == 1

@cache
def inside_boundry(t1, t2):
    min_row, max_row = sorted([t1[0], t2[0]])
    min_col, max_col = sorted([t1[1], t2[1]])
    
    # Check all 4 corners first
    corners = [
        (min_row, min_col),
        (min_row, max_col),
        (max_row, min_col),
        (max_row, max_col)
    ]

    for corner in corners:
        if not (corner in boundry or is_good(corner)):
            return False
    
    # Top edge (left to right) and bottom edge (left to right)
    prev_on_boundry_top = True
    prev_on_boundry_btm = True
    for col in range(min_col+1, max_col):
        if prev_on_boundry_top and (min_row, col) not in boundry:
            prev_on_boundry_top = False
        elif not prev_on_boundry_top and (min_row, col) in boundry and (min_row, col) not in lines:
            return False
        elif (min_row, col) not in lines:
            prev_on_boundry_top = True
        
        if prev_on_boundry_btm and (max_row, col) not in boundry:
            prev_on_boundry_btm = False
        elif not prev_on_boundry_btm and (max_row, col) in boundry and (max_row, col) not in lines:
            return False
        elif (min_row, col) not in lines:
            prev_on_boundry_btm = True

    prev_on_boundry_left = True
    prev_on_boundry_right = True
    for row in range(min_row+1, max_row):
        if prev_on_boundry_left and (row, min_col) not in boundry:
            prev_on_boundry_left = False
        elif not prev_on_boundry_left and (row, min_col) in boundry and (row, min_col) not in lines:
            return False
        elif (row, min_col) not in lines:
            prev_on_boundry_left = True
        
        if prev_on_boundry_right and (row, max_col) not in boundry:
            prev_on_boundry_right = False
        elif not prev_on_boundry_right and (row, max_col) in boundry and (row, max_col) not in lines:
            return False
        elif (row, min_col) not in lines:
            prev_on_boundry_right = True
    
    return True

def area( t1, t2):
    return -((abs(t1[0] - t2[0])+1) * (abs(t1[1] - t2[1])+1))

heap = []

for i in range(len(lines)):
    for j in range(i + 1 , len(lines)):
        heapq.heappush(heap, (area(lines[i], lines[j]), i , j))

ans = 0
while heap:
    ans, i , j = heapq.heappop(heap)
    ans = abs(ans)
    if inside_boundry(tuple(lines[i]) , tuple(lines[j])):
        break
    
print(ans)

