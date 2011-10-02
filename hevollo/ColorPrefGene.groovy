final class ColorPrefGene extends Gene {
    Integer getStartIndex() { 42 }
    Integer getEndIndex() { 51 }

    ColorPrefGene(Genome owner) {
        super(owner)
        encodingTransformation = new ColorPrefTransformation(this)
    }

    BigDecimal getEncodedTrait() {
        encodingTransformation.getTrait().colorPref
    }
}
