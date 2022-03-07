function solution(lines) {
    var answer = 0;
    var arr = [];
    for(var tmp of lines) {
        let t = tmp.split(' ');
        let e = converTime(t[1]);
        let s = Number(e) - Number((t[2].replace('s', ''))*1000)+1;
        arr.push([s,e]);
    }

    arr.sort((a,b) => a[0]-b[0]);

    for(var a of arr) {
        let cnts = 1, cnte = 1;
        for(var b of arr) {
            if(a == b) {
                continue;
            }
            if(isInBound(a[0], b)) {
                cnts++;
            }
            if(isInBound(a[1], b)) {
                cnte++;
            }
        }
        answer = Math.max(cnte, cnts, answer);
    }
    return answer;
}

function isInBound(t, bound) {
    return ((t < bound[0]) && ((t+1000) > bound[0])) ||
    ((t < bound[1]) && ((t+1000) > bound[1])) ||
    ((t >= bound[0]) && (t+1000) <= bound[1]);
}

function converTime(s) {
    let ret = 0;
    let arr = s.split(':');
    //hour
    ret += arr[0]*60*60*1000;
    //min
    ret += arr[1]*60*1000;
    //sec
    ret += Number(arr[2].split('.')[0]*1000);
    //milli sec
    ret += Number(arr[2].split('.')[1]);

    return ret;
}
