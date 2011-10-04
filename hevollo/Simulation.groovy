class Simulation {
    StopSimulation stopped
    Integer generation
    Population population

    public static void main(String[] args) {
        bootstrap()

        SystemOptions opts = SystemOptions.getInstance()
        opts.initialize(args)

        if (opts.isInvalid()) return

        new Simulation().loop()
    }

    static void bootstrap() {
        Object.metaClass.getOptions {-> SystemOptions.getInstance() }
        Object.metaClass.getRandom {-> SimRandom.getInstance() }
    }

    Simulation() {
        stopped = new StopSimulation()
    }

    void loop() {
        population = new Population(options['population'])
        generation = 0
        def display = new SimplePopulationDisplay()
        display.render(this)
        while (!stopped) {
            generation++
            population.cycle()
            display.render(this)
            if (population.isDead()) break
        }
        println "Press ENTER to exit."
    }
}
