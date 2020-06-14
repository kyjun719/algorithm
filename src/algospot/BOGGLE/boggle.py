"""
https://algospot.com/judge/problem/read/BOGGLE
FAIL
"""

import sys


def main():
    c = int(sys.stdin.readline())
    for tc in range(c):
        # alphabet_dict : key : 알파벳, value : 알파벳의 위치들의 배열
        alphabet_dict = dict()
        alphabet_dict.clear()

        # alphabet_dict 입력
        for i in range(5):
            insert_map(alphabet_dict, i, sys.stdin.readline())

        # 탐색 해야할 문자열 입력
        n = int(sys.stdin.readline())
        input_arr = []
        for i in range(n):
            tmp = sys.stdin.readline()
            tmp = tmp.replace("\n", "")
            tmp = tmp.replace("\0", "")
            input_arr.append(tmp)

        for find_str in input_arr:
            is_find = False
            # 각 알파벳이 입력 안에 있는지 확인
            if is_in_alphabet(alphabet_dict.keys(), find_str) is True:
                # 첫번째 알파벳이 있는 위치들 배열 가져옴
                start_pos = alphabet_dict[find_str[0]]
                for pos in start_pos:
                    # 두번째 알파벳 부터 탐색 시작
                    if find_word(alphabet_dict, find_str[1:], pos) is True:
                        is_find = True
                        break
            if is_find is True:
                find_str = find_str + " YES"
            else:
                find_str = find_str + " NO"

            print(find_str)


def insert_map(alphabet_dict, row, row_str):
    for i in range(5):
        tmp = row_str[i]
        if tmp in alphabet_dict.keys():
            alphabet_dict.get(tmp).append([row, i])
        else:
            alphabet_dict[tmp] = [[row, i]]


def is_in_alphabet(alphabet_list, input_str):
    for tmp in input_str:
        if tmp not in alphabet_list:
            return False
    return True


def find_word(alphabet_dict, find_str, ptr):
    if len(find_str) == 0:
        return True

    point_arr = alphabet_dict.get(find_str[0])
    result = False
    for point in point_arr:
        if is_in_bound(ptr, point):
            result = find_word(alphabet_dict, find_str[1:], point)
            if result is True:
                break
    return result


def is_in_bound(ptr, point):
    return (ptr[0] - point[0] >= -1) & (ptr[0] - point[0] <= 1) &\
           (ptr[1] - point[1] >= -1) & (ptr[1] - point[1] >= -1)


main()


"""
2
URLPM
XPRET
GIAET
XTNZY
XOQRS
6
PRETTY
GIRL
REPEAT
KARA
PANDORA
GIAZAPX
URLPM
XPRET
GIAET
XTNZY
XOQRS
6
PRETTY
GIRL
REPEAT
KARA
PANDORA
GIAZAPX
"""