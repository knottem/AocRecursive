package aoc.aoc5

import java.io.File
import java.io.Serializable
import kotlin.math.abs

//https://adventofcode.com/2016/day/1

class Orientation{
    var direction = "N"
    var blocksX = 0
    var blocksY = 0
}

class Aoc5(text: String = "input.txt") {

    private val input = File("src/main/kotlin/aoc/aoc5/$text").readText()

    private fun walk(o: Orientation, turnDirection: Char, steps: Int) {
        when (o.direction) {
            "N" -> {
                o.direction = if (turnDirection == 'R') "E" else "W"
                o.blocksX += if (turnDirection == 'R') steps else -steps
            }
            "E" -> {
                o.direction = if (turnDirection == 'R') "S" else "N"
                o.blocksY += if (turnDirection == 'R') -steps else steps
            }
            "S" -> {
                o.direction = if (turnDirection == 'R') "W" else "E"
                o.blocksX += if (turnDirection == 'R') -steps else steps
            }
            "W" -> {
                o.direction = if (turnDirection == 'R') "N" else "S"
                o.blocksY += if (turnDirection == 'R') steps else -steps
            }
        }
    }

    fun getBlocksAwayFirstVersion(): Int {
        val o = Orientation()
        input.trim().split(", ").forEach{
            val turnDirection = it[0]
            val blocks = it.substring(1).toInt()
            walk(o, turnDirection, blocks)
        }
        return abs(o.blocksX) + abs(o.blocksY)
    }

    fun getBlocksAwayFirstVisitedTwiceFirstVersion(): Int {
        val o = Orientation()
        val visited = mutableSetOf<Pair<Int, Int>>()
        var x = 0
        var y = 0
        visited.add(Pair(x, y))
        input.trim().split(", ").forEach{
            val turnDirection = it[0]
            val blocks = it.substring(1).toInt()
            walk(o, turnDirection, blocks)
            repeat(blocks) {
                when (o.direction) {
                    "N" -> y++
                    "E" -> x++
                    "S" -> y--
                    "W" -> x--
                }
                if (Pair(x, y) in visited) {
                    return abs(x) + abs(y)
                } else {
                    visited.add(Pair(x, y))
                }
            }
        }
        return 0
    }

    fun getBlocksAwaySecondVersion() : Int = input.trim().split(", ").fold(Orientation()) { o, s -> walk(o, s[0], s.substring(1).toInt())
        o
    }.let { abs(it.blocksX) + abs(it.blocksY) }

    fun getBlocksAwayFirstVisitedTwiceSecondVersion() : Int {
        var x = 0
        var y = 0
        return input.trim().split(", ").fold(Pair(Orientation(), mutableSetOf(Pair(0, 0)))) { (o, visited), s ->
            walk(o, s[0], s.substring(1).toInt())
            repeat(s.substring(1).toInt()) {
                when (o.direction) {
                    "N" -> y++
                    "E" -> x++
                    "S" -> y--
                    "W" -> x--
                }
                if (Pair(x, y) in visited) {
                    return abs(x) + abs(y)
                } else {
                    visited.add(Pair(x, y))
                }
            }
            Pair(o, visited)
        }.let { 0 }
    }
}

fun main() {
    val a = Aoc5()
    println(a.getBlocksAwayFirstVersion())
    println(a.getBlocksAwayFirstVisitedTwiceFirstVersion())
    println(a.getBlocksAwaySecondVersion())
    println(a.getBlocksAwayFirstVisitedTwiceSecondVersion())
}