@Singleton(lazy=true)
class SimulationStopped {
    private isStopped = false

    StopSimulation() {
	Closure callback = { isStopped = true }
        simulationInput.registarCallback('q', callback)
    }

    boolean asBoolean() {
        return isStopped
    }
}
