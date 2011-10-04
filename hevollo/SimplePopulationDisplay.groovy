class SimplePopulationDisplay implements Display {
    Boolean windowsColors = options['windowsColors']

    void render(Simulation sim) {
        Integer gen = sim.generation
        Population pop = sim.population

        StringBuffer sb = new StringBuffer()
        sb << ("=" * 83) + "\n"
        sb << "Generation:".padRight(15) + gen + "\n"
        sb << "Size:".padRight(15) + pop.size() + "\n"
        sb << ("-" * 83) + "\n"

        pop.each {
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
        if(windowsColors) return s
        
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
