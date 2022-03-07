function solution(triangle) {
    var answer = 0;
    var n = triangle.length;
    cache = Array(n);
    for(var i = 0; i < n; i++) {
        cache[i] = Array(n).fill(-1);
    }
    answer = solve(triangle, 0, 0, triangle.length);
    return answer;
}

var cache = [[]];

function solve(triangle, r, c, n) {
    if(r == n) {
        return 0;
    }
    if(cache[r][c] != -1) {
        return cache[r][c];
    }
    var ret = 0;
    ret = Math.max(ret, triangle[r][c]+solve(triangle, r+1, c, n));
    if(c+1 < n) {
        ret = Math.max(ret, triangle[r][c]+solve(triangle, r+1, c+1, n));
    }
    //console.log(r,c,'>>',ret);
    cache[r][c] = ret;
    return ret;
}