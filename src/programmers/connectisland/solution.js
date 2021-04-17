function solution(n, costs) {
    var answer = 0;

    costs.sort((a,b) => {
        return a[2]-b[2];
    });
    var parent = new Array(n);
    for(var i = 0; i < n; i++) {
        parent[i] = i;
    }
    for(var edge of costs) {
        var a = edge[0];
        var b = edge[1];

        while(a != parent[a]) {
            a = parent[a];
        }
        while(b != parent[b]) {
            b = parent[b];
        }

        if(a == b) {
            continue;
        }

        if(a > b) {
            var tmp = b;
            b = a;
            a = tmp;
        }

        parent[b] = a;
        answer += edge[2];
    }
    
    return answer;
}

solution(4, [[0,1,1],[0,2,2],[1,2,5],[1,3,1],[2,3,8]]);