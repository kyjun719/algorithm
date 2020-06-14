function solution(N) {
    var answer = 0;
    var arr = [1,1];
    for(var i = 2; i <= N; i++) {
        arr[i] = arr[i-1]+arr[i-2];
    }
    answer = 2*(arr[N-1]+arr[N]);
    return answer;
}