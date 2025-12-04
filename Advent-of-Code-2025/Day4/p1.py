
fileName = 'check.txt'

g = [list(line.strip()) for line in open(fileName, 'r').readlines()]

directions = [
    [0,1], [1,0], [-1, 0], [0, -1], [1, 1], [-1, -1], [-1, 1], [1, -1]
]

def isWorthy( x, y ):
    fewer = 0
    for d in directions:
        try:
            new_i = i-d[0]
            new_j = j-d[1]
            if new_i < 0 or new_j < 0:
                continue
            if g[new_i][new_j] == '@':
                fewer += 1
        except IndexError:
            continue
    
    return fewer < 4


ans = 0
for i in range( len(g) ):
    for j in range( len(g[0]) ):
        if g[i][j] == '@':
            if isWorthy(i, j):
                ans += 1

print(ans)

# Answer:- check: 13, input: 1604