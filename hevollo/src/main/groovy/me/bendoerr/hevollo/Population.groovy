package me.bendoerr.hevollo

@Mixin(ArrayList)
class Population {

    BigDecimal lastGrowthRate
    PairStrategy pairing
    CopulationStrategy copulation

    Population(Integer initialSize) {
        addAll((1..initialSize).collect { new Organism() })

        switch (options['pairStrategy', 'simple']) {
            case 'simple':
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

        Integer change = size() - startSize + children.size()
        lastGrowthRate = change / startSize
        println "!!!!!!!!!!!!!!!! ${size()} left and adding ${children.size()} [change $change] and rate of $lastGrowthRate"
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
            if (reverse) r <=> l
            else l <=> r
        }
    }
}
