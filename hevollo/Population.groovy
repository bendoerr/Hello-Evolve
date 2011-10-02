@Mixin(ArrayList)
class Population {

    Population(Integer initialSize) {
        addAll((1..initialSize).collect { new Organism() })
    }
    
    void cycle() {
      // Eat
      each { it.eat() }
      // Bread
      
      // Die
      removeAll inject([]) {dead, organism->
          organism.die()
          if(organism.isDead()) {
            dead << organism
          }
          dead
      }
    }

    Boolean isAlive() {
        size() > 0
    }

    Boolean isDead() {
        !isAlive()
    }
}

