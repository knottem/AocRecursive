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

    fun getUnderlingsV2(tomte: String): List<String> {
        val tomteList = tomtarna[tomte]
        if (tomteList != null) {
            return tomteList.flatMap { listOf(it.name) + getUnderlingsV2(it.name) }
        }
        return listOf()
    }
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
    val lista = Tomte().getUnderlingsV2("Räven")
    println(lista)
}

