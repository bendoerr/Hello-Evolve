final class ColorGene extends Gene {
    Integer getStartIndex() { 29 }

    Integer getEndIndex() { 38 }

    ColorGene(Genome owner) {
        super(owner)
        encodingTransformation = new ColorTransformation(this)
    }

    String getEncodedTrait() {
        encodingTransformation.getTrait().color
    }
}
