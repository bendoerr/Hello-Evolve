import groovy.transform.*
@InheritConstructors
class BaseLongevityTransformation extends Transformation<BaseLongevityTrait> {
    IntRange shortRange = options['baseLongevityShortMin']..options['baseLongevityShortMax']
    IntRange averageRange = options['baseLongevityAverageMin']..options['baseLongevityAverageMax']
    IntRange longRange = options['baseLongevityLongMin']..options['baseLongevityLongMax']

    BaseLongevityTrait getTrait() {
        if(shortRange.containsWithinBounds(encodingNumber)) return BaseLongevityTrait.SHORT
        if(averageRange.containsWithinBounds(encodingNumber)) return BaseLongevityTrait.AVERAGE
        if(longRange.containsWithinBounds(encodingNumber)) return BaseLongevityTrait.LONG
    }
}

