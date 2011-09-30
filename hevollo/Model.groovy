class Organism {

    Genome genome
    FitnessStrategy fitStrat

    Organism() {
      genome = new Genome()
    }

    Integer getFitness() {
        return fitStrat.get()
    }
}

import java.security.SecureRandom

@Singleton
class SimRandom {
    private SecureRandom rand
    
    private SimRandom() {
        rand = SecureRandom.getInstance("SHA1PRNG", "SUN")  
    }
    
    String getString(String characters, Integer size) {
        (1..size).inject("") {s,i-> s + characters[rand.nextInt(characters.length())] }
    }

}

interface FitnessStrategy { Integer get() }

/**
 *           1         2         3         4         5
 * 0123456789012345678901234567890123456789012345678901234 
 * V5ivlMHA4Wv6NaDZh6iCkrubV96703Akm0lFhPVXUg4kXBKqyNW09cR
 * \_/\________/\_/\________/\_/\________/\_/\________/\_/
 * Jnk          Jnk          Jnk          Jnk          Jnk
 *     Base         Strength     Color        Color
 *     Longevity                              Perference
 */
class Genome {
    private static final MAX_LENGTH = 55
    private static final BASES = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    String dna
    Map genes
    
    Genome() {
        dna = SimRandom.instance.getString(BASES, MAX_LENGTH)
        genes = [:]
        genes.baseLongevity = new BaseLongevityGene(this)
        genes.strenth = new StrengthGene(this)
        genes.color = new ColorGene(this)
        genes.colorPref = new ColorPrefGene(this)
    }
}

abstract class Gene {
    abstract Integer getStartIndex()
    abstract Integer getEndIndex()
    String dnaSequance
    Integer encodingNumber

    Gene(Genome owner) {
        dnaSequance = owner.dna[getStartIndex()..getEndIndex()]
        encodingNumber = calculateEncoding()
    }
    
    protected Integer calculateEncoding() {
        dnaSequance.inject(0) {encoding, base->
            encoding + BASES.indexOf(base)
        }
    }
}

import groovy.transform.*

@InheritConstructors
final class BaseLongevityGene extends Gene {
    Integer startIndex = 3
    Integer endIndex = 12
}

@InheritConstructors
final class StrengthGene extends Gene {
    Integer startIndex = 16
    Integer endIndex = 25
}

@InheritConstructors
final class ColorGene extends Gene {
    Integer startIndex = 29
    Integer endIndex = 38
}

@InheritConstructors
final class ColorPrefGene extends Gene {
    Integer startIndex = 42
    Integer endIndex = 51
}

def o = new Organism()
println o.genome.dna
