package acmicpc._1000

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


fun main(args: Array<String>){
    BufferedReader(InputStreamReader(System.`in`))
        .readLine()
        .split(" ")
        .reduce { total, num -> (total.toInt()+num.toInt()).toString()}
        .also { println(it) }
}

fun main2(args: Array<String>) = with(Scanner(System.`in`)) {
    println("${nextInt()+nextInt()}")
}