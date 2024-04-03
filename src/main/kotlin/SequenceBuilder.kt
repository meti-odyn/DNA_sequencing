class SequenceBuilder (private val spectrum: List<String>, private val groupLength: Int = 10) {

    private val groups: MutableMap<String,Pair<MutableList<String>,MutableList<String>>> = mutableMapOf()
    private val subsequences = mutableListOf<String>()

    init {
        fillGroups()
    }

    fun naiveHeuristic () {
        findSubsequences ()
        joinSubsequences ()
    }

    fun findSubsequences () {
        while (groups.isNotEmpty()) { subsequences.add(findSubsequence()) }
    }

    fun joinSubsequences (until: Int = 1) { // groupLength/2

        for(offset in groupLength - 1 downTo until) {
            if(subsequences.size == 1)
                break
            for(j in 0..subsequences.lastIndex) {
                //val match = subsequences.filter { subsequence != it && it.substring(0, it.length - offset) == subsequence.substring(offset)}
                if(j > subsequences.lastIndex)
                    break
                glueSubsequences(offset, j)
            }
        }
    }

    fun getSubsequences () = subsequences.toList().sortedBy { - it.length }

    fun showGroups () = println(groups)

    private fun glueSubsequences (offset : Int, key : Int) {
        var otherKey = 0
        var currentKey = key
        while (otherKey < subsequences.size && subsequences.size > 1) {
            if (subsequences[currentKey] != subsequences[otherKey] &&
                subsequences[currentKey].substring(subsequences[currentKey].length - offset) ==
                subsequences[otherKey].substring(0, offset))
            {
                subsequences[currentKey] += subsequences[otherKey].substring(offset)
                subsequences.removeAt(otherKey)
                if (otherKey < currentKey)
                    currentKey--
                otherKey = -1
            }
            otherKey++
        }
    }

    private fun findSubsequence (offset: Int = 1): String {

        val key = groups.keys.first()
        val core = groups.remove(key)
        return getPrefix(core!!.first, offset) + key + getSuffix(core.second, offset)
    }

    private fun fillGroups (offset: Int = 1, subspectrum: List<String> = spectrum) {
        subspectrum.forEach {
            groups[it] = Pair(
                (spectrum.filter { subseq -> it.substring(0, subseq.length - offset) == subseq.substring(offset) }).toMutableList(),
                (spectrum.filter { subseq -> subseq.substring(0, subseq.length - offset) == it.substring(offset) }).toMutableList()) }
    }

    private fun getPrefix (core: MutableList<String>, chars: Int = 1): String {

        while(core.isNotEmpty() and !groups.containsKey(core.getOrNull(0))) //!groups.containsKey(core.first) throws exception why???
            core.removeAt(0)
        return if (core.isEmpty()) "" else getPrefix( groups.remove(core.first)!!.first) +
                core.first.subSequence(0,chars)
    }

    private fun getSuffix (core: MutableList<String>, chars: Int = 1): String {

        while(core.isNotEmpty() and !groups.containsKey(core.getOrNull(0))) //!groups.containsKey(core.first) throws exception why???
            core.removeAt(0)
        return if (core.isEmpty()) "" else core.first.substring(core.first.length - chars,core.first.length) +
                getSuffix( groups.remove(core.first)!!.second)
    }

}