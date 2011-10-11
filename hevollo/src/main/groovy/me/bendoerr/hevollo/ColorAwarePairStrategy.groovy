package me.bendoerr.hevollo

class ColorAwarePairStrategy implements PairStrategy {

    List<Mates> mate(Population population) {
        List<Organism> ordered = population.sortByFitness()
        return matchMates(ordered)
    }

    private List<Mates> matchMates(List<Organism> ordered) {
        if (ordered.size() < 2) return []

        Organism p1 = ordered.remove(0)

        // Find the next possible mate
        List possible = getAttractiveColors(p1)
        Organism p2 = ordered.find {
            (possible).contains(it.color)
        }

        if (!p2) return []
        ordered.remove(p2)

        return matchMates(ordered) + new Mates(p1, p2)
    }

    private getAttractiveColors(Organism o) {
        if (random.getBoolean(o.colorPref)) {
            return [o.color]
        }

        ColorTrait color = ColorTrait.forColor(o.color)
        return (color.neighbors() << color)*.color
    }

}
