function solution(tickets) {
    var answer = [];
    var adj = {};
    var visited = {};
    var cnt = 0;
    for(var tmp of tickets){
        if(adj[tmp[0]] == undefined){
            adj[tmp[0]]=[[tmp[1], cnt]];
        }else{
            adj[tmp[0]].push([tmp[1],cnt]);
        }
        cnt++;
    }

    for(var key of Object.keys(adj)) {
        visited[key] = adj[key].length-1;
        adj[key].sort((a,b) => {
            return a[0].localeCompare(b[0]);
        })
    }

    var cntArr = Array(cnt).fill(false);
    DFS('ICN', cntArr, adj, answer, cnt);
    
    return answer;
}

function DFS(now, cntArr, adj, answer, n) {
    answer.push(now);
    if(answer.length == n+1) {
        return;
    }
    
    if(adj[now] == undefined) {
        answer.splice(answer.length-1,1);
        return;
    }

    for(var next of adj[now]) {
        if(!cntArr[next[1]]) {
            cntArr[next[1]] = true;
            DFS(next[0], cntArr, adj, answer, n);
            if(answer.length == n+1) {
                return;
            }
            cntArr[next[1]] = false;
        }
    }
    answer.splice(answer.length-1,1);
}

//solution([['ICN', 'JFK'], ['HND', 'IAD'], ['JFK', 'HND']]);
//solution([['ICN', 'SFO'], ['ICN', 'ATL'], ['SFO', 'ATL'], ['ATL', 'ICN'], ['ATL','SFO']]);