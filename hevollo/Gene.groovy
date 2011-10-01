import groovy.transform.*

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
            encoding + Genome.BASES.indexOf(base)
        }
    }
}

@InheritConstructors
final class BaseLongevityGene extends Gene {
    Integer getStartIndex() { 3 }
    Integer getEndIndex() { 12 }
}

@InheritConstructors
final class StrengthGene extends Gene {
    Integer getStartIndex() { 16 }
    Integer getEndIndex() { 25 }
}

@InheritConstructors
final class ColorGene extends Gene {
    Integer getStartIndex() { 29 }
    Integer getEndIndex() { 38 }
}

@InheritConstructors
final class ColorPrefGene extends Gene {
    Integer getStartIndex() { 42 }
    Integer getEndIndex() { 51 }
}

