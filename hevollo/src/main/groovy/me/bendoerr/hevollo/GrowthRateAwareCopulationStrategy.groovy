package me.bendoerr.hevollo

class GrowthRateAwareCopulationStrategy extends CopulationStrategy {

    // Settings
    Integer maxChildren = 3
    BigDecimal chanceToReproduce = options['chanceToReproduce', 0.80]
    BigDecimal chanceToReproducePenalty = options['chanceToReproducePenalty', -0.30]
    BigDecimal chanceToReproduceBonus = options['chanceToReproduceBonus', 0.15]
    BigDecimal chanceToReproduceBonusGrowthRate = options['chanceToReproduceBonusGrowthRate', -0.08]
    BigDecimal chanceToReproducePenaltyGrowthRate = options['chanceToReproducePenaltyGrowthRate', 0.05]

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
        Integer numberOfChildren = (random.getFraction() * random.getFraction() * maxChildren) + 1
        (1..numberOfChildren).collect {
            new Organism(mutationAlgorithm.mutate(crossover(mates)))
        }
    }

    String crossover(Mates mates) {
        List p1Genes = mates.parent1.genome.ordered()
        List p2Genes = mates.parent2.genome.ordered()

        (0..p1Genes.size() - 1).inject("") {dna, i ->
            dna << crossover(p1Genes[i], p2Genes[i])
        }
    }
}
