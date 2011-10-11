package me.bendoerr.hevollo

class TripleMutationAlgorithm implements MutationAlgorithm {

    String mutate(String original) {
        List bases = original.toList()
        bases = insertion(subtraction(substitution(bases)))
        return bases.join('')
    }

    List substitution(List bases) {
        Integer i = random.getInt(bases.size())
        bases[i] = getNewBase(bases[i])
        return bases
    }

    List insertion(List bases) {
        Integer size = bases.size()
        Integer insertionSize = random.getInt(3) + 2
        Integer index = random.getInt(bases.size() - 5)
        (1..insertionSize).each {
            bases.add index, getNewBase()
        }
        return bases[0..size - 1]
    }

    List subtraction(List obases) {
        List bases = obases.clone()
        Integer size = bases.size()
        Integer subtractionSize = random.getInt(3) + 2
        Integer index = random.getInt(bases.size() - 5)
        List newBases = []
        bases.eachWithIndex {it, i ->
            if ((index..index + subtractionSize - 1).containsWithinBounds(i)) return
            newBases.add it
        }
        newBases.addAll(bases[0..subtractionSize - 1])
        return newBases
    }

    protected String getNewBase() {
        random.getString(Genome.BASES, 1)
    }

    protected String getNewBase(String currentBase) {
        Integer index = Genome.BASES.indexOf(currentBase) + (random.getBoolean() ? 1 : -1)
        index = index >= Genome.BASES.size() ? 0 : index
        index = index < 0 ? Genome.BASES.size() - 1 : index
        Genome.BASES[index]
    }
}
