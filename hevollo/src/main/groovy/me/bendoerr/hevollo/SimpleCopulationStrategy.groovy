package me.bendoerr.hevollo

class SimpleCopulationStrategy extends CopulationStrategy {

    List<Organism> getChildren(Mates mates) {
        if (random.getBoolean(0.20)) return []

        List p1Genes = mates.parent1.genome.ordered()
        List p2Genes = mates.parent2.genome.ordered()

        String newDna = (0..p1Genes.size() - 1).inject("") {dna, i ->
            dna << crossover(p1Genes[i], p2Genes[i])
        }

        return [new Organism(mutationAlgorithm.mutate(newDna))]
    }

}

