class Simulation {
    StopSimulation stopped

    public static void main(String[] args) {
        bootstrap()

        StartOptions opts = StartOptions.getInstance()
        opts.initialize(args)

        if(opts.isInvalid()) return

        new Simulation().loop()
    }

    static void bootstrap() {
        Object.metaClass.getOptions {-> StartOptions.getInstance() }
        Object.metaClass.getRandom {-> SimRandom.getInstance() }
    }

    Simulation() {
        stopped = new StopSimulation()
    }

    void loop() {
        Population population = new Population(options['population'])
        Integer generation = 0
        while(!stopped) {
            generation++
            population.cycle()
            println "Size: ${population.size()}"
            println "----"
            population.each {
                println "$it.fitness $it.life $it.chanceToEat $it.color $it.colorPref $it.genome.dna"
            }
            println "================"
            if(population.isDead()) break
        }
        println "Press ENTER to exit."
    }
}
