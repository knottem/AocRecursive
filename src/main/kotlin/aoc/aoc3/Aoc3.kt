package aoc.aoc3

import java.io.File
import java.util.regex.Pattern

//https://adventofcode.com/2015/day/5
//version2 with the help of a deleted comment https://www.reddit.com/r/adventofcode/comments/3viazx/day_5_solutions/cxnxzd2/

class Aoc3(text: String = "input.txt") {

    private val input = File("src/main/kotlin/aoc/aoc3/$text").readText().trim().split("\r\n", "\n").map { it }


    fun version1FirstProblem(): Int =
        input.count { c ->
            !c.contains("ab") && !c.contains("cd") && !c.contains("pq") && !c.contains("xy") &&
                    c.count { it in setOf('a', 'e', 'i', 'o', 'u') } >= 3 &&
                    c.zipWithNext().any { it.first == it.second }
        }

    fun version1SecondProblem(): Int {
        var count = 0
        for (c in input) {
            var hasRepeatingPair = false
            var hasRepeatingChar = false
            for (i in 0 until c.length - 1) {
                val pair = c.substring(i, i + 2)
                if (c.indexOf(pair, i + 2) >= 0) {
                    hasRepeatingPair = true
                    break
                }
            }
            if (!hasRepeatingPair) {
                continue
            }
            for (i in 0 until c.length - 2) {
                if (c[i] == c[i + 2]) {
                    hasRepeatingChar = true
                    break
                }
            }
            if (hasRepeatingChar) {
                count++
            }
        }
        return count
    }

    fun version2FirstProblem(): Int =
        input.count { c ->
                    Pattern.compile("ab|cd|pq|xy").matcher(c).find().not() &&
                    Pattern.compile("(.*[aeiou]){3}").matcher(c).find() &&
                    Pattern.compile("(.)\\1").matcher(c).find() }

    fun version2SecondProblem(): Int = input.count { c ->
                    Pattern.compile("(..).*\\1").matcher(c).find() &&
                    Pattern.compile("(.).\\1").matcher(c).find() }
}