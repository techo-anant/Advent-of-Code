fileName = "input.txt"
lines = [line.strip().split(':') for line in open(fileName, 'r').readlines()]

outlets = {}
dp = {}

for line in lines:
    outlets[line[0]] = line[1].strip().split()

def dfs( dev ):
    if outlets[dev][0] == "out":
        return 1
    if dev in dp:
        return dp[dev]
    
    ways = 0
    for outputs in outlets[dev]:
        ways += dfs(outputs)
    
    dp[dev] = ways
    return ways

print(dfs("you"))