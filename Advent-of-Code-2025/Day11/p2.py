import functools
fileName = "input.txt"
lines = [line.strip().split(':') for line in open(fileName, 'r').readlines()]

outlets = {}

for line in lines:
    outlets[line[0]] = line[1].strip().split()

@functools.cache
def dfs( dev, fft, dac ):
    if outlets[dev][0] == "out":
        return 1 if fft and dac else 0
    
    if dev == "fft":
        fft = True
    if dev == "dac":
        dac = True
    
    ways = 0
    for outputs in outlets[dev]:
        ways += dfs(outputs, fft, dac)

    return ways

print(dfs("svr", False, False))