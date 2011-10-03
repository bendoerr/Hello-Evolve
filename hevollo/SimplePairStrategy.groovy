class SimplePairStrategy implements PairStrategy {

    List<Mates> mate(Population population) {
        List<Organism> ordered = population.sortByFitness()
        return matchMates(ordered)
    }

    private List<Mates> matchMates(List<Organism> ordered) {
        if(ordered.size() < 2) return []

        Organism p1 = ordered.remove(0)
        Organism p2 = ordered.remove(0)

        return matchMates(ordered) + new Mates(p1, p2)
    }
}
