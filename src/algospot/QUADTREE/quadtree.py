"""
https://algospot.com/judge/problem/read/QUADTREE
PASS
"""

import sys

"""
x1234 => x3412
1 => 1
"""

def task(tree, ptr):
    if (tree[ptr] == 'w') | (tree[ptr] == 'b'):
        return tree[ptr]

    ptr += 1
    # ptr is index of x
    top_left = task(tree, ptr)

    ptr += len(top_left)
    top_right = task(tree, ptr)

    ptr += len(top_right)
    bottom_left = task(tree, ptr)

    ptr += len(bottom_left)
    bottom_right = task(tree, ptr)

    return 'x' + bottom_left + bottom_right + top_left + top_right


def main():
    tc = int(sys.stdin.readline())
    for _ in range(tc):
        tree = sys.stdin.readline()
        if tree[len(tree) - 1] == "\n":
            tree = tree[:-1]

        print(task(tree, 0))


main()
