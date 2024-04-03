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
    val dnaFinder = SequenceBuilder(spectrum)
    dnaFinder.naiveHeuristic()
    val sequence = dnaFinder.getSubsequences()[0]

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
    val files = listOf ("9.200-40.txt", "53.500-200.txt")
    val file = File(instancesPath + files[1])
    val dnaFinder = SequenceBuilder( file.useLines { it.toList() } )
//    dnaFinder.showGroups()
//    println()

    //dnaFinder.naiveHeuristic()
    dnaFinder.findSubsequences()
    dnaFinder.joinSubsequences(5)
    showSubsequences(dnaFinder.getSubsequences())
}