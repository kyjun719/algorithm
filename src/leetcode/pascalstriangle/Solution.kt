private class pascalstriangle{
    class Solution {
        fun generate(numRows: Int): List<List<Int>> {
            var ret = mutableListOf<List<Int>>()
            if(numRows>0) {
                ret.add(listOf(1))
            }
            for(i in 1..numRows-1) {
                var tmp = MutableList<Int>(i+1, {1})
                //ret[i][0] = ret[i][i] = 1
                for(j in 1..i-1) {
                    tmp[j]=ret[i-1][j-1]+ret[i-1][j]
                }
                ret.add(tmp)
            }
            return ret
        }
    }
}
