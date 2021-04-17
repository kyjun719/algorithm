function solution(routes) {
    var answer = 0;
    routes.sort((a,b) => {
        return a[1]-b[1]; 
    });

    while(routes.length != 0) {
        var now = routes.splice(0, 1);
        answer++;
        if(routes.length == 0 || now == undefined) {
            break;
        }
        
        while(routes.length != 0) {
            if(routes[0][0] <= now[0][1]) {
                routes.splice(0, 1);
            } else {
                break;
            }
        }
    }
    return answer;
}