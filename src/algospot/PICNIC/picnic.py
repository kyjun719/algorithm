"""
https://algospot.com/judge/problem/read/PICNIC
PASS
"""

import sys


def main():
    t = int(sys.stdin.readline())

    for tc in range(t):
        str = input().split(" ")
        n = int(str[0])
        m = int(str[1])

        friends_map = []
        for i in range(n):
            friends_map.append([False] * n)

        info_arr = input().split(" ")
        for i in range(m):
            a = int(info_arr[i * 2])
            b = int(info_arr[(i * 2) + 1])
            friends_map[a][b] = True
            friends_map[b][a] = True

        is_select = [False] * n
        answer = find_set(friends_map, is_select, 0)

        print(answer)


def find_set(friends_map, is_select, cnt):
    start = -1
    if cnt == (len(is_select)/2):
        return 1

    for i in range(len(is_select)):
        if is_select[i] is False:
            is_select[i] = True
            start = i
            break

    if start == -1:
        return 0

    result = 0
    for i in range(start + 1, len(is_select)):
        if (friends_map[start][i] is True) & (is_select[i] is False):
            is_select[i] = True
            result += find_set(friends_map, is_select, cnt + 1)
            is_select[i] = False
    is_select[start] = False

    return result


main()
