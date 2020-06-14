function solution(key, lock) {
    var answer = false;
    var m = key.length, n = lock.length;
    var nlock = [];
    for(var i = 0; i < n+2*m-2; i++) {
        nlock.push(Array(n+2*m-2).fill(0));
    }

    for(var i = 0; i < n; i++) {
        for(var j = 0; j < n; j++) {
            nlock[i+m-1][j+m-1] = lock[i][j];
        }
    }
    
    var keyArr = Array(4);
    keyArr[0] = key;
    for(var i = 1; i < 4; i++) {
        keyArr[i] = turnKey(keyArr[i-1]);
    }
    
    //console.log(cnt,',',nlock);
    for(var r = 0; r < m+n-1; r++){
        for(var c = 0; c < m+n-1; c++){
            for(var key of keyArr){
                if(canSolve(n,r,c,nlock,key)){
                    answer=true;
                    break;
                }
            }
            if(answer) {
                break;
            }
        }
    }
    return answer;
}

function canSolve(n, r, c, nlock, key) {
    var m = key.length;

    //console.log(r,'~',r+n, c,'~',c+n);
    //console.log(key);
    var tmp = [];
    for(var i =0; i<nlock.length; i++) {
        tmp.push(nlock[i].slice());
    }
    //console.log(tmp);
    for(var i=r; i<r+m; i++){
        //console.log(i,':',nlock[i].slice(c,c+n));
        for(var j=c; j<c+m;j++){
            if(key[i-r][j-c] === 1){
                //console.log(i,',',j,',',nlock[i][j]);
                if(tmp[i][j]===1) {
                    //console.log(i-r,',',j-c,'~',i,',',j);
                    return false;
                } else {
                    tmp[i][j] = 1;
                    //cnt--;
                }
            }
        }
    }

    for(var i = 0; i < n; i++) {
        for(var j = 0; j < n; j++) {
            if(tmp[i+m-1][j+m-1] === 0) {
                return false;
            }
        }
    }

    return true;
}

function turnKey(key){
    var ret = [];
    var m = key.length;
    for(var i=0; i < m; i++) {
        ret.push(Array(m).fill(0));
    }
    for(var i=0; i<m; i++){
        for(var j=0; j<m;j++){
            ret[i][j] = key[m-j-1][i];
        }
    }
    return ret;
}