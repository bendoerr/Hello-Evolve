@Singleton(lazy = true)
class SimulationStopped {
    private isStopped = false

    private SimulationStopped() {
        Closure callback = { isStopped = true }
        simulationInput.registerCallback('q', callback)
    }

    boolean asBoolean() {
        return isStopped
    }
}