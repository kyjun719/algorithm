"""
https://algospot.com/judge/problem/read/FESTIVAL
PASS
"""

import sys


def main():
    tc = int(sys.stdin.readline())

    for t in range(tc):
        num_str = sys.stdin.readline().split(" ")
        n = int(num_str[0])
        l = int(num_str[1])

        arr = []
        arr_str = sys.stdin.readline().split(" ")
        for i in range(n):
            arr.append(int(arr_str[i]))

        add_arr = [[]] * (n-l+1)
        for i in range(n-l+1):
            add_arr[i] = [0] * n

        # add_arr[0][l-1] = sum(arr[0 ~ l-1])
        for i in range(l-1):
            add_arr[0][l-2] += arr[i]
        ret = 987654321

        for i in range(n-l+1):
            for j in range(l+i-1, n):
                add_arr[i][j] = add_arr[i][j-1] + arr[j]
                ret = min(ret, add_arr[i][j] / (j - i + 1))
            if (i+1) <= (n-l):
                add_arr[i+1][l+i-1] = add_arr[i][l+i-1] - arr[i]
        print(ret)


"""
1
6 3
1 2 3 1 2 3
## add_arr
[0,3,6,7,9,12]
[0,0,5,6,8,11]
[0,0,0,4,6,9]
[0,0,0,0,3,6]
"""


main()
