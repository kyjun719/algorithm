private class groupanagrams {
    class Solution {
        fun groupAnagrams(strs: Array<String>): List<List<String>> {
            var map=mutableMapOf<Int, MutableList<String>>()
            for(s in strs){
                var num=s.toCharArray().sorted().joinToString().hashCode()
                if(map[num]==null){
                    map[num]=mutableListOf<String>()
                }
                //println(num)
                //println(map[num])
                map[num]!!.add(s)
            }
            //println(map)
            var ret=mutableListOf<List<String>>()
            for(k in map.keys){
                ret.add(map[k]!!.toList())
            }
            return ret.toList()
        }
    }
}