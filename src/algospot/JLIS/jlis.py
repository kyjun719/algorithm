"""
https://algospot.com/judge/problem/read/JLIS
timeover
"""

import sys


def main():
    c = int(sys.stdin.readline())
    global n, m, a, b, val
    for _ in range(c):
        a = []
        b = []
        val = []
        n, m = [int(tmp) for tmp in sys.stdin.readline().split(' ')]
        a = [int(tmp) for tmp in sys.stdin.readline().split(' ')]
        b = [int(tmp) for tmp in sys.stdin.readline().split(' ')]
        val = [[-1 for _ in range(m + 1)] for _ in range(n + 1)]
        # for fully search, start pos is -1
        # and start from -1, result = result - 2
        print(jlis(-1, -1) - 2)


def jlis(a_pos, b_pos):
    if val[a_pos][b_pos] != -1:
            return val[a_pos][b_pos]

    a_max = -9876543210 if a_pos == -1 else a[a_pos]
    b_max = -9876543210 if b_pos == -1 else b[b_pos]

    max_val = b_max
    if a_max > b_max:
        max_val = a_max

    ret = 2
    for i in range(a_pos + 1, n):
        if max_val < a[i]:
            tmp = jlis(i, b_pos) + 1
            if ret < tmp:
                ret = tmp

    for i in range(b_pos + 1, m):
        if max_val < b[i]:
            tmp = jlis(a_pos, i) + 1
            if ret < tmp:
                ret = tmp
    val[a_pos][b_pos] = ret
    return ret


main()
