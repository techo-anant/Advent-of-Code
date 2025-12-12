from math import prod

fileName = "input.txt"
lines = [line.strip() for line in open(fileName, 'r').readlines()]

presents = {}
areas = []

curr_present = -1
for line in lines:
    if line and line[0].isnumeric() and len(line) < 3:
        curr_present = int(line[0])
        presents[int(line[0])] = []
    
    elif len(line) == 3:
        presents[curr_present].append(list(line))
    
    elif line == '':
        continue
    
    else:
        areas.append(list(line.strip().split(':')))
    

# In fond memories of advent of code 2025
# def transform_present(present, transform):
#     p = present
#     return {
#         '90': [[p[j][2-i] for j in range(3)] for i in range(3)],
#         '180': [[p[2-i][2-j] for j in range(3)] for i in range(3)],
#         '270': [[p[2-j][i] for j in range(3)] for i in range(3)],
#         'ver': [[p[2-i][j] for j in range(3)] for i in range(3)],
#         'hor': [[p[i][2-j] for j in range(3)] for i in range(3)],
#         'dia': [[p[j][i] for j in range(3)] for i in range(3)],
#         'anti_dia': [[p[2-j][2-i] for j in range(3)] for i in range(3)]
#     }[transform]

# matrix_tran = ('90', '180', '270', 'ver', 'hor', 'dia', 'anit_dia')


counter = 0
for area in areas:
    grid_info = area[0]
    gird_size = prod(list(map(int, grid_info.split('x'))))
    fit_present = list(map(int, area[1].strip().split(' ')))

    curr_sum = 0
    for i in range(len(fit_present)):
        if fit_present[i] > 0:
            for j in range(fit_present[i]):
                curr_sum += sum([1 if presents[i][x][y] == '#' else 0 for x in range(3) for y in range(3)])

    if curr_sum < gird_size * 0.85: # thanks luke
        counter += 1
    
print(counter)
