fileName = "input.txt"
lines = [list(line.strip()) for line in open(fileName, 'r').readlines()]

dp = {}

m, n = len(lines), len(lines[0])
def dfs( x, y):
    try:
        while lines[x+1][y] != '^':
            x += 1
    except IndexError:
        return 1

    left = dp.get((x+1, y-1)) or dfs(x+1, y-1)
    right = dp.get((x+1, y+1)) or dfs(x+1, y+1)
    dp[(x+1, y-1)] = left
    dp[(x+1, y+1)] = right
    
    return left + right

x, y = 0, lines[0].index('S')
print(dfs(x, y))

# Answer: check: 40, input: 231229866702355