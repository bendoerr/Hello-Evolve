package me.bendoerr.hevollo

class Organism implements Comparable<Organism> {

    Genome genome
    Fitness fitStrat

    Integer life

    Integer baseLife
    BigDecimal chanceToEat
    String color
    BigDecimal colorPref

    Food foodSource = Food.getInstance()

    Organism() {
        genome = new Genome()
        initialize()
    }

    Organism(String dna) {
        genome = new Genome(dna)
        initialize()
    }

    protected void initialize() {
        baseLife = genome.get('baseLongevity').encodedTrait
        chanceToEat = genome.get('strength').encodedTrait
        color = genome.get('color').encodedTrait
        colorPref = genome.get('colorPref').encodedTrait

        life = baseLife

        switch (options['fitnessStrategy', 'base']) {
            case 'withDna':
                fitStrat = new LongevityStrengthWeightedFitness(this)
                break
            case 'withJunk':
                fitStrat = new LongevityStrengthJunkFitness(this)
                break
            case 'base':
            default:
                fitStrat = new LongevityStrengthFitness(this)
        }
    }

    void eat() {
        Boolean strengthToFightForFood = random.getBoolean(chanceToEat)
        if (!strengthToFightForFood || strengthToFightForFood && !foodSource.take()) {
            life--
        }
    }

    void die() {
        life--
    }

    Boolean isDead() {
        life <= 0
    }

    int compareTo(Organism other) {
        fitness <=> other.fitness
    }

    Integer getFitness() {
        return fitStrat.get()
    }
}
