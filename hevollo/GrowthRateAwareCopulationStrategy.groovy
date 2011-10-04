class GrowthRateAwareCopulationStrategy implements CopulationStrategy {

    Integer maxChildren = 3

    BigDecimal chanceToReproduce = options['chanceToReproduce', 0.80]
    BigDecimal chanceToReproducePenalty = options['chanceToReproducePenalty', -0.30]
    BigDecimal chanceToReproduceBonus = options['chanceToReproduceBonus', 0.15]

    BigDecimal chanceToReproduceBonusGrowthRate = options['chanceToReproduceBonusGrowthRate', -0.15]
    BigDecimal chanceToReproducePenaltyGrowthRate = options['chanceToReproducePenaltyGrowthRate', 0.10]

    private Population population

    GrowthRateAwareCopulationStrategy(Population population) {
        this.population = population
    }

    BigDecimal getGrowthRate() {
        population.getLastGrowthRate()
    }

    List getChildren(Mates mates) {
        BigDecimal chance = chanceToReproduce + (
        growthRate < chanceToReproduceBonusGrowthRate ? chanceToReproduceBonus :
            growthRate > chanceToReproducePenaltyGrowthRate ? chanceToReproducePenalty : 0
        )

        if (!random.getBoolean(chance)) return []
        else return reproduce(mates)
    }

    List reproduce(Mates mates) {
        Integer numberOfChildren = (random.getFraction() * random.getFraction() * random.getFraction() * maxChildren) + 1
        (1..numberOfChildren).collect {
            new Organism(mutate(crossover(mates)))
        }
    }

    String crossover(Mates mates) {
        List p1Genes = mates.parent1.genome.ordered()
        List p2Genes = mates.parent2.genome.ordered()

        (0..p1Genes.size() - 1).inject("") {dna, i ->
            dna << crossover(p1Genes[i], p2Genes[i])
        }
    }

    String crossover(Gene p1, Gene p2) {
        Boolean p1even = (p1.encodingNumber % 2) == 0
        Boolean p2even = (p2.encodingNumber % 2) == 0

        if (p1 && p2) {
            return p1.dnaSequance
        } else if (!p1 && !p2) {
            return p2.dnaSequance
        }
        return random.getBoolean() ? p1.dnaSequance : p2.dnaSequance
    }


    String mutate(String dna) {
        List bases = dna.toList()
        bases = insertion(subtraction(substitution(bases)))
        return bases.join('')
    }

    List substitution(List bases) {
        bases[random.getInt(bases.size())] = getNewBase()
        return bases
    }

    List insertion(List bases) {
        Integer size = bases.size()
        Integer insertionSize = random.getInt(3) + 2
        Integer index = random.getInt(bases.size()-5)
        (1..insertionSize).each {
            bases.add index, getNewBase()
        }
        return  bases[0..size-1]
    }

    List subtraction(List obases) {
        List bases = obases.clone()
        Integer size = bases.size()
        Integer subtractionSize = random.getInt(3) + 2
        Integer index = random.getInt(bases.size()-5)
        List newBases = []
        bases.eachWithIndex {it, i->
            if((index..index+subtractionSize-1).containsWithinBounds(i)) return
            newBases.add it
        }
        newBases.addAll(bases[0..subtractionSize-1])
        return newBases
    }

    protected String getNewBase() {
        random.getString(Genome.BASES, 1)
    }
}
