function solution(n) {
    var answer = 0;
    var arr =Array(n+1).fill(0);
    arr[1]=1;
    arr[2]=2;
    for(var i=3; i<=n; i++){
        arr[i] = (Number(arr[i-1])+Number(arr[i-2]))%1000000007;
    }
    //console.log(arr);
    answer=arr[n];
    return answer;
}