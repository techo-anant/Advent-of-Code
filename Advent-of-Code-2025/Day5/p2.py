fileName = 'input.txt'
lines = [line.strip() for line in open(fileName, 'r').readlines()]

bounds = lines[:lines.index('')]

bounds = [list(map(int, b.split('-'))) for b in bounds]
bounds.sort(key=lambda x: x[0])

final = []
for bound in bounds:
    if not final:
        final.append(bound)
        continue

    is_final = False

    for f in final:
        if f[0] <= bound[0] <= f[1] or bound[0] <= f[1] <= bound[1]:
            is_final = True
            f[0] = min(f[0], bound[0])
            f[1] = max(f[1], bound[1])
            break
    
    if not is_final:
        final.append(bound)

num_good = 0
for s , e in final:
    num_good += e - s + 1 

print(num_good)
    
# Answer:- check: 14, input: 341753674214273