package me.bendoerr.hevollo

abstract class Transformation<T> {
    Integer encodingNumber

    Transformation(Gene gene) {
        this.encodingNumber = gene.encodingNumber
    }

    abstract T getTrait()
}

