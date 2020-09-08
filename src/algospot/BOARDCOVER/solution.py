"""
https://algospot.com/judge/problem/read/BOARDCOVER
PASS

"""

import sys


def main():
    tc = int(sys.stdin.readline())

    for t in range(tc):
        info_arr = sys.stdin.readline().replace("\n", "").split(" ")
        global h, w
        h, w = [int(info_arr[0]), int(info_arr[1])]
        arr = list()
        cnt = 0
        for i in range(h):
            tmp = sys.stdin.readline()
            tmp_arr = list()
            for j in range(w):
                tmp_arr.append(True if (tmp[j] == '.') else False)
            arr.append(tmp_arr)
            cnt += tmp_arr.count(True)

        if (cnt % 3) != 0:
            print(0)
        else:
            print(task(arr, 0, cnt))


block_arr = [[[0, 0], [0, 1], [1, 0]], [[0, 0], [0, -1], [1, 0]],
             [[0, 0], [1, 0], [1, -1]], [[0, 0], [1, 0], [1, 1]],
             [[0, 0], [0, -1], [1, -1]], [[0, 0], [0, 1], [1, 1]]]


def task(arr, ptr, remain):
    if remain == 0:
        return 1

    """
    ptr = 6 :: row=0, col=6
    ptr = 7 :: row=1, col=0
    """
    row = int(ptr / len(arr[0]))
    col = ptr % len(arr[0])
    
    if (row == (h - 1)) & (col == (w - 1)):
        return 0

    if arr[row][col] is False:
        return task(arr, ptr + 1, remain)

    ret = 0

    for block in block_arr:
        if block_in_bound(arr, ptr, block):
            set_block(arr, ptr, block, False)
            ret += task(arr, ptr + 1, remain - 3)
            set_block(arr, ptr, block, True)
    return ret


def block_in_bound(arr, ptr, block):
    row = int(ptr / len(arr[0]))
    col = ptr % len(arr[0])
    for tmp in block:
        if (row + tmp[0] >= 0) & (row + tmp[0] < h) & (col + tmp[1] >= 0) & (col + tmp[1] < w):
            if arr[row + tmp[0]][col + tmp[1]] is False:
                return False
        else:
            return False

    return True


def set_block(arr, ptr, block, val):
    row = int(ptr / len(arr[0]))
    col = ptr % len(arr[0])
    for tmp in block:
        arr[row + tmp[0]][col + tmp[1]] = val


main()
