package aoc.aoc2

import java.io.File

//https://adventofcode.com/2020/day/4
//version2 with the help of https://github.com/kotlin-hands-on/advent-of-code-2020/blob/master/src/day04/day4.kt
class Aoc2(text : String = "passports.txt") {

    private val input = File("src/main/kotlin/aoc/aoc2/$text").readText().trim().split("\n\n", "\r\n\r\n").toList()

    private fun inputToListMap(): List<Map<String, String>> {
        val listOfMaps: MutableList<Map<String, String>> = mutableListOf()
        input.forEach { c ->
            val map2 = mutableMapOf<String, String>()
            c.split("\n", "\r\n").forEach { d ->
                d.split(" ").forEach { e ->
                    val (key, value) = e.split(":")
                    map2[key] = value
                }
            }
            listOfMaps += map2
        }
        return listOfMaps
    }

    fun version1FirstProblem(): Int =
        input.count { c -> setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid").all { f -> c.contains(f) } }

    fun version1SecondProblem(): Int {
        var total = 0
        inputToListMap().forEach { c ->
            var counter = 0
            c.forEach { d ->
                when (d.key) {
                    "byr" -> if (d.value.toInt() in 1920..2002) counter++
                    "iyr" -> if (d.value.toInt() in 2010..2020) counter++
                    "eyr" -> if (d.value.toInt() in 2020..2030) counter++
                    "hgt" -> if (d.value.contains("cm")) {
                        if (d.value.replace("cm", "").toIntOrNull() in 150..193) counter++
                    } else if (d.value.contains("in")) {
                        if (d.value.replace("in", "").toIntOrNull() in 59..76) counter++
                    }

                    "hcl" -> if (d.value.matches(Regex("#[0-9a-f]{6}"))) counter++
                    "ecl" -> if (d.value.matches(Regex("amb|blu|brn|gry|grn|hzl|oth"))) counter++
                    "pid" -> if (d.value.matches(Regex("[0-9]{9}"))) counter++
                }
            }
            if (counter >= 7) {
                total++
            }
        }
        return total
    }


    //version2
    private fun inputToListMapV2(): List<Map<String, String>> {
        return input.map { c ->
            c.split("\n", "\r\n").flatMap { d ->
                d.split(" ").map { e ->
                    val (key, value) = e.split(":")
                    key to value
                }
            }.toMap()
        }
    }

    private fun valid(map: Map<String, String>): Boolean =
        map.all { (key, value) ->
            when (key) {
                "byr" -> (value.toInt() in 1920..2002)
                "iyr" -> (value.toInt() in 2010..2020)
                "eyr" -> (value.toInt() in 2020..2030)
                "hgt" -> if (value.contains("cm")) {
                    (value.replace("cm", "").toInt() in 150..193)
                } else if (value.contains("in")) {
                    (value.replace("in", "").toInt() in 59..76)
                } else false
                "hcl" -> (value.matches(Regex("#[0-9a-f]{6}")))
                "ecl" -> (value.matches(Regex("amb|blu|brn|gry|grn|hzl|oth")))
                "pid" -> (value.matches(Regex("[0-9]{9}")))
                else -> true //cid eller CORRECT:CORRECT blir true
            }
        }

    private val validCheck = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    fun version2FirstProblem(): Int = inputToListMapV2().count { c -> validCheck.all { f -> c.contains(f) } }
    fun version2SecondProblem(): Int = inputToListMapV2().count { c -> validCheck.all { f -> c.contains(f) && valid(c) } }

}