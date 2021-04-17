function solution(begin, target, words) {
    var answer = 987654321;
    var visited = Array(words.length).fill(false);
    var cnt = Array(words.length).fill(0);
    var q = [begin];
    visited[words.indexOf(begin)] = true;
    while(q.length != 0) {
        var w = String(q.splice(0, 1));
        var idx = words.indexOf(w);
        var cntVal = words.indexOf(w) == -1?0:cnt[words.indexOf(w)];
        if(w === target) {
            answer = cntVal;
            break;
        }
        for(var i in words) {
            if(!visited[i] && canGo(w, words[i])) {
                q.push(words[i]);
                visited[i] = true;
                cnt[i] = cntVal+1;
            }
        }
    }
    return answer==987654321?0:answer;
}

function canGo(a, b) {
    var defCnt = 0;
    for(var i = 0; i < a.length; i++) {
        if(a[i] != b[i]) {
            defCnt++;
        }
    }
    return defCnt === 1;
}