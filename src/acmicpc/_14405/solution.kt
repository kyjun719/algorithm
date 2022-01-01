package acmicpc._14405

fun main(){
    val arr=arrayOf("pi", "ka", "chu")
    val str = readLine()!!
    var i=0
    while(i <= str.length){
        when{
            i+2>str.length -> break
            str.substring(i,i+2) in arr -> {
                i+=2
            }
            i+3>str.length -> break
            str.substring(i,i+3) in arr -> {
                i+=3
            }
            else -> break
        }
    }
    if(i==str.length) {
        println("YES")
    } else {
        println("NO")
    }
}