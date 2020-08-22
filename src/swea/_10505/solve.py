def main():
    T = int(input())
    # 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
    for test_case in range(1, T + 1):
        n = int(input())
        arr = list(map(int, input().split()))
        avg = sum(arr)/n
        cnt = len(list(filter(lambda x: x <= avg, arr)))
        print("#"+str(test_case), cnt)

main()