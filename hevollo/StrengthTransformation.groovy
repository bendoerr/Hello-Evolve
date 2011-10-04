import groovy.transform.InheritConstructors

@InheritConstructors
class StrengthTransformation extends Transformation<StrengthTrait> {
    IntRange weakRange = options['strengthWeakMin']..options['strengthWeakMax']
    IntRange averageRange = options['strengthAverageMin']..options['strengthAverageMax']
    IntRange strongRange = options['strengthStrongMin']..options['strengthStrongMax']

    StrengthTrait getTrait() {
        if (weakRange.containsWithinBounds(encodingNumber)) return StrengthTrait.WEAK
        if (averageRange.containsWithinBounds(encodingNumber)) return StrengthTrait.AVERAGE
        if (strongRange.containsWithinBounds(encodingNumber)) return StrengthTrait.STRONG
    }
}

