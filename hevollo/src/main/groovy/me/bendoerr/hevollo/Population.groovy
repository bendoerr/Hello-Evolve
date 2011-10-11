package me.bendoerr.hevollo

@Mixin(ArrayList)
class Population {
    private List lastFewAverageGrowthRates = []
    BigDecimal averageGrowthRate
    BigDecimal lastGrothRate
    Integer delta

    PairStrategy pairing
    CopulationStrategy copulation

    Population(Integer initialSize) {
        addAll((1..initialSize).collect { new Organism() })

        switch (options['pairStrategy', 'colorAndFitness']) {
            case 'colorAware':
                pairing = new ColorAwarePairStrategy()
                break
            case 'colorAndFitness':
                pairing = new ColorAndFitnessPairStrategy()
                break
            case 'simple':
            default:
                pairing = new SimplePairStrategy()
        }

        switch (options['copulationStrategy', 'growthRateAware']) {
            case 'simple':
                copulation = new SimpleCopulationStrategy()
                break
            case 'growthRateAware':
                copulation = new GrowthRateAwareCopulationStrategy(this)
                break
        }
    }

    void cycle() {
        // Eat
        each { it.eat() }

        // Bread
        /*
       How does this work. Breeding happens population wide. From the most fitness to the least.
       Each organism gets a chance to pick a mate from the available pool. Once picked the picked one is
       removed from the available pool. Organisms will generally pick an organism with the highest fitness
       ( try trournamate selection too) unless it has a it has decided to only choose same color organisms
       which it will then choose the highest fitness organism of the same color.
        */
        List children = []
        pairing.mate(this).each {mates ->
            children.addAll copulation.getChildren(mates)
        }

        Integer startSize = size()

        // Die
        removeAll inject([]) {dead, organism ->
            organism.die()
            if (organism.isDead()) {
                dead << organism
            }
            dead
        }

        delta = size() - startSize + children.size()
        lastGrothRate = delta / startSize
        if (lastFewAverageGrowthRates.size() > 3) {
            lastFewAverageGrowthRates.pop()
        }
        lastFewAverageGrowthRates.add(0, lastGrothRate)
        averageGrowthRate = lastFewAverageGrowthRates.sum() / lastFewAverageGrowthRates.size()

        // New Life
        addAll children
    }

    Boolean isAlive() {
        size() > 0
    }

    Boolean isDead() {
        !isAlive()
    }

    List<Organism> sortByFitness(Boolean reverse = false) {
        return clone().sort {l, r ->
            if (reverse) l <=> r
            else r <=> l
        }
    }

    Integer averageFitness() {
        sum { it.fitness } / size()
    }
}
