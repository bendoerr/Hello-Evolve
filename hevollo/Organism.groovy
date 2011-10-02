class Organism {

    Genome genome
    Fitness fitStrat

    Integer life

    Integer baseLife
    BigDecimal chanceToEat
    String color
    BigDecimal colorPref

    Organism() {
      if(!genome) genome = new Genome()

      baseLife = genome.get('baseLongevity').encodedTrait
      chanceToEat = genome.get('strength').encodedTrait
      color = genome.get('color').encodedTrait
      colorPref = genome.get('colorPref').encodedTrait

      life = baseLife
      fitStrat = new LongevityStrengthFitness(this)
    }

    void eat() {
        if(!random.getBoolean(chanceToEat)) {
            life--
        }
    }

    void die() {
        life--
    }

    Boolean isDead() {
        life <= 0
    }

    Integer getFitness() {
        return fitStrat.get()
    }
}
