function solution(n) {
    var answer = [0];
    for(var i=1; i<n; i++) {
        var tmp = [...answer];
        answer.push(0);
        for(var j = tmp.length-1; j >= 0; j--) {
            console.log(tmp[j]);
            if(tmp[j] === 0) {
                answer.push(1);
            } else {
                answer.push(0);
            }
        }
        console.log(answer);
    }
    return answer;
}

solution(3);