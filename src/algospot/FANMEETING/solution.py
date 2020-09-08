"""
https://algospot.com/judge/problem/read/FANMEETING
timeover
"""

import sys


def main():
    tc = int(sys.stdin.readline())

    for _ in range(tc):
        member = sys.stdin.readline()
        fan = sys.stdin.readline()
        member = member.replace('\n', '')
        fan = fan.replace('\n', '')
        member = [(0 if tmp == 'F' else 1) for tmp in member]
        fan = [(0 if tmp == 'F' else 1) for tmp in fan]
        fan.reverse()
        print(fan)

        print(karatsuba(member, fan))
        print(karatsuba(member, fan)[len(member)-1: len(fan)].count(0))

"""
a0 a1
b0 b1
-> z0 = a0*b0
   z2 = a1*b1
   z1 = (a0+a1)*(b0+b1)-z0-z2
"""


# a*b = (a0 + a1) * (b0 + b1)
#     = a0*b0 + a1*b0+a0*b1 + a1*b1
#     = z0 + z1 + z2
def karatsuba(a, b):
    if len(a) < len(b):
        return karatsuba(b, a)

    if (len(a) == 0) or (len(b) == 0):
        return list()

    if (len(a) == 1) and (len(b) == 1):
        return [a[0] * b[0]]

    half = int(len(a)/2)
    a0 = a[:half]
    a1 = a[half:]
    if half > len(b):
        b0 = b
        b1 = []
    else:
        b0 = b[:half]
        b1 = b[half:]

    z0 = karatsuba(a0, b0)
    z2 = karatsuba(a1, b1)
    add_to(a0, a1, 0)
    add_to(b0, b1, 0)
    z1 = karatsuba(a0, b0)
    sub_from(z1, z0)
    sub_from(z1, z2)

    ret = z0
    add_to(ret, z1, half)
    add_to(ret, z2, half * 2)
    return ret


def add_to(a, b, ptr):
    if len(a) < (len(b) + ptr):
        a.extend([0 for _ in range((len(b) + ptr - len(a)))])

    for idx, b_tmp in enumerate(b):
        a[idx + ptr] += b_tmp


def sub_from(a, b):
    for idx, b_tmp in enumerate(b):
        a[idx] -= b_tmp


main()
