package me.bendoerr.hevollo

abstract class Gene {
    abstract Integer getStartIndex()

    abstract Integer getEndIndex()

    Transformation encodingTransformation
    String dnaSequance
    Integer encodingNumber

    Gene() {}

    Gene(Genome owner) {
        initialize(owner)
    }

    protected void initialize(Genome owner) {
        dnaSequance = owner.dna[getStartIndex()..getEndIndex()]
        encodingNumber = calculateEncoding()
    }

    protected Integer calculateEncoding() {
        dnaSequance.inject(0) {encoding, base ->
            encoding + Genome.BASES.indexOf(base) + 1
        }
    }

    abstract Object getEncodedTrait()
}
