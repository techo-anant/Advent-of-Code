start = 50
num_0 = 0

with open('input.txt', 'r') as f:
    lines = f.read().splitlines()

# lines = [line.strip() for line in open('input.txt', 'r').readlines()]

for line in lines:

    if line[0] == 'L':
        prev = start
        start -= int(''.join(list(line)[1:]))

        if start == 0:
            num_0 += 1
        elif start < 0:
            if prev != 0:
                num_0 += 1
            num_0 += abs(start)//100 
            start = (100 - abs(start)%100) % 100
        
    else:
        start += int(''.join(list(line)[1:]))
        if start > 99:
            num_0 += start // 100
            start %= 100 

print(num_0)