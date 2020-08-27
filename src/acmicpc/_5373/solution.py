import sys

sys.setrecursionlimit(100000)

U,D,F,B,L,R=0,1,2,3,4,5
# 윗 면은 흰색, 아랫 면은 노란색, 앞 면은 빨간색, 뒷 면은 오렌지색, 왼쪽 면은 초록색, 오른쪽 면은 파란색
# 흰색은 w, 노란색은 y, 빨간색은 r, 오렌지색은 o, 초록색은 g, 파란색은 b.
def initcube():
    cube = [list() for _ in range(6)]
    for i in range(6):
        if i==U:
            ch='w'
        elif i==D:
            ch='y'
        elif i==F:
            ch='r'
            # ch='g'
        elif i==B:
            ch='o'
            # ch='b'
        elif i==L:
            ch='g'
            # ch='o'
        elif i==R:
            ch='b'
            # ch='r'
        cube[i] = [[ch for _ in range(3)] for _ in range(3)]
    
    return cube

def turncube(cube, cmd):
    if cmd[0]=='U':
        cube[U]=turnplane(cube[U],cmd[1])
        if cmd[1]=='+':
            for i in range(3):
                tmp=cube[L][0][i]
                cube[L][0][i]=cube[F][0][i]
                cube[F][0][i]=cube[R][0][i]
                cube[R][0][i]=cube[B][0][i]
                cube[B][0][i]=tmp
        else:            
            for i in range(3):
                tmp=cube[L][0][i]
                cube[L][0][i]=cube[B][0][i]
                cube[B][0][i]=cube[R][0][i]
                cube[R][0][i]=cube[F][0][i]
                cube[F][0][i]=tmp
    elif cmd[0]=='D':
        cube[D]=turnplane(cube[D],cmd[1])
        if cmd[1]=='+':
            for i in range(3):
                tmp=cube[F][2][i]
                cube[F][2][i]=cube[L][2][i]
                cube[L][2][i]=cube[B][2][i]
                cube[B][2][i]=cube[R][2][i]
                cube[R][2][i]=tmp
        else:
            for i in range(3):
                tmp=cube[F][2][i]
                cube[F][2][i]=cube[R][2][i]
                cube[R][2][i]=cube[B][2][i]
                cube[B][2][i]=cube[L][2][i]
                cube[L][2][i]=tmp                
    elif cmd[0]=='F':
        cube[F]=turnplane(cube[F],cmd[1])
        if cmd[1]=='+':
            for i in range(3):
                tmp=cube[U][2][i]
                cube[U][2][i]=cube[L][2-i][2]
                cube[L][2-i][2]=cube[D][0][2-i]
                cube[D][0][2-i]=cube[R][i][0]
                cube[R][i][0]=tmp
        else:
            for i in range(3):
                tmp=cube[U][2][i]
                cube[U][2][i]=cube[R][i][0]
                cube[R][i][0]=cube[D][0][2-i]
                cube[D][0][2-i]=cube[L][2-i][2]
                cube[L][2-i][2]=tmp
    elif cmd[0]=='B':
        cube[B]=turnplane(cube[B],cmd[1])
        if cmd[1]=='+':
            for i in range(3):
                tmp=cube[D][2][2-i]
                cube[D][2][2-i]=cube[L][2-i][0]
                cube[L][2-i][0]=cube[U][0][i]
                cube[U][0][i]=cube[R][i][2]
                cube[R][i][2]=tmp
        else:
            for i in range(3):
                tmp=cube[D][2][2-i]
                cube[D][2][2-i]=cube[R][i][2]
                cube[R][i][2]=cube[U][0][i]
                cube[U][0][i]=cube[L][2-i][0]
                cube[L][2-i][0]=tmp
    elif cmd[0]=='L':
        cube[L]=turnplane(cube[L],cmd[1])
        if cmd[1]=='+':
            for i in range(3):
                tmp=cube[U][i][0]
                cube[U][i][0]=cube[B][2-i][2]
                cube[B][2-i][2]=cube[D][i][0]
                cube[D][i][0]=cube[F][i][0]
                cube[F][i][0]=tmp
        else:
            for i in range(3):
                tmp=cube[U][i][0]
                cube[U][i][0]=cube[F][i][0]
                cube[F][i][0]=cube[D][i][0]
                cube[D][i][0]=cube[B][2-i][2]
                cube[B][2-i][2]=tmp
    elif cmd[0]=='R':
        cube[R]=turnplane(cube[R],cmd[1])
        if cmd[1]=='+':
            for i in range(3):
                tmp=cube[U][i][2]
                cube[U][i][2]=cube[F][i][2]
                cube[F][i][2]=cube[D][i][2]
                cube[D][i][2]=cube[B][2-i][0]
                cube[B][2-i][0]=tmp
        else:
            for i in range(3):
                tmp=cube[U][i][2]
                cube[U][i][2]=cube[B][2-i][0]
                cube[B][2-i][0]=cube[D][i][2]
                cube[D][i][2]=cube[F][i][2]
                cube[F][i][2]=tmp
    return cube

def printupper(cube):
    for i in range(3):
        s=''
        for j in range(3):
            s+=cube[U][i][j]
        print(s)

def turnplane(plane,cmd):
    tmp = [['' for _ in range(3)] for _ in range(3)]
    if cmd=='+':
        for i in range(3):
            for j in range(3):
                tmp[i][j] = plane[2-j][i]
    else:
        for i in range(3):
            for j in range(3):
                tmp[i][j] = plane[j][2-i]
    return tmp

def printcube(cube):
    for i in range(6):
        print(cube[i])
    print()

T = int(sys.stdin.readline().rstrip())
for _ in range(T):
    n = int(sys.stdin.readline().rstrip())
    cube = initcube()
    cmdArr = sys.stdin.readline().rstrip().split()
    for cmd in cmdArr:
        cube = turncube(cube, cmd)
        #printcube(cube)
    printupper(cube)

