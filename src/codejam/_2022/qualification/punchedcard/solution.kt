package codejam._2022.qualification.punchedcard


fun main() {
    val tc = readLine()!!.toInt()
    for(t in 1..tc){
        val (R,C) = readLine()!!.split(" ").map { it.toInt() }
        val sb = StringBuilder()
        sb.append("Case #$t:\n")
        for(r in 0 until (2*R+1)){
            for(c in 0 until C){
                when {
                    r==0 && c==0 || r==1 && c==0 -> {
                        sb.append("..")
                    }
                    r%2==0 -> {
                        sb.append("+-")
                    }
                    else -> {
                        sb.append("|.")
                    }
                }
            }
            if(r%2==0){
                sb.append("+\n")
            } else {
                sb.append("|\n")
            }
        }
        print(sb.toString())
    }
}