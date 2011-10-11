package me.bendoerr.hevollo

@Singleton
class Food {
    Integer maxAvailableFood = options['maxAvailableFood', 600]
    Integer availableFood

    void reset() {
        availableFood = maxAvailableFood
    }

    Boolean take() {
        if (availableFood > 0) {
            availableFood--
            return true
        } else {
            return false
        }
    }

}
