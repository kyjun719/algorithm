function solution(n, edge) {
    var answer = 0;
    var adj = Array(n);

    for(var i = 0; i < n; i++) {
        adj[i] = [];
    }

    for(var tmp of edge) {
        var a = tmp[0]-1, b = tmp[1]-1;
        adj[a].push(b);
        adj[b].push(a);
    }
    var cnt = Array(n).fill(-1);
    var arr = [0];
    cnt[0] = 0;
    var cntMax = 0;
    while(arr.length != 0) {
        var now = arr.splice(0,1);
        for(var there of adj[now]) {
            if(cnt[there] === -1) {
                cnt[there] = cnt[now]+1;
                arr.push(there);
                if(cntMax < cnt[there]) {
                    answer = 1;
                    cntMax = cnt[there];
                } else if(cntMax === cnt[there]) {
                    answer++;
                }
            }
        }
    }

    return answer;
}