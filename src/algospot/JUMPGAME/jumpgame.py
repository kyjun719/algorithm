"""
https://algospot.com/judge/problem/read/JUMPGAME
"""

import sys


def main():
    tc = int(sys.stdin.readline())
    for _ in range(tc):
        global n, board, val
        n = int(sys.stdin.readline())
        board = []
        val = []
        for _ in range(n):
            tmp = sys.stdin.readline().replace('\n', '')
            board.append([int(a) for a in tmp.split(' ')])
            val.append([-1 for _ in range(n)])
        ret = jump_game(0, 0)
        if ret:
            print("YES")
        else:
            print("NO")


def jump_game(y, x):
    if (y >= n) | (x >= n):
        return 0
    if (y == n-1) & (x == n-1):
        return 1
    if val[y][x] != -1:
        return val[y][x]

    step = board[y][x]
    val[y][x] = jump_game(y + step, x) | jump_game(y, x + step)
    return val[y][x]


main()