class SequenceBuilder (private val spectrum: List<String>, private val maxSequenceSize: Int ,private val groupLength: Int = 10) {

    private val groups: MutableMap<String,Triple<MutableList<String>,MutableList<String>, MutableList<MutableList<String>>>> = mutableMapOf()
    private var subsequences = mutableListOf<String>()

    fun naiveHeuristicWithCutting (): String {
        fillGroups()
        findSubsequences ()
        joinSubsequences (withCutting = true)
        subsequences.sortByDescending { it.length }
        return if(subsequences[0].length > maxSequenceSize) subsequences[0].substring(0, maxSequenceSize) else subsequences[0]
    }

    fun naiveHeuristic (): String {
        fillGroups()
        findSubsequences ()
        joinSubsequences ()
        subsequences.sortByDescending { it.length }
        return  subsequences[0]
    }

    fun findSubsequences () {
        while (groups.isNotEmpty()) { subsequences.add(findSubsequence()) }
    }

    fun joinSubsequences (until: Int = 1, withCutting: Boolean = false) {

        for(offset in groupLength - 1 downTo until) {
            if(subsequences.size == 1)
                break
            subsequences.sortBy { - it.length }
            for(key in 0..subsequences.lastIndex) {
                if(key > subsequences.lastIndex)
                    break
                glueSubsequences(offset, key, withCutting)
            }
        }
    }

    fun getSubsequences () = subsequences.sortedBy { - it.length }

    fun showGroups () = println(groups)

    private fun glueSubsequences (offset : Int, key : Int, withCutting: Boolean ) {
        var otherKey = 0
        var currentKey = key
        while (otherKey < subsequences.size && subsequences.size > 1) {
            if (subsequences[currentKey] != subsequences[otherKey] &&
                subsequences[currentKey].substring(subsequences[currentKey].length - offset) ==
                subsequences[otherKey].substring(0, offset) && (withCutting ||
                        maxSequenceSize > subsequences[currentKey].length - offset + subsequences[otherKey].length))
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
            groups[it] = Triple(
                (spectrum.filter { subseq: String -> it.substring(0, subseq.length - offset) == subseq.substring(offset) }).toMutableList(),
                (spectrum.filter { subseq -> subseq.substring(0, subseq.length - offset) == it.substring(offset) }).toMutableList(),
                mutableListOf()
            ) }

        groups.forEach {k, v ->
            v.first.forEach { groups[it]?.third?.add(v.first) }
            v.second.forEach{ groups[it]?.third?.add(v.second) }
        }
    }

    private fun getPrefix (core: MutableList<String>, chars: Int = 1): String {

        core.removeAll { !groups.containsKey(it) }

        return if (core.isEmpty()) {
            ""
        } else {
            var index = 0
            while(core.lastIndex > index && groups[core[index]]!!.first.isEmpty()) {
                index++
            }
            val key = core[index]
            getPrefix( removeFromGroups(key)!!.first) + key.subSequence(0,chars)
        }
    }

    private fun getSuffix (core: MutableList<String>, chars: Int = 1): String {

        core.removeAll { !groups.containsKey(it) }

        return if (core.isEmpty()) "" else {
            var index = 0
            while(core.lastIndex > index && groups[core[index]]!!.second.isEmpty()) {
                index++
            }
            val key = core[index]
            key.substring(key.length - chars, key.length) + getSuffix( removeFromGroups(key)!!.second)
        }
    }

    private fun removeFromGroups(key: String) : Triple<MutableList<String>,MutableList<String>, MutableList<MutableList<String>>>? {
        groups[key]?.third?.forEach { it.remove(key) }
        return groups.remove(key)
    }
}