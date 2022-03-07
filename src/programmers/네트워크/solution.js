function solution(n, computers) {
    var answer = 0;
    var visited = [];
    for(var i=0; i<n; i++) {
        visited.push(false);
    }
    
    for(var i=0; i<n; i++) {
        if(!visited[i]) {
            answer++;
            var arr = [i];
            while(arr.length != 0) {
                var here = arr.splice(0, 1);
                visited[here]=true;
                for(var there=0; there<n;there++) {
                    if(computers[here][there]===0) {
                        continue;
                    }
                    if(here === there || visited[there]) {
                        continue;
                    }
                    
                    arr.push(there);
                }
            }
        }
    }
    return answer;
}