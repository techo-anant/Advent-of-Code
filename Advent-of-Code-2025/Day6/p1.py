fileName = "input.txt"

lines = [line.strip() for line in open(fileName, 'r').readlines()]

question = [[] for _ in range(len(lines[0].split()))]

for line in lines:
    l = line.split(' ')
    i = 0
    while i < len(question):
        if l[i] != '':
            question[i].append(l[i])
            i += 1
        else:
            l.pop(i)

summ = 0
opr = len(question[0])

for q in question:
    op = q[opr - 1]
    if op == '+':
        curr = 0
        for i in range(opr - 1):
            curr += int(q[i])
    else:
        curr = 1
        for i in range(opr - 1):
            curr *= int(q[i])
    
    summ += curr

print(summ)

# Answer:- check: 4277556, input: 4449991244405
