import sys
sys.setrecursionlimit(100000)

arr = [0]* 1004001
sosu = []
maxVal = 1004000
for i in range(2,1004001):
    if arr[i]==0:
        sosu.append(i)
        tmpVal = i*2
        while tmpVal <= maxVal:
            arr[tmpVal] = 1
            tmpVal += i

def isPelindrom(val):
    strVal = str(val)
    n = len(strVal)
    for i in range(int(n/2)):
        if strVal[i] != strVal[n-1-i]:
            return False
    return True

# print(sosu)
# print(len(sosu))
n = int(sys.stdin.readline().rstrip())
pelin = []
for val in sosu:
    if n > val:
        continue
    if isPelindrom(val):
        print(val)
        break