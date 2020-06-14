"""
https://algospot.com/judge/problem/read/LIS
"""

import sys


def main():
    c = int(sys.stdin.readline())
    global n, arr, val
    arr = []
    val = []
    for _ in range(c):
        arr.clear()
        val.clear()
        n = int(sys.stdin.readline())
        arr = [int(tmp) for tmp in sys.stdin.readline().replace('\n', '').split(' ')]
        val = [-1 for _ in range(n)]
        print(lis(-1) - 1)


def lis(pos):
    if (pos != -1) & (val[pos] != -1):
        return val[pos]

    ret = 1
    if pos == -1:
        a = range(n)
    else:
        a = range(pos, n)
    for i in a:
        if (pos == -1) | (arr[i] > arr[pos]):
            ret = max(ret, lis(i) + 1)
    val[pos] = ret
    return ret


main()

"""
3
4
1 2 3 4
8
5 4 3 2 1 6 7 8
8
5 6 7 8 1 2 3 4
"""