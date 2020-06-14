"""
https://algospot.com/judge/problem/read/TRIANGLEPATH
"""

import sys


def main():
    c = int(sys.stdin.readline())
    global n, tri, val
    tri = []
    val = []
    for _ in range(c):
        tri.clear()
        val.clear()
        n = int(sys.stdin.readline())
        for _ in range(n):
            #print(str(sys.stdin.readline()).replace('\n', '').split(' '))
            tmp_arr = [int(a) for a in sys.stdin.readline().replace('\n', '').split(' ')]
            tri.append(tmp_arr)
            val.append([-1 for _ in range(len(tmp_arr))])
        n -= 1
        print(triangle(0, 0))


def triangle(y, x):
    if y == n:
        return tri[y][x]

    if val[y][x] != -1:
        return val[y][x]

    ret = max(triangle(y+1, x), triangle(y+1, x+1)) + tri[y][x]
    val[y][x] = ret
    return ret


main()


"""
2
5
6
1 2
3 7 4
9 4 1 7
2 7 5 9 4
5
1
2 4
8 16 8
32 64 32 64
128 256 128 256 128
"""