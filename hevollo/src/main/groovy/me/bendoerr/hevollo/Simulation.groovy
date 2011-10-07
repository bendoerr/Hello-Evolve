package me.bendoerr.hevollo

class Simulation {
    Integer generation
    Population population
    Display display

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
        Object.metaClass.getSimulationStopped {-> SimulationStopped.getInstance() }
        Object.metaClass.getSimulationInput {-> SimulationInput.getInstance()}
    }

    Simulation() {
        population = new Population(options['population'])
        generation = 0
        display = new SimplePopulationDisplay()
    }

    void loop() {
        display.render(this)
        simulationInput.processInput()
        while (!simulationStopped) {
            generation++
            population.cycle()
            display.render(this)
            if (population.isDead()) break
            simulationInput.processInput()
        }
        println "Press ENTER to exit."
    }
}
