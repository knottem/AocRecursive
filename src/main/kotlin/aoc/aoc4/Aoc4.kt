package aoc.aoc4

import java.io.File


//https://adventofcode.com/2022/day/10

class Cpu {
    var cycle = 0
    var value = 1
}

class Aoc4(text: String = "input.txt") {

    private val input = File("src/main/kotlin/aoc/aoc4/$text").readLines().toList()
    private val cycleNumbers = listOf(20, 60, 100, 140, 180, 220)

    fun getSumFirstVersion() : Int {
        var sum = 0
        val cpu = Cpu()
        input.forEach {
            cpu.cycle += 1
            if (cpu.cycle + 1 in cycleNumbers && it.startsWith("addx")) {
                sum += ((cpu.cycle + 1) * cpu.value)
            } else if (cpu.cycle in cycleNumbers) {
                sum += (cpu.cycle * cpu.value)
            }
            if (it.startsWith("addx")) {
                cpu.cycle += 1
                cpu.value += it.replace("addx ", "").toInt()
            }
        }
        return sum
    }

    fun getCrtFirstVersion(): String {
        val cpu = Cpu()
        val sprite = mutableListOf(cpu.value)
        input.forEach {
            sprite.add(cpu.value)
            if(it.startsWith("addx")) {
                cpu.value += it.replace("addx ", "").toInt()
                sprite.add(cpu.value)
            }
        }
        var output = ""
        for (i in 0 until 240) {
            val x = i % 40
            val spriteX = sprite[i]
            val isPixelLit = spriteX in (x - 1)..(x + 1)
            output += (if (isPixelLit) "#" else ".")
            if (x == 39) output += "\n"
        }
        return output
    }

}

fun main() {

    val b = Aoc4()
    println(b.getCrtFirstVersion())

}


