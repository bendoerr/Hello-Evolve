enum ColorPrefTrait {
    NOPREF(0.0), PREF(0.45), STRONGPREF(0.80)

    BigDecimal colorPref

    ColorPrefTrait(BigDecimal colorPref) {
        this.colorPref = colorPref
    }
}

