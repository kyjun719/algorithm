function solution(N, number) {
    if(N == number) {
        return 1
    }

    var val = [];
    for(var i = 0; i < 8; i++) {
        val.push(new Set([parseInt(String(N).repeat(i+1))]));
    }

    for(var i = 0; i < 8; i++) {
        for(var j = 0; j < i; j++) {
            //console.log(i,'>>',j,' ',(i-j-1));
            //console.log(val[j],' ',val[i-j]);
            ///*
            for(var a of val[j]) {
                for(var b of val[i-j-1]) {
                    ///*
                    val[i].add(a+b);
                    val[i].add(a-b);
                    val[i].add(a*b);
                    if(b !== 0) {
                        val[i].add(parseInt(a/b));
                    }
                    //*/
                   //console.log(i,'>>',a,' ',b);
                }
            }

            if(val[i].has(number)) {
                return i+1;
            }
            //*/
        }
    }

    return -1;
}

function solve(N, number, num, cnt) {
    console.log(num);
    if(cnt > 3) {
        return 987654321;
    }
    if(num === number) {
        return cnt;
    }

    let ret = 987654321;
    ret = Math.min(ret, solve(N,number,num*10+N,cnt+1));
    ret = Math.min(ret, solve(N,number,Number(num)+N,cnt+1));
    ret = Math.min(ret, solve(N,number,num-N,cnt+1));
    ret = Math.min(ret, solve(N,number,num*N,cnt+1));
    if(num%N === 0){
        ret = Math.min(ret, solve(N,number,num/N,cnt+1));
    }
    return ret;
}