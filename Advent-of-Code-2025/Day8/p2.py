import math
import heapq

fileName = "input.txt"
lines = [list(map(int ,line.strip().split(','))) for line in open(fileName, 'r').readlines()]


def st_distance( t1, t2):
    return math.sqrt((t1[0]-t2[0])**2 + (t1[1]-t2[1])**2 + (t1[2]-t2[2])**2)

heap = []

for i in range(len(lines)):
    for j in range(i + 1 , len(lines)):
        heapq.heappush(heap, (st_distance(lines[i], lines[j]), i , j))

merged = []
prev = None

merged.append(set(heapq.heappop(heap)[1:]))

while len(merged) != 1 or all(len(s) != len(lines) for s in merged):
    dis, i, j = heapq.heappop(heap)
    edge_set = {i, j}
    prev = list(edge_set)
    overlapping = [s for s in merged if edge_set & s]
    
    if overlapping:
        new_set = edge_set.union(*overlapping)
        merged = [s for s in merged if s not in overlapping]
        merged.append(new_set)
    else:
        merged.append(edge_set)

print(lines[prev[0]][0] * lines[prev[1]][0])

#Answer:- check: 25272, input: 1131823407