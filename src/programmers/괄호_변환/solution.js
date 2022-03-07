function solution(p) {
    var answer = '';
    if(p.length == 0) {
        return answer;
    }
    var idx = 1;
    var cnt = p[0]=='('?1:-1;
    var isCorrect = p[0]==')'?false:true;
    var openCnt = p[0]=='('?1:0;
    while(cnt != 0 && idx < p.length) {
        var ch = p[idx];
        if(ch == '(') {
            openCnt++;
            cnt++;
        } else {
            if(openCnt > 0) {
                openCnt--;
            } else {
                isCorrect = false;
            }
            cnt--;
        }
        idx++;
    }
    var u = p.slice(0, idx), v=p.slice(idx);
    if(isCorrect) {
        answer = u+solution(v);
    } else {
        answer = '('+solution(v)+')';
        u = u.slice(1,u.length-1);
        var revu = '';
        for(var ch of u) {
            if(ch == '(') {
                revu += ')';
            } else {
                revu += '(';
            }
        }
        answer += revu;
    }
    //console.log(p,'>>',answer);
    return answer;
}