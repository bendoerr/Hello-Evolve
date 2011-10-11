package me.bendoerr.hevollo

class ColorAwarePairStrategy implements PairStrategy {

    List<Mates> mate(Population population) {
        List<Organism> ordered = population.sortByFitness()
        return matchMates(ordered)
    }

    protected List<Mates> matchMates(List<Organism> ordered) {
        if (ordered.size() < 2) return []
        Mates mates = nextMates(ordered)
        if (mates == null) return []
        return matchMates(ordered) + mates
    }

    protected Mates nextMates(List<Organism> ordered) {
        Organism p1 = ordered.remove(0)
        Organism p2 = getAttractiveMate(p1, ordered)

        if (!p2) return null
        ordered.remove(p2)
        new Mates(p1, p2)
    }

    protected Organism getAttractiveMate(Organism mate, List<Organism> ordered) {
        List possible = getAttractiveColors(mate)
        return ordered.find {
            (possible).contains(it.color)
        }
    }

    private getAttractiveColors(Organism o) {
        if (random.getBoolean(o.colorPref)) {
            return [o.color]
        }

        ColorTrait color = ColorTrait.forColor(o.color)
        return (color.neighbors() << color)*.color
    }

}
