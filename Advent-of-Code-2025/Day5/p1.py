fileName = 'check.txt'
lines = [line.strip() for line in open(fileName, 'r').readlines()]

bounds = lines[:lines.index('')]
ids = lines[(lines.index('')+1):]

counter = 0

reached = False
for i in ids:
    for b in bounds:
        start, end = b.split('-')
        if int(start) <= int(i) <= int(end):
            counter += 1
            reached = True
            break
    if reached:
        continue

print(counter)

# Answer:- check: 3, input: 652

