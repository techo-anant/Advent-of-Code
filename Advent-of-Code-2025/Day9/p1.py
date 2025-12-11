import heapq

fileName = "input.txt"
lines = [list(map(int ,line.strip().split(','))) for line in open(fileName, 'r').readlines()]

x, y = max(x for x, _ in lines), max(y for _, y in lines)

def area( t1, t2):
    return -abs(((t1[0] - t2[0])+1) * ((t1[1] - t2[1])+1))

heap = []

for i in range(len(lines)):
    for j in range(i + 1 , len(lines)):
        heapq.heappush(heap, (area(lines[i], lines[j]), i , j))
    
# print(heap)
print(abs(heapq.heappop(heap)[0]))