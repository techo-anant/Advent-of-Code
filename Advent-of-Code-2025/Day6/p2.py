fileName = "input.txt"

lines = [line for line in open(fileName, 'r').readlines()]
question = [[] for _ in range(len(lines[3].split()))]

lenn = []

curr = 0
for i in range(len(lines[-1])):
    if lines[-1][i] == '+' or lines[-1][i] == '*':
        curr += 1
    else:
        try:
            if lines[-1][i] == ' ' and lines[-1][i+1] == ' ':
                curr += 1
            else:
                lenn.append(curr)
                curr = 0
        except IndexError:
            lenn.append(curr+1)
            break

for grab in lines:
    i = 0
    for j in range(len(lenn)):
        question[j].append(grab[i:(i+lenn[j])])
        i += lenn[j] + 1      

summ = 0
opr = len(question[0])

for q in question:
    op = q[-1].strip()
    curr = 0
    fq = []

    for i in range(-1, -len(q[0]) -1, -1):
        neww = 0
        for j in range(len(q)-1):
            if q[j][i] != ' ':
                neww *= 10
                neww += int(q[j][i])
        fq.append(neww)

    if op == '+':
        curr = 0
        for i in range(len(fq)):
            curr += int(fq[i])
    else:
        curr = 1
        for i in range(len(fq)):
            curr *= int(fq[i])

    summ += curr

print(summ)

# Answer:- check: 3263827, input: 9348430857627
