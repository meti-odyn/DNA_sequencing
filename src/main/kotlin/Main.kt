import java.io.File

fun createList (seq: String, subSeqLength: Int = 10): MutableList<String> {
    val set: MutableSet<String> = mutableSetOf()
    for(i in subSeqLength .. seq.length)
        set.add(seq.substring(i - subSeqLength, i))
    return set.shuffled().toMutableList()
}

fun trial () {
    val origin = "ACGTCTTGACGCCAACTGCC"
    val spectrum = createList(origin)
    val dnaFinder = SequenceBuilder(spectrum, Int.MAX_VALUE)

    val sequence = dnaFinder.naiveHeuristicWithCutting()

    println(spectrum)
    println("sequence $sequence")
    println("origin $origin")
    println(if(origin == sequence) "found sequence is correct" else "found sequence is incorrect")
}

fun showSubsequences (subsequences: List<String>) {
    for(dna in subsequences) {
        println("dna: $dna\nlength: ${dna.length}")
    }
    println("subsequences count: ${subsequences.size}")
}

fun main() { //args: Array<String>

    val instancesPath = "C:/Users/48519/Documents/Studia/semestr 6/BioInformatyka/DNA_sequencing/src/instances/"
    val fileIndex = 0
    val files = listOf ("9.200-40.txt", "53.500-200.txt", "34.500-32.txt", "113.500-8.txt", "10.500+200.txt", "25.500+50.txt")
    val n = listOf(209, 509, 509, 509, 509, 509)

    for (i in n.indices) {
        val file = File(instancesPath + files[i])
        val dnaFinder = SequenceBuilder( file.useLines { it.toList() }, n[i])
        var dnaResault = dnaFinder.naiveHeuristic()
        println("naive")
        println("dna: $dnaResault\nlength: ${dnaResault.length}")

        dnaResault = dnaFinder.naiveHeuristicWithCutting()
        println("naive with cutting")
        println("dna: $dnaResault\nlength: ${dnaResault.length}")
        println()
    }
}