package me.bendoerr.hevollo

enum ColorTrait {
    RED('red'), ORANGE('orange'), YELLOW('yellow'), GREEN('green'),
    BLUE('blue'), INDIGO('indigo'), VIOLET('violet')

    String color

    ColorTrait(String color) {
        this.color = color
    }
}

