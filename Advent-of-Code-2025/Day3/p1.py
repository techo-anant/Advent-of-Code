
fileName = 'input.txt'

lines = [line.strip() for line in open(fileName, 'r').readlines()]

summ = 0

for line in lines:

    maxx , idx = -float('Inf'), 0
    for i in range(len(line)-1):
        if int(line[i]) > maxx:
            maxx = int(line[i])
            idx = i
        
    maxx = str(maxx)

    max2 = 0

    for i in range(idx+1, len(line), 1):
        if int(line[i]) > max2:
            max2 = int(line[i])
    numm =  maxx + str(max2)
        
    summ += int(numm)

print(summ)

# Answer: check -> 357, input -> 17427