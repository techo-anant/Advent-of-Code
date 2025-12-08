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

connect = 1000

merged = []
for i in range(connect):
    dis, i, j = heapq.heappop(heap)
    edge_set = {i, j}
    overlapping = [s for s in merged if edge_set & s]
    
    if overlapping:
        new_set = edge_set.union(*overlapping)
        merged = [s for s in merged if s not in overlapping]
        merged.append(new_set)
    else:
        merged.append(edge_set)

lenn = []
for i in merged:
    heapq.heappush(lenn, -len(i))

mult = 1
for j in range(3):
    mult *= abs(heapq.heappop(lenn))

print(mult)

#Answer:- check: 40, input: 83520