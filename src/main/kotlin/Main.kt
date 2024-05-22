import java.io.File
import kotlin.time.measureTime

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

fun getQuality (spectrum: List<String>, dna: String, n: Int): Double {
    var quality = 0.0
    spectrum.forEach { if (dna.contains(it)) quality += 1.0 }
    return quality / (n - 9)
}

fun buildSequencesforAllFiles(path: String) {

    val dir = File(path)

    if (dir.isDirectory) {
        dir.listFiles()?.forEach { directory ->
            if(directory.isDirectory()) {
                println("${directory.name}:")
                directory.listFiles()?.forEach{ file ->
                    if (file.isFile()) {
                        val regex = Regex("""(?<=\.)\d+""")
                        val match = regex.find(file.name)

                        if (match != null) {
                            val number = match.groupValues[0].toInt() + 9
                            var dnaResault: String
                            val spectrum = file.useLines { it.toList()}
                            val duration = measureTime {
                                val dnaFinder = SequenceBuilder(spectrum, number)
                                dnaResault = dnaFinder.naiveHeuristicWithCutting()
                            }
                            println("n: $number \t length: ${dnaResault.length} \t quality: ${getQuality(spectrum, dnaResault, number)}" +
                                    " \t time: $duration \t\t dna: $dnaResault")
                        }
                    }
                }
                println()
            }
            else println("Wrong Path")
        }
    }
    else println("Wrong Path")
}

fun main() { //args: Array<String>

    val instancesPath = "C:/Users/48519/Documents/Studia/semestr 6/BioInformatyka/DNA_sequencing/src/instances/"
    buildSequencesforAllFiles(instancesPath)
}