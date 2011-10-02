final class BaseLongevityGene extends Gene {
    Integer getStartIndex() { 3 }
    Integer getEndIndex() { 12 }

    BaseLongevityGene(Genome owner) {
        super(owner)
        encodingTransformation = new BaseLongevityTransformation(this)
    }

    Integer getEncodedTrait() {
        encodingTransformation.getTrait().life
    }
}
