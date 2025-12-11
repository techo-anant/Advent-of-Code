from collections import deque
import copy

fileName = "input.txt"
lines = [line.strip().split(' ') for line in open(fileName, 'r').readlines()]

# to get convversed state after button push
def conv_state ( state, btn):
    new_state = copy.copy(state)
    for b in btn:
        new_state[int(b)] = not new_state[int(b)]
    return new_state

# to get minimium # of clicks
def get_min_buttons ( btns, final):
    init = [False]*len(final)
    q = deque()
    q.append((-1, init, 0))
    while True:
        bt, curr, depth = q.popleft()
        for i in range(len(btns)):
            if i == bt:
                continue
            convevrged = conv_state(curr, btns[i])

            if convevrged == final:
                return depth+1
            else:
                if convevrged not in [x for _, x, _ in q]:
                    q.append((i, convevrged, depth+1))

ans = 0
for line in lines:
    final, btns = line[0], line[1:-1]
    final = [x == '#' for x in list(final)[1:-1]]
    btns = [''.join(list(btn)[1:-1]).split(',') for btn in btns]
    ans += get_min_buttons(btns, final)

print(ans)

# Answer:- check: 7, input: 425