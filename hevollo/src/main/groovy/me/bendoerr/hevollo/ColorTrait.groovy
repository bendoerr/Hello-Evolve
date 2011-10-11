package me.bendoerr.hevollo

enum ColorTrait {
    RED('red'), ORANGE('orange'), YELLOW('yellow'), GREEN('green'),
    BLUE('blue'), INDIGO('indigo'), VIOLET('violet')

    private static List<ColorTrait> ORDERED = [RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET]

    String color

    ColorTrait(String color) {
        this.color = color
    }

    static forColor(String color) {
        values().find { it.color == color }
    }

    List<ColorTrait> neighbors() {
        Integer i = ORDERED.indexOf(this)
        List neighbors = []
        if (i > 0) neighbors.add(ORDERED[i - 1])
        if (i < ORDERED.size() - 1) neighbors.add(ORDERED[i + 1])
        return neighbors
    }
}

