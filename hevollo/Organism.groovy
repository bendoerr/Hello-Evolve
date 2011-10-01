class Organism {

    Genome genome
    Fitness fitStrat

    Organism() {
      genome = new Genome()
      fitStrat = new LongevityStrengthFitness(this)
    }

    Integer getFitness() {
        return fitStrat.get()
    }
}
