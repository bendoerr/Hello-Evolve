package me.bendoerr.hevollo;

class SimpleMutationAlgorithm implements MutationAlgorithm {

    String mutate(String original) {
        List bases = dna.toList()
        (0..random.getInt(5)).each {
            bases[random.getInt(dna.size())] = random.getString(Genome.BASES, 1)
        }
        return bases.join('')
    }
}
