package me.bendoerr.hevollo

@Mixin(ArrayList)
class Population {
    private List lastFewAverageGrowthRates = []
    BigDecimal averageGrowthRate
    BigDecimal lastGrothRate
    Integer delta

    PairStrategy pairing
    CopulationStrategy copulation
    Food foodSource = Food.getInstance()

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
        foodSource.reset()

        // Eat
        sortByFitness().each { it.eat() }

        // Bread
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
