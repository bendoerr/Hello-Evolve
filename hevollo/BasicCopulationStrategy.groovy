class BasicCopulationStrategy implements CopulationStrategy {

    List<Organism> getChildren(Mates mates) {
        if(random.getBoolean(0.20)) return []

        List p1Genes = mates.parent1.genome.ordered()
        List p2Genes = mates.parent2.genome.ordered()

        String newDna = (0..p1Genes.size()-1).inject("") {dna,i->
            dna << crossover(p1Genes[i], p2Genes[i])
        }

        return [new Organism(mutate(newDna))]
    }

    String crossover(Gene p1, Gene p2) {
        Boolean p1even = (p1.encodingNumber % 2) == 0
        Boolean p2even = (p2.encodingNumber % 2) == 0

        if(p1 && p2) {
            return p1.dnaSequance
        } else if (!p1 && !p2) {
            return p2.dnaSequance
        }
        return random.getBoolean() ? p1.dnaSequance : p2.dnaSequance
    }

    String mutate(String dna) {
        List bases = dna.toList()
        (0..random.getInt(5)).each {
            bases[random.getInt(dna.size())] = random.getString(Genome.BASES, 1)
        }
        return bases.join('')
    }
}

