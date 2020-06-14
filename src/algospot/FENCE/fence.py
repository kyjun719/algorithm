"""
https://algospot.com/judge/problem/read/FENCE
PASS
"""

import sys

# check left, right and both


def main():
    tc = int(sys.stdin.readline())

    for _ in range(tc):
        fence_length = int(sys.stdin.readline())
        global fence
        fence = list(map(int, sys.stdin.readline().split()))

        print(task(0, fence_length - 1))


def task(left, right):
    if left == right:
        return fence[left]

    mid = (left + right)//2

    left_area = task(left, mid)
    right_area = task(mid + 1, right)
    if left_area > right_area:
        val = left_area
    else:
        val = right_area

    start = mid
    end = mid + 1
    if fence[start] > fence[end]:
        height = fence[end]
    else:
        height = fence[start]

    if val < (height * 2):
        val = height * 2

    while True:
        if start == left:
            if end == right:
                break
            else:
                end += 1
                if height > fence[end]:
                    height = fence[end]
        else:
            if end == right:
                start -= 1
                if height > fence[start]:
                    height = fence[start]
            else:
                if fence[start-1] < fence[end+1]:
                    end += 1
                    if height > fence[end]:
                        height = fence[end]
                else:
                    start -= 1
                    if height > fence[start]:
                        height = fence[start]
        tmp = (end - start + 1) * height
        if val < tmp:
            val = tmp

    return val


main()
