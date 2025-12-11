fileName = "input.txt"
lines = [list(line.strip()) for line in open(fileName, 'r').readlines()]

m, n = len(lines), len(lines[0])
def dfs( x, y):
    if lines[x][y] == '|':
        return 0

    try:
        while lines[x+1][y] != '^':
            if lines[x+1][y] == '|':
                return 0
            lines[x][y] = '|'
            x += 1
    except IndexError:
        return 0

    left = dfs(x+1, y-1)
    right = dfs(x+1, y+1)
    
    return left + right + 1

x, y = 0, lines[0].index('S')
print(dfs(x, y))

# Answer:- check: 21, input: 1672