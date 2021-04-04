private class courseschedule{
    class Solution {
        fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
            var dtt=mutableMapOf<Int,MutableList<Int>>()
            var ttd=mutableMapOf<Int,MutableList<Int>>()
            var arr=IntArray(numCourses){-1}
            prerequisites.forEach{
                var a=it[0]
                var b=it[1]
                if(dtt[a]==null){
                    dtt[a]=mutableListOf<Int>()
                }
                if(ttd[b]==null){
                    ttd[b]=mutableListOf<Int>()
                }
                arr[a]=1
                if(arr[b]==-1){
                    arr[b]=0
                }
                dtt[a]!!.add(b)
                ttd[b]!!.add(a)
            }

            //find root
            var root=mutableListOf<Int>()
            for(i in 0..arr.size-1){
                if(arr[i]==0){
                    root.add(i)
                }
            }

            while(root.size>0){
                var tmp=root.removeAt(0)
                arr[tmp]=2
                ttd[tmp]?.let{
                    for(i in it){
                        dtt[i]?.remove(tmp)
                        if(dtt[i]?.size==0){
                            root.add(i)
                        }
                    }
                }
            }

            for(i in arr){
                if(i==0 || i==1){
                    return false
                }
            }
            return true
        }
    }
}
