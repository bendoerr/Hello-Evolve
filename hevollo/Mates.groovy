import groovy.transform.InheritConstructors

@InheritConstructors
class Mates extends AbstractMap.SimpleImmutableEntry<Organism, Organism> {

    Organism getParent1() {
        return getKey()
    }

    Organism getParent2() {
        return getValue() 
    }
}

