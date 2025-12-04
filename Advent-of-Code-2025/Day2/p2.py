import re

file = 'input.txt'

line = open(file, 'r').read()

ranges = line.split(',')

pattern = r"^(\d+)\1+$"

invalid = 0

for bound in ranges:
    begin, end = bound.split('-')
    b, e = int(begin), int(end)
    i = b
    while i < e+1:
        num = str(i)
        if re.match(pattern, num):
            invalid += i
        i += 1
    
print(invalid)

