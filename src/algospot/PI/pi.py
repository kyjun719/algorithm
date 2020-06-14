"""
https://algospot.com/judge/problem/read/PI
5
12341234
11111222
12122222
22222222
12673939
"""

import sys


def main():
    c = int(sys.stdin.readline())
    global arr, val
    for _ in range(c):
        arr = [0]
        arr.extend([int(a) for a in sys.stdin.readline().replace('\n', '')])
        val = [sys.maxsize for _ in range(len(arr))]
        val[0] = 0
        tmp_list = [0]
        while True:
            next_tmp_list = []
            for tmp in tmp_list:
                for i in range(3, 6):
                    if tmp + i >= len(arr):
                        continue
                    val[tmp + i] = min(classify(arr[tmp+1:tmp + i+1]) + val[tmp],
                                       val[tmp + i])
                    next_tmp_list.append(tmp + i)
            if len(next_tmp_list) == 0:
                break
            tmp_list.clear()
            tmp_list.extend(set(next_tmp_list))

        print(val[-1])

def classify(tmp_arr):
    is_same = True
    for tmp in tmp_arr:
        if tmp != tmp_arr[0]:
            is_same = False
    if is_same:
        return 1

    is_positive = True
    is_temp = True
    pos_delta = tmp_arr[1] - tmp_arr[0]
    temp_delta = abs(tmp_arr[1] - tmp_arr[0])
    for idx in range(1, len(tmp_arr)):
        if pos_delta != (tmp_arr[idx] - tmp_arr[idx - 1]):
            is_positive = False
        if temp_delta != abs(tmp_arr[idx] - tmp_arr[idx - 1]):
            is_temp = False

    if is_positive:
        if pos_delta == 1:
            return 2
        else:
            return 5

    if is_temp:
        return 4

    return 10


def pi(pos):
    if pos == len(val):
        return 0

    if pos > len(val):
        return sys.maxsize

    if val[pos] != -1:
        return val[pos]

    ret = sys.maxsize

    for i in range(3, 6):
        ret = min(ret, pi(pos + i) + classify(arr[pos:pos+i]))

    val[pos] = ret
    return ret


main()
