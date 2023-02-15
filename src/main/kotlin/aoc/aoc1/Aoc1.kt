package aoc.aoc1

import java.io.File

//https://adventofcode.com/2022/day/1
// Min andra version av https://adventofcode.com/2022/day/1 tog jag hjälp av https://github.com/osipxd/advent-of-code-2022/blob/main/src/Day01.kt


class Aoc1(text : String = "input.txt") {

    private val input = "src/main/kotlin/aoc/aoc1/$text"

    private fun getTextAsInt(text : String): List<List<Int>> {
        val file = File(text)
        val numList = mutableListOf<List<Int>>()
        var currentList = emptyList<Int>()
        file.forEachLine { line ->
            if (line.isBlank()) {
                if (currentList.isNotEmpty()) {
                    numList.add(currentList)
                    currentList = emptyList()
                }
            } else {
                currentList += line.split(" ").map { it.toInt() }
            }
        }
        if (currentList.isNotEmpty()) {
            numList.add(currentList)
        }
        return numList
    }


    fun version1firstProblem(): Int {
        val numList = getTextAsInt(input)
        var highSum = 0
        for (i in numList) {
            var currSum = 0
            for (j in i) {
                currSum += j
                if (currSum > highSum) {
                    highSum = currSum
                }
                if (currSum < 0) {
                    currSum = 0
                }
            }
        }
        return highSum
    }
    fun version1SecondProblem(): Int {
        val numList = getTextAsInt(input)
        val sumList = mutableListOf<Int>()
        for (i in numList) {
            var sum = 0
            for (j in i) {
                sum += j
                if (sum < 0) {
                    sum = 0
                }
            }
            sumList.add(sum)
        }
        return sumList.sortedDescending().take(3).sum()
    }

    fun version2FirstProblem(): Int {
        val test2 = mutableListOf<Int>()
        getTextAsInt(input).forEach { test2.add(it.sum())}
        return test2.max()
    }

    fun version2SecondProblem(): Int {
        val test2 = mutableListOf<Int>()
        getTextAsInt(input).forEach { test2.add(it.sum()) }
        return test2.sortedDescending().take(3).sum()
    }



}


