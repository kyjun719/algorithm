import sys
sys.setrecursionlimit(100000)

text = sys.stdin.readline().rstrip()
stack = []
setList = []
for i in range(len(text)):
    ch = text[i]
    if ch=='(':
        stack.append(i)
    elif ch==')':
        start = stack.pop()
        setList.append((start, i))
# print(setList)

eqList = []
setLen = len(setList)

def addEq(removed):
    tmpText = ''
    # print('success::',removed)
    for i in range(len(text)):
        if removed[i]:
            continue
        tmpText += text[i]
    eqList.append(tmpText)

def findEq(i, setList, removed):
    if i == setLen:
        addEq(removed)
        return
    
    for next in range(i+1, setLen):
        removed[setList[next][0]] = True
        removed[setList[next][1]] = True
        # print(i,'->',next,'>>',removed)
        findEq(next, setList, removed)
        removed[setList[next][0]] = False
        removed[setList[next][1]] = False

    findEq(i+1, setList, removed)

for i in range(setLen):
    removed = [False]*len(text)
    removed[setList[i][0]] = True
    removed[setList[i][1]] = True
    # print('start',i,'>>',removed)
    findEq(i, setList, removed)

# print(eqList)
eqList = list(set(eqList))
eqList.sort()
for eq in eqList:
    print(eq)