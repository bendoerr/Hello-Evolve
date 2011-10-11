package me.bendoerr.hevollo

enum ColorPrefTrait {
    NOPREF(0.0), PREF(0.55), STRONGPREF(0.95)

    BigDecimal colorPref

    ColorPrefTrait(BigDecimal colorPref) {
        this.colorPref = colorPref
    }
}

