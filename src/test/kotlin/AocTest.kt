import aoc.aoc1.Aoc1
import aoc.aoc2.Aoc2
import aoc.aoc3.Aoc3
import aoc.aoc4.Aoc4
import aoc.aoc5.Aoc5
import aoc.aoc6.Aoc6
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AocTest {

    @Test
    fun aocFirst() {
        // test file
        val aocTest = Aoc1("test.txt")
        assertEquals(30, aocTest.version1firstProblem())
        assertEquals(50, aocTest.version1SecondProblem())
        assertEquals(30, aocTest.version2FirstProblem())
        assertEquals(50, aocTest.version2SecondProblem())

        // correct answers
        val aoc1 = Aoc1()
        assertEquals(72602, aoc1.version1firstProblem())
        assertEquals(207410, aoc1.version1SecondProblem())
        assertEquals(72602, aoc1.version2FirstProblem())
        assertEquals(207410, aoc1.version2SecondProblem())
    }

    @Test
    fun aocSecond(){
        // test file
        val aocTest = Aoc2("test.txt")
        assertEquals(4, aocTest.version1FirstProblem())
        assertEquals(3, aocTest.version1SecondProblem())
        assertEquals(4, aocTest.version2FirstProblem())
        assertEquals(3, aocTest.version2SecondProblem())

        // correct answers
        val aoc2 = Aoc2()
        assertEquals(192, aoc2.version1FirstProblem())
        assertEquals(101, aoc2.version1SecondProblem())
        assertEquals(192, aoc2.version2FirstProblem())
        assertEquals(101, aoc2.version2SecondProblem())
    }

    @Test
    fun aocThird(){
        // test file
        val aocTest = Aoc3("test.txt")
        assertEquals(2, aocTest.version1FirstProblem())
        assertEquals(1, aocTest.version1SecondProblem())
        assertEquals(2, aocTest.version2FirstProblem())
        assertEquals(1, aocTest.version2SecondProblem())

        // correct answers
        val aoc3 = Aoc3()
        assertEquals(258, aoc3.version1FirstProblem())
        assertEquals(53, aoc3.version1SecondProblem())
        assertEquals(258, aoc3.version2FirstProblem())
        assertEquals(53, aoc3.version2SecondProblem())
    }

    @Test
    fun aocFourth(){
        // test file
        val aocTest = Aoc4("test.txt")
        assertEquals(13140, aocTest.getSumFirstVersion())
        assertEquals(13140, aocTest.getSumSecondVersion())
        val string : String =
                "##..##..##..##..##..##..##..##..##..##..\n" +
                "###...###...###...###...###...###...###.\n" +
                "####....####....####....####....####....\n" +
                "#####.....#####.....#####.....#####.....\n" +
                "######......######......######......####\n" +
                "#######.......#######.......#######....."
        assertEquals(string , aocTest.getCrtFirstVersion(false))
        assertEquals(string , aocTest.getCrtSecondVersion())

        // correct answers
        val aoc4 = Aoc4()
        assertEquals(14920,aoc4.getSumFirstVersion())
        val string2 : String =
                    "###..#..#..##...##...##..###..#..#.####.\n" +
                    "#..#.#..#.#..#.#..#.#..#.#..#.#..#....#.\n" +
                    "###..#..#.#....#..#.#....###..#..#...#..\n" +
                    "#..#.#..#.#....####.#....#..#.#..#..#...\n" +
                    "#..#.#..#.#..#.#..#.#..#.#..#.#..#.#....\n" +
                    "###...##...##..#..#..##..###...##..####."
        assertEquals(string2 , aoc4.getCrtFirstVersion(false))
        assertEquals(string2, aoc4.getCrtSecondVersion())
    }

    @Test
    fun aocFifth(){
        // test file
        assertEquals(12, Aoc5("test.txt").getBlocksAwayFirstVersion())
        assertEquals(4, Aoc5("test2.txt").getBlocksAwayFirstVisitedTwiceFirstVersion())
        assertEquals(12, Aoc5("test.txt").getBlocksAwaySecondVersion())
        assertEquals(4, Aoc5("test2.txt").getBlocksAwayFirstVisitedTwiceSecondVersion())

        // correct answer
        val aoc5 = Aoc5()
        assertEquals(278, aoc5.getBlocksAwayFirstVersion())
        assertEquals(161, aoc5.getBlocksAwayFirstVisitedTwiceFirstVersion())
        assertEquals(278, aoc5.getBlocksAwaySecondVersion())
        assertEquals(161, aoc5.getBlocksAwayFirstVisitedTwiceSecondVersion())
    }

    @Test
    fun aocSixth(){
        // test file
        val aocTest = Aoc6("test.txt")
        assertEquals(1, aocTest.part1V1())
        assertEquals(0, aocTest.part2V1())
        assertEquals(1, aocTest.part1V2())
        assertEquals(0, aocTest.part2V2())

        // correct answers
        val aoc6 = Aoc6()
        assertEquals(966, aoc6.part1V1())
        assertEquals(628, aoc6.part2V1())
        assertEquals(966, aoc6.part1V2())
        assertEquals(628, aoc6.part2V2())
    }

}