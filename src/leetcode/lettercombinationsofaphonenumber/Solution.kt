class Solution {
    var map: Map<Char,Array<String>>
    init{
        map=mapOf(
	        '2' to arrayOf("a","b","c"),
	        '3' to arrayOf("d","e","f"),
	        '4' to arrayOf("g","h","i"),
	        '5' to arrayOf("j","k","l"),
	        '6' to arrayOf("m","n","o"),
	        '7' to arrayOf("p","q","r","s"),
	        '8' to arrayOf("t","u","v"),
	        '9' to arrayOf("w","x","y","z")
	    )
    }

    fun letterCombinations(digits: String): List<String> {
        var q=mutableListOf<String>()
        q.add("")
        for(i in digits){
            var next=mutableListOf<String>()
            while(q.size>0){
                var s=q.removeAt(0)
				for(ch in map[i]!!){
					next.add(s+ch)
				}
            }
            q.addAll(next)
        }
        if(q[0]==""){
            q.removeAt(0)
        }
        return q
    }
}