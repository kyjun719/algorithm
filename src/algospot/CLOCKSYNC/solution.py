"""
https://algospot.com/judge/problem/read/CLOCKSYNC
PASS
"""

import sys


"""
0 :: 0,3,5
1 :: 0,8
2 :: 0,5,8
3 :: 1,6,8,9
4 :: 2,3,7,8,9
5 :: 3,7,8,9
6 :: 3,4
7 :: 1,3,4,7
8 :: 4
9 :: 1,9
10 :: 2,4
11 :: 1
12 :: 4
13 :: 9
14 :: 2,5,6,7
15 :: 2,5,6,7
"""


def main():
    tc = int(sys.stdin.readline())

    for t in range(tc):
        time_str_arr = sys.stdin.readline().replace("\n", "").split(" ")
        if time_str_arr[len(time_str_arr) - 1] == "":
            del time_str_arr[len(time_str_arr) - 1]
        time_arr = [int(a) for a in time_str_arr]
        clock_sw_arr = [[0, 3, 5], [0, 8], [0, 5, 8], [1, 6, 8, 9], [2, 3, 7, 8, 9],
                        [3, 7, 8, 9], [3, 4], [1, 3, 4, 7], [4], [1, 9], [2, 4],
                        [1], [4], [9], [2, 5, 6, 7], [2, 5, 6, 7]]
        print(int(task(time_arr, clock_sw_arr)))


def is_clock_set_12(time_arr):
    return time_arr == [12] * 16


def fail_to_move_time(pushed_sw, sel_time_num, clock_sw, time_arr):
    time = time_arr[sel_time_num]
    for time_num, sw_arr in enumerate(clock_sw):
        if (time_num == sel_time_num) | (len(sw_arr) == 0):
            continue
        if (len(sw_arr) == 1) & (sw_arr[0] == pushed_sw):
            if time != time_arr[time_num]:
                return True
    return False


def get_moved_time_arr(pushed_sw, clock_sw):
    time_arr = []
    for time_num, sw_arr in enumerate(clock_sw):
        if pushed_sw in sw_arr:
            time_arr.append(time_num)

    return time_arr


def get_sw_pushed_time(time):
    # 12 = 0, 9 = 1, 6 = 2, 3 = 3
    return 4 - (time/3)


def update_time_arr(time_arr, move_time_arr, sw_push_time):
    for time_num in move_time_arr:
        time_arr[time_num] += (3 * sw_push_time)
        if time_arr[time_num] > 12:
            time_arr[time_num] -= 12
        time_arr[time_num] = int(time_arr[time_num])


def remove_pushed_sw(clock_sw, pushed_sw):
    for clock_num, clock_arr in enumerate(clock_sw):
        if pushed_sw in clock_arr:
            clock_arr.remove(pushed_sw)


def get_pushed_number(sw_push_arr):
    push_num = 0
    for sw_push_num in sw_push_arr:
        if sw_push_num != -1:
            push_num += sw_push_num

    return push_num


def task(time_arr, clock_sw):
    # switch push arr
    sw_push_arr = [-1] * 10

    while sw_push_arr.count(-1) != 0:
        # check clock move only one switch
        if is_clock_set_12(time_arr):
            break

        for time_num, sw_arr in enumerate(clock_sw):
            # check all clock set to 12
            if is_clock_set_12(time_arr):
                break

            if len(sw_arr) == 1:
                pushed_sw = sw_arr[0]

                # check other clock also only move by this switch and same as time
                if fail_to_move_time(pushed_sw, time_num, clock_sw, time_arr):
                    return -1

                move_time_arr = get_moved_time_arr(pushed_sw, clock_sw)
                sw_push_time = get_sw_pushed_time(time_arr[time_num])

                # update time
                update_time_arr(time_arr, move_time_arr, sw_push_time)

                # remove pushed switch in clock arr
                remove_pushed_sw(clock_sw, pushed_sw)
                # set number of switch pushed
                sw_push_arr[pushed_sw] = sw_push_time

    # if all switch pushed and clock not set to 12, return -1
    if is_clock_set_12(time_arr):
        return get_pushed_number(sw_push_arr)
    else:
        return -1


main()
