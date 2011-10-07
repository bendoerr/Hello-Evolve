package me.bendoerr.hevollo

enum StrengthTrait {
    WEAK(0.60), AVERAGE(0.75), STRONG(0.90)

    BigDecimal chanceToEat

    StrengthTrait(BigDecimal chanceToEat) {
        this.chanceToEat = chanceToEat
    }
}

