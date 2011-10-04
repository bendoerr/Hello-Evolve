class StopSimulation {
    private Thread waitForInput

    StopSimulation() {
        waitForInput = new Thread({System.console().readLine()})
        waitForInput.start()
    }

    boolean asBoolean() {
        return !waitForInput.isAlive()
    }
}
