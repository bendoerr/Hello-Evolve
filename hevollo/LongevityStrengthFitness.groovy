class LongevityStrengthFitness implements Fitness {
    private Organism organism

    LongevityStrengthFitness(Organism organism) {
        this.organism = organism
    }

    Integer get() {
        organism.genome.genes.baseLongevity.encodingNumber + organism.genome.genes.strength.encodingNumber
    }
}
