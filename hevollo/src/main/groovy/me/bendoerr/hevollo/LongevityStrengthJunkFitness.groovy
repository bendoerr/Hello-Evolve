package me.bendoerr.hevollo

import groovy.transform.InheritConstructors

@InheritConstructors
class LongevityStrengthJunkFitness extends LongevityStrengthFitness {

    Integer get() {
        (super.get() * 1.25) + organism.genome.junkValue()
    }

}
