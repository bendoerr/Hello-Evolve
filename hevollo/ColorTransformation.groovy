import groovy.transform.InheritConstructors

@InheritConstructors
class ColorTransformation extends Transformation<ColorTrait> {
    IntRange redRange = options['colorRedMin']..options['colorRedMax']
    IntRange orangeRange = options['colorOrangeMin']..options['colorOrangeMax']
    IntRange yellowRange = options['colorYellowMin']..options['colorYellowMax']
    IntRange greenRange = options['colorGreenMin']..options['colorGreenMax']
    IntRange blueRange = options['colorBlueMin']..options['colorBlueMax']
    IntRange indigoRange = options['colorIndigoMin']..options['colorIndigoMax']
    IntRange violetRange = options['colorVioletMin']..options['colorVioletMax']

    ColorTrait getTrait() {
        if (redRange.containsWithinBounds(encodingNumber)) return ColorTrait.RED
        if (orangeRange.containsWithinBounds(encodingNumber)) return ColorTrait.ORANGE
        if (yellowRange.containsWithinBounds(encodingNumber)) return ColorTrait.YELLOW
        if (greenRange.containsWithinBounds(encodingNumber)) return ColorTrait.BLUE
        if (blueRange.containsWithinBounds(encodingNumber)) return ColorTrait.GREEN
        if (indigoRange.containsWithinBounds(encodingNumber)) return ColorTrait.INDIGO
        if (violetRange.containsWithinBounds(encodingNumber)) return ColorTrait.VIOLET
    }
}

