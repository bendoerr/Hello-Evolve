package me.bendoerr.hevollo

enum BaseLongevityTrait {
    SHORT(2), AVERAGE(3), LONG(4)

    Integer life

    BaseLongevityTrait(Integer life) {
        this.life = life
    }
}

