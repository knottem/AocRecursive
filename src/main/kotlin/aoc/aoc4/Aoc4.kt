package aoc.aoc4

import java.io.File

//https://adventofcode.com/2022/day/10
//version 2 with help of https://todd.ginsberg.com/post/advent-of-code/2022/day10/
// and https://github.com/MarcusDunn/aoc2022/blob/master/src/main/kotlin/io/github/marcusdunn/aoc2022/day10/CathodeRayTube.kt

class Cpu {
    var cycle = 0
    var value = 1
}

class Aoc4(text: String = "input.txt") {

    private val input = File("src/main/kotlin/aoc/aoc4/$text").readLines().toList()
    private val set = setOf(20, 60, 100, 140, 180, 220)
    fun getSumFirstVersion(): Int {
        var sum = 0
        val cpu = Cpu()
        input.forEach {
            cpu.cycle += 1
            if (cpu.cycle + 1 in set && it.startsWith("addx")) {
                sum += ((cpu.cycle + 1) * cpu.value)
            } else if (cpu.cycle in set) {
                sum += (cpu.cycle * cpu.value)
            }
            if (it.startsWith("addx")) {
                cpu.cycle += 1
                cpu.value += it.replace("addx ", "").toInt()
            }
        }
        return sum
    }

    fun getCrtFirstVersion(showPrintln: Boolean): String {
        val sprite = getListOfValues()
        var output = ""
        for (i in 0 until 240) {
            val x = i % 40
            val isPixelLit = sprite[i] in (x - 1)..(x + 1)
            output += (if (isPixelLit) "#" else ".")
            if (x == 39) {
                output += if (i == 239) "" else "\n"
            }
            if (showPrintln) println("Pixel: $x Sprite location: ${sprite[i]}  x = ${x - 1}, $x, ${x + 1}   $isPixelLit")
        }
        return output
    }

    private fun getListOfValues(): MutableList<Int> {
        val cpu = Cpu()
        val cycle = mutableListOf(cpu.value)
        input.forEach {
            cycle.add(cpu.value)
            if (it.startsWith("addx")) {
                cpu.value += it.replace("addx ", "").toInt()
                cycle.add(cpu.value)
            }
        }
        return cycle
    }
    fun getSumSecondVersion(): Int = getListOfValues().filterIndexed { index, _ -> index in set.map { it - 1 } }.withIndex().sumOf {  it.value * set.elementAt(it.index) }
    fun getCrtSecondVersion(): String = getListOfValues().asSequence().mapIndexed { index, value -> index % 40 to value }
        .map { (x, value) -> if (value in (x - 1)..(x + 1)) "#" else "." }
        .take(240)
        .chunked(40)
        .joinToString("\n") { it.joinToString("") }

}
