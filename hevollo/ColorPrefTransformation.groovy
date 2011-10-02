import groovy.transform.*
@InheritConstructors
class ColorPrefTransformation extends Transformation<ColorPrefTrait> {
    IntRange noPrefRange = options['colorPrefNoPrefMin']..options['colorPrefNoPrefMax']
    IntRange prefRange = options['colorPrefPrefMin']..options['colorPrefPrefMax']
    IntRange strongPrefRange = options['colorPrefStrongPrefMin']..options['colorPrefStrongPrefMax']

    ColorPrefTrait getTrait() {
        if(noPrefRange.containsWithinBounds(encodingNumber)) return ColorPrefTrait.NOPREF
        if(prefRange.containsWithinBounds(encodingNumber)) return ColorPrefTrait.PREF
        if(strongPrefRange.containsWithinBounds(encodingNumber)) return ColorPrefTrait.STRONGPREF
    }
}

