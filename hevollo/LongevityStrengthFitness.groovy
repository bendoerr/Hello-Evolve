class LongevityStrengthFitness implements Fitness {
    private Organism organism

    LongevityStrengthFitness(Organism organism) {
        this.organism = organism
    }

    Integer get() {
        organism.genome.get('baseLongevity').encodingNumber + organism.genome.get('strength').encodingNumber
    }
}
