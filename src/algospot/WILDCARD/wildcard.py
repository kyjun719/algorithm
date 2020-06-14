"""
https://algospot.com/judge/problem/read/WILDCARD
"""

import sys


def main():
    tc = int(sys.stdin.readline())
    for _ in range(tc):
        global regex, tmp, val
        regex = sys.stdin.readline().replace('\n', '')
        n = int(sys.stdin.readline())
        str_list = []
        for _ in range(n):
            str_list.append(sys.stdin.readline().replace('\n', ''))

        str_list.sort()

        for tmp in str_list:
            val = [[-1 for _ in range(len(tmp) + 1)] for _ in range(len(regex) + 1)]
            if wildcard(0, 0):
                print(tmp)


def wildcard(w_pos, s_pos):
    if (w_pos > len(regex)) | (s_pos > len(tmp)):
        return 0

    if val[w_pos][s_pos] != -1:
        return val[w_pos][s_pos]

    w_tmp = w_pos
    s_tmp = s_pos

    while True:
        if (w_pos >= len(regex)) | (s_pos >= len(tmp)):
            break

        if (regex[w_pos] == "?") | (regex[w_pos] == tmp[s_pos]):
            w_pos += 1
            s_pos += 1
        else:
            break

    ret = 0
    if (w_pos == len(regex)) & (s_pos == len(tmp)):
        ret = 1
    if w_pos < len(regex):
        if regex[w_pos] == "*":
            for i in range((len(tmp) - s_pos) + 1):
                if wildcard(w_pos + 1, s_pos + i):
                    ret = 1

    val[w_tmp][s_tmp] = ret
    return ret


main()
