package me.bendoerr.hevollo

class Organism implements Comparable<Organism> {

    Genome genome
    Fitness fitStrat

    Integer life

    Integer baseLife
    BigDecimal chanceToEat
    String color
    BigDecimal colorPref

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
        fitStrat = new LongevityStrengthFitness(this)
    }

    void eat() {
        if (!random.getBoolean(chanceToEat)) {
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
