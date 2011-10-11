package me.bendoerr.hevollo

import groovy.transform.InheritConstructors

@InheritConstructors
class LongevityStrengthWeightedFitness extends LongevityStrengthFitness {

    Integer get() {
        (super.get() * 1.5) + organism.genome.dnaValue()
    }

}
