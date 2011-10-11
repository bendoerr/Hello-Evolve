package me.bendoerr.hevollo

class SimplePopulationDisplay implements Display {
    Boolean windowsColors = options['windowsColors']

    void render(Simulation sim) {
        Integer gen = sim.generation
        Population pop = sim.population

        if (pop.isEmpty()) {
            println "Dead"
            return
        }

        StringBuffer sb = new StringBuffer()
        Integer padding = 20
        sb << ("=" * 83) + "\n"
        sb << "Generation:".padRight(padding) + gen + "\n"
        sb << "Size:".padRight(padding) + pop.size() + "\n"
        sb << "Growth Rate:".padRight(padding) + pop.averageGrowthRate + "\n"
        sb << "Last Growth Rate:".padRight(padding) + pop.lastGrothRate + "\n"
        sb << "Avg Fitness:".padRight(padding) + pop.averageFitness() + "\n"
        sb << ("-" * 83) + "\n"

        List ordered = pop.sortByFitness()
        ordered[0..(ordered.size() > 10 ? 10 : ordered.size() - 1)].each {
            StringBuffer lb = new StringBuffer()
            lb << "$it.fitness".padRight(5)
            lb << "$it.baseLife".padRight(2)
            lb << "$it.life".padRight(3)
            lb << "$it.chanceToEat".padRight(6)
            lb << "$it.color".padRight(7)
            lb << "$it.colorPref".padRight(5)
            lb << "$it.genome.dna"
            sb << color(it.color, lb) + "\n"
        }

        println sb
    }

    String color(String color, def s) {
        if (windowsColors) return s

        Map colors = [
                red: '[1;31m',
                orange: '[0;31m',
                yellow: '[0;33m',
                green: '[0;32m',
                blue: '[0;34m',
                indigo: '[0;36m',
                violet: '[1;35m',
        ]

        String escape = "${(char) 27}"

        return escape + colors.get(color) + s + escape + "[00m"
    }
}
