package tomte

/*
Tomtarna på Nordpolen har en strikt chefs-hierarki
Högsta chefen för allt är "Tomten"
Under "Tomten" jobbar "Glader" och "Butter"
Under "Glader" jobbar "Tröger", "Trötter"och "Blyger"
Under "Butter" jobbar "Rådjuret", "Nyckelpigan", "Haren" och "Räven"
Under "Trötter" jobbar "Skumtomten"
Under "Skumtomten" jobbar "Dammråttan"
Under "Räven" jobbar "Gråsuggan" och "Myran"
Under "Myran" jobbar "Bladlusen"
Er uppgift är att illustrera tomtens arbetshierarki i en lämplig datastruktur.
(Det finns flera tänkbara datastrukturer här)
Skriv sedan en funktion där man anger ett namn på tomten eller någon av hens underhuggare som
inparameter.
Funktionen listar sedan namnen på alla underlydande som en given person har
Exempel: Du anger "Trötter" och får som svar ["Skumtomten", "Dammråttan"]"
För att bli godkänd på uppgiften måste du använda rekursion.
 */

class Tomte (private val name : String = "") {

    fun getUnderlings(tomte: String, res: MutableList<String>): List<String> {
        val tomteList = tomtarna[tomte]
        if (tomteList != null) {
            for (t in tomteList) {
                res.add(t.name)
                getUnderlings(t.name, res)
            }
        }
        return res
    }

    fun getUnderlingsOldV2(tomte: String): List<String> {
        val tomteList = tomtarna[tomte]
        if (tomteList != null) {
            return tomteList.flatMap { listOf(it.name) + getUnderlingsOldV2(it.name) }
        }
        return listOf()
    }

    fun getUnderlingsOldV3(tomte: String): List<String> {
        if (tomtarna[tomte] != null) {
            return tomtarna[tomte]?.flatMap { listOf(it.name) + getUnderlingsOldV3(it.name) } ?: listOf()
        }
        return listOf()
    }
    fun getUnderlings(tomte: String): List<String> = tomtarna[tomte]?.flatMap { listOf(it.name) + getUnderlingsOldV3(it.name) } ?: listOf()

}

val tomtarna = mapOf(
    "Tomten" to listOf(Tomte("Glader"), Tomte("Butter")),
    "Glader" to listOf(Tomte("Tröger"), Tomte("Trötter"), Tomte("Blyger")),
    "Butter" to listOf(Tomte("Rådjuret"), Tomte("Nyckelpigan"), Tomte("Haren"), Tomte("Räven")),
    "Trötter" to listOf(Tomte("Skumtomten")),
    "Skumtomten" to listOf(Tomte("Dammråttan")),
    "Räven" to listOf(Tomte("Gråsuggan"), Tomte("Myran")),
    "Myran" to listOf(Tomte("Bladlusen")))

fun main() {
    val tomte = Tomte()
    val text = "Tomten"

    val start = System.nanoTime()
    println(tomte.getUnderlings(text,  mutableListOf()))
    val end = System.nanoTime()
    println("Version 1 Time: ${end - start} ns")

    val start2 = System.nanoTime()
    println(tomte.getUnderlingsOldV2(text))
    val end2 = System.nanoTime()
    println("Version 2 Time: ${end2 - start2} ns")

    val start3 = System.nanoTime()
    println(tomte.getUnderlingsOldV3(text))
    val end3 = System.nanoTime()
    println("Version 3 Time: ${end3 - start3} ns")

    val start4 = System.nanoTime()
    println(tomte.getUnderlings(text))
    val end4 = System.nanoTime()
    println("Version 4 Time: ${end4 - start4} ns")
}

