import java.util.concurrent.ConcurrentLinkedQueue

@Singleton(lazy=true)
class SimulationInput {
	private Thread inputThread
	
	ConcurrentLinkedQueue<String> input
	Map callbacks = [:]

	private SimulationInput() {
		input = new ConcurrentLinkedQueue<String>()
        loopForInput()
	}

	void loopForInput() {
		inputThread = new Thread({
			while(!simulationStopped) {
				String input = System.console().readLine()
                simulationInput.input.add(input)
			}
		})
		inputThread.start()
	}

	
	void processInput() {
		String command = input.poll() 
		while(command) {
			Closure callback = callbacks.get(command)
			if(callback) callback()
			command = input.poll()
		}
	}

	void registerCallback(String input, Closure callback) {
		callbacks.put(input, callback)
	}

}
