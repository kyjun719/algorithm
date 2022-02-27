package programmers.archerycontent

class Solution {
    var maxSum = -1
    var maxArr = IntArray(11){0}

    fun solution(n: Int, info: IntArray): IntArray {
        solve(0, info, IntArray(11){0}, n)
        if(maxSum == -1) {
            return intArrayOf(-1)
        }
        return maxArr
    }

    fun solve(idx: Int, info: IntArray, arr: IntArray, cnt: Int) {
        if(idx == 11) {
            arr[10] += cnt
            calc(info, arr)
            arr[10] -= cnt
            return
        }

        if(info[idx] < cnt) {
            arr[idx] = info[idx]+1
            solve(idx+1, info, arr, cnt-arr[idx])
            arr[idx] = 0
        }
        solve(idx+1, info, arr, cnt)
    }

    fun calc(info: IntArray, arr: IntArray) {
        var sum = 0
        var otherSum = 0
        for(i in 0..10){
            if(info[i] < arr[i]){
                sum+=(10-i)
            } else if(info[i] > 0){
                otherSum += (10-i)
            }
        }
//        println("${arr.joinToString()} >> $sum, $otherSum, $maxSum")
        if(sum <= otherSum) {
            return
        }

        sum -= otherSum

        if(maxSum < sum){
            maxSum = sum
            maxArr = arr.copyOf()
        } else if(maxSum==sum){
            for(i in 10 downTo 0){
                if(maxArr[i] > arr[i]) {
                    break
                }
                if(maxArr[i] < arr[i]){
                    maxArr = arr.copyOf()
                    break
                }
            }
        }
    }
}