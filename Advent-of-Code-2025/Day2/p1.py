file = 'input.txt'

with open(file, 'r') as f:
    line = f.read()

lines = line.split(',')

invalid = 0

for range in lines:
    begin, end = range.split('-')
    b, e = int(begin), int(end)
    i = b
    while i < e+1:
        num = str(i)
        l = len(num)

        if l % 2 == 0 and num[0:(l//2)] == num[(l//2):]:
            invalid += i

        i += 1
    
print(invalid)

