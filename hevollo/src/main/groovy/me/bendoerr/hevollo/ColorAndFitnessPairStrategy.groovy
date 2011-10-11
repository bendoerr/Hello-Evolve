package me.bendoerr.hevollo

class ColorAndFitnessPairStrategy extends ColorAwarePairStrategy {
    Integer averageFitness

    @Override
    List<Mates> mate(Population population) {
        averageFitness = population.averageFitness()
        return super.mate(population)
    }

    @Override
    protected Mates nextMates(List<Organism> ordered) {
        Mates mates = super.nextMates(ordered)
        if (mates == null) return null
        if (mates.parent1.fitness > averageFitness && random.getBoolean()) {
            ordered.add(0, mates.parent1)
        } else if (mates.parent2.fitness > averageFitness && random.getBoolean()) {
            ordered.add(0, mates.parent1)
        }
        return mates
    }

}
