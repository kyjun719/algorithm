function solution(budgets, M) {
    var answer = 0;
    budgets.sort((a,b) => {
        return a-b;
    });
    var low = 1, high = budgets[budgets.length-1]+1;
    for(var i = 0; i < 100; i++) {
        var mid = parseInt((low+high)/2);
        var sum = 0;
        for(var tmp of budgets) {
            if(tmp > mid) {
                sum += mid;
            } else {
                sum += tmp;
            }
        }
        if(sum > M) {
            high = mid;
        } else {
            low = mid;
        }
    }
    answer = parseInt((low+high)/2);
    
    return answer;
}