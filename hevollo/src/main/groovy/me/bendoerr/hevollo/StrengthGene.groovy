package me.bendoerr.hevollo

final class StrengthGene extends Gene {
    Integer getStartIndex() { 16 }

    Integer getEndIndex() { 25 }

    StrengthGene(Genome owner) {
        super(owner)
        encodingTransformation = new StrengthTransformation(this)
    }

    BigDecimal getEncodedTrait() {
        encodingTransformation.getTrait().chanceToEat
    }
}
