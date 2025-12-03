
fileName = 'check.txt'

lines = open(fileName, 'r').readlines()


def find12( l , remaining ):
    if len(l) == remaining:
        return ''.join(l)
    
    if remaining == 1:
        return max(l)

    curr_at = 0
    curr_max = -float('Inf')

    for i in range(0, (len(l) - remaining + 1)):
        if int(l[i]) > curr_max:
            curr_max = int(l[i])
            curr_at = i

    return str(curr_max) + find12(l[curr_at+1:], remaining-1)


summ = 0
for line in lines:
    summ += int(find12(list(line.strip()), 12))

print(summ)

# Answer: check -> 3121910778619, input -> 173161749617495