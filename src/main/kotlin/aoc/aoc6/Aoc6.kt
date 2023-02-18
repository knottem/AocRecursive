package aoc.aoc6

import java.io.File

//https://adventofcode.com/2019/day/4
//version2  https://github.com/nibarius/aoc/blob/master/src/main/aoc2019/Day4.kt
class Aoc6(text: String = "input.txt") {

    private val input = File("src/main/kotlin/aoc/aoc6/$text").readText().take(6).toInt()
    private val input2 = File("src/main/kotlin/aoc/aoc6/$text").readText().takeLast(6).toInt()

    private fun validPasswordPart1(password: Int): Boolean {
        var valid = false
        for(i  in 1 until password.toString().length){
            if(password.toString()[i-1] > password.toString()[i]) return false
            if(password.toString()[i-1] == password.toString()[i]) valid = true
        }
        return valid
    }

    private fun validPasswordPart2(password: Int): Boolean {
        var valid = false
        var count = 1
        for(i  in 1 until password.toString().length){
            if(password.toString()[i-1] > password.toString()[i]) return false
            if(password.toString()[i-1] == password.toString()[i]) count++
            else {
                if(count == 2) valid = true
                count = 1
            }
        }
        if(count == 2) valid = true
        return valid
    }

    fun part1V1(): Int {
        var count = 0
        for (i in input..input2) {
            if (validPasswordPart1(i)) count++
        }
        return count
    }

    fun part2V1(): Int {
        var count = 0
        for (i in input..input2) {
            if (validPasswordPart2(i)) count++
        }
        return count
    }


    fun part1V2(): Int = (input..input2).count { c -> (c.toString().zipWithNext().all { it.first <= it.second } && c.toString().zipWithNext().any { it.first == it.second })}
    fun part2V2(): Int = (input..input2).count { c -> (c.toString().zipWithNext().all { it.first <= it.second } && c.toString().zipWithNext().any { it.first == it.second }) && c.toString().groupingBy { it }.eachCount().any { it.value == 2 } }

}